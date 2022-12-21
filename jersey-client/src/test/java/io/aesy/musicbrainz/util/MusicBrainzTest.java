package io.aesy.musicbrainz.util;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit.dsl.RequestMatcherBuilder;
import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.aesy.musicbrainz.client.MusicBrainzClient;
import io.aesy.musicbrainz.client.MusicBrainzJerseyClient;
import io.aesy.musicbrainz.client.MusicBrainzResponse;
import io.aesy.musicbrainz.concurrent.RateLimitedExecutor;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.api.WithAssumptions;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.ClientBuilder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;

@ExtendWith(HoverflyExtension.class)
public abstract class MusicBrainzTest
    implements WithAssertions, WithAssumptions {

    private static final String MUSICBRAINZ_URL = "MUSICBRAINZ_URL";

    private static URL musicbrainzUrl;
    private static boolean isIntegrationTest;
    private static Executor executor;

    static {
        String musicbrainzUrl = System.getenv(MUSICBRAINZ_URL);

        MusicBrainzTest.isIntegrationTest = musicbrainzUrl != null;

        if (!isIntegrationTest) {
            musicbrainzUrl = "http://musicbrainz.org/ws/2";
        }

        try {
            MusicBrainzTest.musicbrainzUrl = new URL(musicbrainzUrl);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid musicbrainz URL", exception);
        }

        if (isIntegrationTest) {
            MusicBrainzTest.executor = new RateLimitedExecutor(10, TimeUnit.MINUTES);
        } else {
            // Avoid rate limiting for faster tests
            MusicBrainzTest.executor = Executors.newCachedThreadPool();
        }
    }

    private Hoverfly hoverfly;

    @BeforeEach
    private void getHoverflyInstance(Hoverfly hoverfly) {
        this.hoverfly = hoverfly;

        if (isIntegrationTest) {
            hoverfly.setMode(HoverflyMode.CAPTURE);
        } else {
            hoverfly.setMode(HoverflyMode.SIMULATE);
        }
    }

    protected <T> MusicBrainzAssert<T> assertThat(MusicBrainzResponse<T> response) {
        return MusicBrainzAssert.assertThat(response);
    }

    protected MusicBrainzJerseyClient.Builder clientBuilder() {
        ClientBuilder clientBuilder = JerseyClientBuilder.newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS);

        if ("https".equals(musicbrainzUrl.getProtocol())) {
            SSLContext sslContext = hoverfly.getSslConfigurer().getSslContext();
            clientBuilder = clientBuilder.sslContext(sslContext);
        }

        return MusicBrainzJerseyClient
            .builder()
            .userAgent("musicbrainz-api-client/integration-testing (https://github.com/aesy/musicbrainz-api-client)")
            .client(clientBuilder.build())
            .baseUrl(musicbrainzUrl.toString())
            .executor(executor);
    }

    protected MusicBrainzClient client() {
        return clientBuilder().build();
    }

    protected StubServiceBuilder service() {
        String baseUrl = String.format("%s://%s", musicbrainzUrl.getProtocol(), musicbrainzUrl.getHost());

        return io.specto.hoverfly.junit.dsl.HoverflyDsl.service(baseUrl);
    }

    protected RequestMatcherBuilder get(String path) {
        return service().get(String.format("%s/%s", musicbrainzUrl.getPath(), path));
    }

    protected Simulation simulate(StubServiceBuilder serviceBuilder) {
        hoverfly.simulate(dsl(serviceBuilder));

        return () -> hoverfly.verifyAll();
    }

}

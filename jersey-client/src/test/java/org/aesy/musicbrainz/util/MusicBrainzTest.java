package org.aesy.musicbrainz.util;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit.dsl.RequestMatcherBuilder;
import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.aesy.musicbrainz.client.MusicBrainzClient;
import org.aesy.musicbrainz.client.MusicBrainzJerseyClient;
import org.aesy.musicbrainz.client.MusicBrainzResponse;
import org.aesy.musicbrainz.concurrent.RateLimitedExecutor;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.api.WithAssumptions;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
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

    private final URL musicbrainzUrl;
    private final boolean isIntegrationTest;

    private Hoverfly hoverfly;

    public MusicBrainzTest() {
        String musicbrainzUrl = System.getenv(MUSICBRAINZ_URL);

        this.isIntegrationTest = musicbrainzUrl != null;

        if (!isIntegrationTest) {
            // Avoid SSL handshake which hoverfly complains about in SIMULATE mode
            musicbrainzUrl = "http://musicbrainz.org/ws/2";
        }

        try {
            this.musicbrainzUrl = new URL(musicbrainzUrl);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid musicbrainz URL", exception);
        }
    }

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
        Executor executor;

        if (isIntegrationTest) {
            executor = new RateLimitedExecutor(1, TimeUnit.SECONDS);
        } else {
            // Avoid rate limiting for faster tests
            executor = Executors.newCachedThreadPool();
        }

        Client client = JerseyClientBuilder.createClient();

        return MusicBrainzJerseyClient
            .builder()
            .client(client)
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

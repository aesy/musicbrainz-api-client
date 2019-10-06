package org.aesy.musicbrainz.util;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit.dsl.RequestMatcherBuilder;
import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.aesy.musicbrainz.client.MusicBrainzClient;
import org.aesy.musicbrainz.client.MusicBrainzJerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;

@Tag("UnitTest")
@ExtendWith(HoverflyExtension.class)
public abstract class UnitTest
    extends Test {

    private Hoverfly hoverfly;

    @BeforeAll
    private static void setHoverflyMode(Hoverfly hoverfly) {
        hoverfly.setMode(HoverflyMode.CAPTURE);
    }

    @BeforeEach
    private void getHoverflyInstance(Hoverfly hoverfly) {
        this.hoverfly = hoverfly;
    }

    protected MusicBrainzClient client() {
        // Avoid rate limiting for faster tests
        Executor executor = Executors.newCachedThreadPool();

        Client client = JerseyClientBuilder.createClient();

        return MusicBrainzJerseyClient
            .builder()
            .client(client)
            // Avoid SSL handshake when using hoverfly
            .baseUrl("http://musicbrainz.org/ws/2")
            .executor(executor)
            .build();
    }

    public StubServiceBuilder service() {
        return io.specto.hoverfly.junit.dsl.HoverflyDsl
            .service("http://musicbrainz.org");
    }

    public RequestMatcherBuilder get(String path) {
        return service()
            .get("/ws/2/" + path);
    }

    public Simulation simulate(StubServiceBuilder serviceBuilder) {
        hoverfly.simulate(dsl(serviceBuilder));

        return () -> hoverfly.verifyAll();
    }

}

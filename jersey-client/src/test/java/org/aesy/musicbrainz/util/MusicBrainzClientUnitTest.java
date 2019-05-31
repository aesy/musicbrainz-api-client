package org.aesy.musicbrainz.util;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit.dsl.RequestMatcherBuilder;
import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.client.MusicBrainzClient;
import org.aesy.musicbrainz.client.MusicBrainzJerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import javax.ws.rs.client.Client;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;

@Tag("UnitTest")
public abstract class MusicBrainzClientUnitTest
    extends MusicBrainzClientTest {

    private Hoverfly hoverfly;

    @BeforeAll
    public static void beforeAll(Hoverfly hoverfly) {
        hoverfly.setMode(HoverflyMode.CAPTURE);
    }

    @BeforeEach
    public void beforeEach(Hoverfly hoverfly) {
        this.hoverfly = hoverfly;
    }

    @Override
    public MusicBrainzClient client() {
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

    @Override
    public RequestMatcherBuilder get(String path) {
        return service("http://musicbrainz.org")
            .get("/ws/2/" + path);
    }

    public Simulation simulate(StubServiceBuilder serviceBuilder) {
        hoverfly.simulate(dsl(serviceBuilder));

        return () -> hoverfly.verifyAll();
    }

}

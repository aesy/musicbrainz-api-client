package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.util.MusicBrainzClientUnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzJerseyClientTest
    extends MusicBrainzClientUnitTest {

    @Test
    @DisplayName("Custom user agent")
    public void useragent() {
        UUID mbid = UUID.randomUUID();
        String userAgent = "This is a custom user agent";
        MusicBrainzClient client = MusicBrainzJerseyClient.builder()
            // Avoid SSL handshake when using hoverfly
            .baseUrl("http://musicbrainz.org/ws/2")
            .userAgent(userAgent)
            .build();

        StubServiceBuilder request = get("artist/" + mbid)
            .header(HttpHeaders.USER_AGENT, userAgent)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<?> response = client
            .artist()
            .withId(mbid)
            .lookup();

        simulation.verify();
    }

}

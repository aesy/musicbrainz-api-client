package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.util.MusicBrainzTest;
import io.aesy.musicbrainz.util.Resources;
import io.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzJerseyClientTest
    extends MusicBrainzTest {

    @Test
    @DisplayName("Custom user agent")
    public void useragent() {
        UUID mbid = UUID.randomUUID();
        String userAgent = "This is a custom user agent";

        MusicBrainzClient client = clientBuilder()
            .userAgent(userAgent)
            .build();

        StubServiceBuilder request = get("artist/" + mbid)
            .header(HttpHeaders.USER_AGENT, userAgent)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<?> response = client
            .artist()
            .withId(mbid)
            .lookup();

        simulation.verify();
    }

}

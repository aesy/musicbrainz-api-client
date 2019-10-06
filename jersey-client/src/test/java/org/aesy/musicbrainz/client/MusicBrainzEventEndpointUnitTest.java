package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Event;
import org.aesy.musicbrainz.util.UnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzEventEndpointUnitTest
    extends UnitTest {

    private MusicBrainzEventEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().event();
    }

    @Test
    @DisplayName("Event lookup request")
    public void test_event_lookup() {
        UUID eventId = UUID.randomUUID();

        StubServiceBuilder request = get("event/" + eventId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Event> response = endpoint
            .withId(eventId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Event browse area request")
    public void test_event_browse_area() {
        UUID areaMbid = UUID.randomUUID();

        StubServiceBuilder request = get("event")
            .queryParam("area", areaMbid)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Event>> response = endpoint
            .withAreaId(areaMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

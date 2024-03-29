package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Event;
import io.aesy.musicbrainz.util.MBID;
import io.aesy.musicbrainz.util.MusicBrainzTest;
import io.aesy.musicbrainz.util.Resources;
import io.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzEventEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzEventEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().event();
    }

    @Test
    @DisplayName("Event lookup request")
    public void test_event_lookup() {
        UUID eventId = MBID.Event.BJORK_AT_GLOBEN;

        StubServiceBuilder request = get("event/" + eventId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Event> response = endpoint
            .withId(eventId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Event browse area request")
    public void test_event_browse_area() {
        UUID areaId = MBID.Area.STOCKHOLM;

        StubServiceBuilder request = get("event")
            .queryParam("area", areaId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Event>> response = endpoint
            .withAreaId(areaId)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Place;
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

public class MusicBrainzPlaceEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzPlaceEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().place();
    }

    @Test
    @DisplayName("Place lookup request")
    public void test_place_lookup() {
        UUID placeId = MBID.Place.KONSERTHUSET;

        StubServiceBuilder request = get("place/" + placeId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Place> response = endpoint
            .withId(placeId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Place browse area request")
    public void test_place_browse_area() {
        UUID areaId = MBID.Area.STOCKHOLM;

        StubServiceBuilder request = get("place")
            .queryParam("area", areaId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Place>> response = endpoint
            .withAreaId(areaId)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

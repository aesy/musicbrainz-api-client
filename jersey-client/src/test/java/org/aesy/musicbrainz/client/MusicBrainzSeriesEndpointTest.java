package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Series;
import org.aesy.musicbrainz.util.MusicBrainzTest;
import org.aesy.musicbrainz.util.Resources;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzSeriesEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzSeriesEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().series();
    }

    @Test
    @DisplayName("Series lookup request")
    public void test_series_lookup() {
        UUID seriesId = UUID.randomUUID();

        StubServiceBuilder request = get("series/" + seriesId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Series> response = endpoint
            .withId(seriesId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Series browse area request")
    public void test_series_browse_area() {
        UUID collectionMbid = UUID.randomUUID();

        StubServiceBuilder request = get("series")
            .queryParam("collection", collectionMbid)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Series>> response = endpoint
            .withCollectionId(collectionMbid)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

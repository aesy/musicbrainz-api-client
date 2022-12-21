package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Series;
import io.aesy.musicbrainz.util.MBID;
import io.aesy.musicbrainz.util.MusicBrainzTest;
import io.aesy.musicbrainz.util.Resources;
import io.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
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
        UUID seriesId = MBID.Series.NOW_THATS_WHAT_I_CALL_MUSIC;

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

}

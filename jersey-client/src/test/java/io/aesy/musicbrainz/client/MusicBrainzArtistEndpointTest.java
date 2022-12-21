package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Artist;
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

public class MusicBrainzArtistEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzArtistEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().artist();
    }

    @Test
    @DisplayName("Artist lookup request")
    public void test_artist_lookup() {
        UUID artistId = MBID.Artist.PETER_GABRIEL;

        StubServiceBuilder request = get("artist/" + artistId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Artist> response = endpoint
            .withId(artistId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Artist browse area request")
    public void test_artist_browse_area() {
        UUID areaId = MBID.Area.STOCKHOLM;

        StubServiceBuilder request = get("artist")
            .queryParam("area", areaId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Artist>> response = endpoint
            .withAreaId(areaId)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

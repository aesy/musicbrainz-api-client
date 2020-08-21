package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Artist;
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
        UUID artistId = UUID.randomUUID();

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
        UUID areaMbid = UUID.randomUUID();

        StubServiceBuilder request = get("artist")
            .queryParam("area", areaMbid)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Artist>> response = endpoint
            .withAreaId(areaMbid)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Artist search request")
    public void test_artist_search() {
        UUID mbid = UUID.randomUUID();
        String alias = "alias";
        String name = "name";

        StubServiceBuilder request = get("artist")
            .queryParam("query", "+arid:\"" + mbid + "\" +artist:\"" + name + "\"")
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Artist>> response = endpoint
            .query()
            .withId(mbid)
            .withName(name)
            .search();

        simulation.verify();

        assertThat(response)
            .isSuccessful();
    }

}

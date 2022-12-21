package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.ReleaseGroup;
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

public class MusicBrainzReleaseGroupEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzReleaseGroupEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().releaseGroup();
    }

    @Test
    @DisplayName("Release group lookup request")
    public void test_releaseGroup_lookup() {
        UUID releaseGroupId = MBID.ReleaseGroup.IN_RAINBOWS;

        StubServiceBuilder request = get("release-group/" + releaseGroupId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<ReleaseGroup> response = endpoint
            .withId(releaseGroupId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Release group browse artist request")
    public void test_releaseGroup_browse_artist() {
        UUID artistId = MBID.Artist.PETER_GABRIEL;

        StubServiceBuilder request = get("release-group")
            .queryParam("artist", artistId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<ReleaseGroup>> response = endpoint
            .withArtistId(artistId)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

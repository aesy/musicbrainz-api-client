package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Recording;
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

public class MusicBrainzRecordingEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzRecordingEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().recording();
    }

    @Test
    @DisplayName("Recording lookup request")
    public void test_recording_lookup() {
        UUID recordingId = MBID.Recording.VOULEZ_VOUS;

        StubServiceBuilder request = get("recording/" + recordingId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Recording> response = endpoint
            .withId(recordingId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Recording browse artist request")
    public void test_recording_browse_artist() {
        UUID artistId = MBID.Artist.PETER_GABRIEL;

        StubServiceBuilder request = get("recording")
            .queryParam("artist", artistId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Recording>> response = endpoint
            .withArtistId(artistId)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

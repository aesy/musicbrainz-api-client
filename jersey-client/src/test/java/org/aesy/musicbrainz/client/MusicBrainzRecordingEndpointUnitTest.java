package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Recording;
import org.aesy.musicbrainz.util.UnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzRecordingEndpointUnitTest
    extends UnitTest {

    private MusicBrainzRecordingEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().recording();
    }

    @Test
    @DisplayName("Recording lookup request")
    public void test_recording_lookup() {
        UUID recordingId = UUID.randomUUID();

        StubServiceBuilder request = get("recording/" + recordingId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Recording> response = endpoint
            .withId(recordingId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Recording browse area request")
    public void test_recording_browse_area() {
        UUID collectionMbid = UUID.randomUUID();

        StubServiceBuilder request = get("recording")
            .queryParam("collection", collectionMbid)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Recording>> response = endpoint
            .withCollectionId(collectionMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

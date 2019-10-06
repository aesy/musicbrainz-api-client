package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.ReleaseGroup;
import org.aesy.musicbrainz.util.UnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzReleaseGroupEndpointUnitTest
    extends UnitTest {

    private MusicBrainzReleaseGroupEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().releaseGroup();
    }

    @Test
    @DisplayName("Release group lookup request")
    public void test_releaseGroup_lookup() {
        UUID releaseGroupId = UUID.randomUUID();

        StubServiceBuilder request = get("release-group/" + releaseGroupId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<ReleaseGroup> response = endpoint
            .withId(releaseGroupId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Release group browse area request")
    public void test_releaseGroup_browse_area() {
        UUID collectionMbid = UUID.randomUUID();

        StubServiceBuilder request = get("release-group")
            .queryParam("collection", collectionMbid)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<ReleaseGroup>> response = endpoint
            .withCollectionId(collectionMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

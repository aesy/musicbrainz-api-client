package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Release;
import org.aesy.musicbrainz.util.UnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzReleaseEndpointUnitTest
    extends UnitTest {

    private MusicBrainzReleaseEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().release();
    }

    @Test
    @DisplayName("Release lookup request")
    public void test_release_lookup() {
        UUID releaseId = UUID.randomUUID();

        StubServiceBuilder request = get("release/" + releaseId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Release> response = endpoint
            .withId(releaseId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Release browse area request")
    public void test_release_browse_area() {
        UUID areaMbid = UUID.randomUUID();

        StubServiceBuilder request = get("release")
            .queryParam("area", areaMbid)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Release>> response = endpoint
            .withAreaId(areaMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

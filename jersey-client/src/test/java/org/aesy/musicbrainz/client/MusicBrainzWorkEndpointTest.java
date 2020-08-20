package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Work;
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

public class MusicBrainzWorkEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzWorkEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().work();
    }

    @Test
    @DisplayName("Work lookup request")
    public void test_work_lookup() {
        UUID workId = UUID.randomUUID();

        StubServiceBuilder request = get("work/" + workId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Work> response = endpoint
            .withId(workId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Work browse area request")
    public void test_work_browse_area() {
        UUID collectionMbid = UUID.randomUUID();

        StubServiceBuilder request = get("work")
            .queryParam("collection", collectionMbid)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Work>> response = endpoint
            .withCollectionId(collectionMbid)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

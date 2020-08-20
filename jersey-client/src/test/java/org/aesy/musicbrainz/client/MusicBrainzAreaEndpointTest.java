package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.DefAreaElementInner;
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

public class MusicBrainzAreaEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzAreaEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().area();
    }

    @Test
    @DisplayName("Area lookup request")
    public void test_area_lookup() {
        UUID areaId = UUID.randomUUID();

        StubServiceBuilder request = get("area/" + areaId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<DefAreaElementInner> response = endpoint
            .withId(areaId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Area browse area request")
    public void test_area_browse_area() {
        UUID collectionMbid = UUID.randomUUID();

        StubServiceBuilder request = get("area")
            .queryParam("collection", collectionMbid)
            .anyQueryParams()
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<DefAreaElementInner>> response = endpoint
            .withCollectionId(collectionMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

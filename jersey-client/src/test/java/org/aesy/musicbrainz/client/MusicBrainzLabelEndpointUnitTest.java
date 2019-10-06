package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Label;
import org.aesy.musicbrainz.util.UnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzLabelEndpointUnitTest
    extends UnitTest {

    private MusicBrainzLabelEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().label();
    }

    @Test
    @DisplayName("Label lookup request")
    public void test_label_lookup() {
        UUID labelId = UUID.randomUUID();

        StubServiceBuilder request = get("label/" + labelId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Label> response = endpoint
            .withId(labelId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Label browse area request")
    public void test_label_browse_area() {
        UUID areaMbid = UUID.randomUUID();

        StubServiceBuilder request = get("label")
            .queryParam("area", areaMbid)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Label>> response = endpoint
            .withAreaId(areaMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

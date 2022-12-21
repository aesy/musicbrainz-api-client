package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Label;
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

public class MusicBrainzLabelEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzLabelEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().label();
    }

    @Test
    @DisplayName("Label lookup request")
    public void test_label_lookup() {
        UUID labelId = MBID.Label.WARP;

        StubServiceBuilder request = get("label/" + labelId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Label> response = endpoint
            .withId(labelId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

    @Test
    @DisplayName("Label browse area request")
    public void test_label_browse_area() {
        UUID areaId = MBID.Area.STOCKHOLM;

        StubServiceBuilder request = get("label")
            .queryParam("area", areaId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Label>> response = endpoint
            .withAreaId(areaId)
            .browse();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

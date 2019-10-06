package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Url;
import org.aesy.musicbrainz.util.UnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzUrlEndpointUnitTest
    extends UnitTest {

    private MusicBrainzUrlEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().url();
    }

    @Test
    @DisplayName("Url lookup request")
    public void test_artist_lookup() {
        UUID artistId = UUID.randomUUID();

        StubServiceBuilder request = get("url/" + artistId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Url> response = endpoint
            .withId(artistId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

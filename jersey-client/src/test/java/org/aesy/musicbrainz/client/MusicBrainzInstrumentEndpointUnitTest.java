package org.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import org.aesy.musicbrainz.entity.Instrument;
import org.aesy.musicbrainz.util.MusicBrainzClientUnitTest;
import org.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzInstrumentEndpointUnitTest
    extends MusicBrainzClientUnitTest {

    private MusicBrainzInstrumentEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().instrument();
    }

    @Test
    @DisplayName("Instrument lookup request")
    public void test_instrument_lookup() {
        UUID instrumentId = UUID.randomUUID();

        StubServiceBuilder request = get("instrument/" + instrumentId)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Instrument> response = endpoint
            .withId(instrumentId)
            .lookup();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

    @Test
    @DisplayName("Instrument browse area request")
    public void test_instrument_browse_area() {
        UUID collectionMbid = UUID.randomUUID();

        StubServiceBuilder request = get("instrument")
            .queryParam("collection", collectionMbid)
            .willReturn(success(resource("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<List<Instrument>> response = endpoint
            .withCollectionId(collectionMbid)
            .browse();

        assertThat(response.isSuccessful())
            .isTrue();

        simulation.verify();
    }

}

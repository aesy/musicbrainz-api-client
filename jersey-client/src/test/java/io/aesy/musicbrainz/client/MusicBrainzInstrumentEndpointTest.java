package io.aesy.musicbrainz.client;

import io.specto.hoverfly.junit.dsl.StubServiceBuilder;
import io.aesy.musicbrainz.entity.Instrument;
import io.aesy.musicbrainz.util.MBID;
import io.aesy.musicbrainz.util.MusicBrainzTest;
import io.aesy.musicbrainz.util.Resources;
import io.aesy.musicbrainz.util.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class MusicBrainzInstrumentEndpointTest
    extends MusicBrainzTest {

    private MusicBrainzInstrumentEndpoint endpoint;

    @BeforeEach
    public void setup() {
        this.endpoint = client().instrument();
    }

    @Test
    @DisplayName("Instrument lookup request")
    public void test_instrument_lookup() {
        UUID instrumentId = MBID.Instrument.GUITAR;

        StubServiceBuilder request = get("instrument/" + instrumentId)
            .willReturn(success(Resources.readString("metadata.xml"), MediaType.APPLICATION_XML));

        Simulation simulation = simulate(request);

        MusicBrainzResponse<Instrument> response = endpoint
            .withId(instrumentId)
            .lookup();

        assertThat(response)
            .isSuccessful();

        simulation.verify();
    }

}

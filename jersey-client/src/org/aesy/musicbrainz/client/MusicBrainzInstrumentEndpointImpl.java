package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzInstrumentEndpointImpl
    implements MusicBrainzInstrumentEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/instrument";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzInstrumentEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzInstrumentLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzInstrumentLookupRequestImpl(target, id);
    }

}

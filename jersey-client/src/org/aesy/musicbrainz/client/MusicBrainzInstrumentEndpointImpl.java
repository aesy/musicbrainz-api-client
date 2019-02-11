package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzInstrumentEndpointImpl
    implements MusicBrainzInstrumentEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/instrument";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzInstrumentEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzInstrumentLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzInstrumentLookupRequestImpl(target, executor, id);
    }

}

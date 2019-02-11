package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzEventEndpointImpl
    implements MusicBrainzEventEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/event";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzEventEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzEventLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzEventLookupRequestImpl(target, executor, id);
    }

}

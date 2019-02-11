package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzReleaseEndpointImpl
    implements MusicBrainzReleaseEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/release";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzReleaseEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzReleaseLookupRequestImpl(target, executor, id);
    }

}

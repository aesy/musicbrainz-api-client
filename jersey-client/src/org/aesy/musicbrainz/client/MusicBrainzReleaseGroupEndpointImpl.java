package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzReleaseGroupEndpointImpl
    implements MusicBrainzReleaseGroupEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/release-group";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzReleaseGroupEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzReleaseGroupLookupRequestImpl(target, executor, id);
    }

}

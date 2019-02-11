package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzArtistEndpointImpl
    implements MusicBrainzArtistEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/artist";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzArtistEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzArtistLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzArtistLookupRequestImpl(target, executor, id);
    }

}

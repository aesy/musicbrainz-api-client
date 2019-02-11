package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzRecordingEndpointImpl
    implements MusicBrainzRecordingEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/recording";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzRecordingEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzRecordingLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzRecordingLookupRequestImpl(target, executor, id);
    }

}

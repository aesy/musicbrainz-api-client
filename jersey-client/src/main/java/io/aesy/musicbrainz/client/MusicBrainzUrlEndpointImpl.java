package io.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzUrlEndpointImpl
    implements MusicBrainzUrlEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/url";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzUrlEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzUrlLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzUrlLookupRequestImpl(target, executor, id);
    }

}

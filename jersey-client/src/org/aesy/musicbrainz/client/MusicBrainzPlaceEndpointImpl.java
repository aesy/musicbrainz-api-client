package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.DefAreaElementInner;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzPlaceEndpointImpl
    implements MusicBrainzPlaceEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/place";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzPlaceEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzPlaceLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzPlaceLookupRequestImpl(target, executor, id);
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withArea(@NotNull DefAreaElementInner area) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withAreaId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withCollection(@NotNull Collection collection) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withCollectionId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

}

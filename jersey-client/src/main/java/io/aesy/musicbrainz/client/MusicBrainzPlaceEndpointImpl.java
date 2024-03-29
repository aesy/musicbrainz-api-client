package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.DefAreaElementInner;
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
        UUID id = UUID.fromString(area.getId());

        return withAreaId(id);
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withAreaId(@NotNull UUID id) {
        return new MusicBrainzPlaceBrowseRequestImpl(target, executor, "area", id);
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzPlaceBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzPlaceBrowseRequestImpl(target, executor, "collection", id);
    }

}

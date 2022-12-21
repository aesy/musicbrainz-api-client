package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Collection;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzSeriesEndpointImpl
    implements MusicBrainzSeriesEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/series";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzSeriesEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzSeriesLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzSeriesLookupRequestImpl(target, executor, id);
    }

    @NotNull
    @Override
    public MusicBrainzSeriesBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzSeriesBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzSeriesBrowseRequestImpl(target, executor, "collection", id);
    }

}

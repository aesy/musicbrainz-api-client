package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Collection;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzWorkEndpointImpl
    implements MusicBrainzWorkEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/work";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzWorkEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzWorkLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzWorkLookupRequestImpl(target, executor, id);
    }

    @NotNull
    @Override
    public MusicBrainzWorkBrowseRequest withArtist(@NotNull Artist artist) {
        UUID id = UUID.fromString(artist.getId());

        return withArtistId(id);
    }

    @NotNull
    @Override
    public MusicBrainzWorkBrowseRequest withArtistId(@NotNull UUID id) {
        return new MusicBrainzWorkBrowseRequestImpl(target, executor, "artist", id);
    }

    @NotNull
    @Override
    public MusicBrainzWorkBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzWorkBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzWorkBrowseRequestImpl(target, executor, "collection", id);
    }

}

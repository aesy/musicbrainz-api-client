package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.Release;
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

    @NotNull
    @Override
    public MusicBrainzReleaseGroupBrowseRequest withArtist(@NotNull Artist artist) {
        UUID id = UUID.fromString(artist.getId());

        return withArtistId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupBrowseRequest withArtistId(@NotNull UUID id) {
        return new MusicBrainzReleaseGroupBrowseRequestImpl(target, executor, "artist", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzReleaseGroupBrowseRequestImpl(target, executor, "collection", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupBrowseRequest withRelease(@NotNull Release release) {
        UUID id = UUID.fromString(release.getId());

        return withReleaseId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupBrowseRequest withReleaseId(@NotNull UUID id) {
        return new MusicBrainzReleaseGroupBrowseRequestImpl(target, executor, "release", id);
    }

}

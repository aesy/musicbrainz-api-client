package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Artist;
import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.Release;
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

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withArtist(@NotNull Artist artist) {
        UUID id = UUID.fromString(artist.getId());

        return withArtistId(id);
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withArtistId(@NotNull UUID id) {
        return new MusicBrainzRecordingBrowseRequestImpl(target, executor, "artist", id);
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzRecordingBrowseRequestImpl(target, executor, "collection", id);
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withRelease(@NotNull Release release) {
        UUID id = UUID.fromString(release.getId());

        return withReleaseId(id);
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withReleaseId(@NotNull UUID id) {
        return new MusicBrainzRecordingBrowseRequestImpl(target, executor, "release", id);
    }

}

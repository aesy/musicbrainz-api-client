package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.*;
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

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withArea(@NotNull DefAreaElementInner area) {
        UUID id = UUID.fromString(area.getId());

        return withAreaId(id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withAreaId(@NotNull UUID id) {
        return new MusicBrainzArtistBrowseRequestImpl(target, executor, "area", id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzArtistBrowseRequestImpl(target, executor, "collection", id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withRecording(@NotNull Recording recording) {
        UUID id = UUID.fromString(recording.getId());

        return withRecordingId(id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withRecordingId(@NotNull UUID id) {
        return new MusicBrainzArtistBrowseRequestImpl(target, executor, "recording", id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withRelease(@NotNull Release release) {
        UUID id = UUID.fromString(release.getId());

        return withReleaseId(id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withReleaseId(@NotNull UUID id) {
        return new MusicBrainzArtistBrowseRequestImpl(target, executor, "release", id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withReleaseGroup(@NotNull ReleaseGroup releaseGroup) {
        UUID id = UUID.fromString(releaseGroup.getId());

        return withReleaseGroupId(id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withReleaseGroupId(@NotNull UUID id) {
        return new MusicBrainzArtistBrowseRequestImpl(target, executor, "release-group", id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withWork(@NotNull Work work) {
        UUID id = UUID.fromString(work.getId());

        return withWorkId(id);
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withWorkId(@NotNull UUID id) {
        return new MusicBrainzArtistBrowseRequestImpl(target, executor, "work", id);
    }

}

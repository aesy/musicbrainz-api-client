package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.*;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzReleaseEndpointImpl
    implements MusicBrainzReleaseEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/release";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzReleaseEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzReleaseLookupRequestImpl(target, executor, id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withArea(@NotNull DefAreaElementInner area) {
        UUID id = UUID.fromString(area.getId());

        return withAreaId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withAreaId(@NotNull UUID id) {
        return new MusicBrainzReleaseBrowseRequestImpl(target, executor, "area", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withArtist(@NotNull Artist artist) {
        UUID id = UUID.fromString(artist.getId());

        return withArtistId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withArtistId(@NotNull UUID id) {
        return new MusicBrainzReleaseBrowseRequestImpl(target, executor, "artist", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzReleaseBrowseRequestImpl(target, executor, "collection", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withLabel(@NotNull Label label) {
        UUID id = UUID.fromString(label.getId());

        return withLabelId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withLabelId(@NotNull UUID id) {
        return new MusicBrainzReleaseBrowseRequestImpl(target, executor, "label", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withRecording(@NotNull Recording recording) {
        UUID id = UUID.fromString(recording.getId());

        return withRecordingId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withRecordingId(@NotNull UUID id) {
        return new MusicBrainzReleaseBrowseRequestImpl(target, executor, "recording", id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withReleaseGroup(@NotNull ReleaseGroup releaseGroup) {
        UUID id = UUID.fromString(releaseGroup.getId());

        return withReleaseGroupId(id);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withReleaseGroupId(@NotNull UUID id) {
        return new MusicBrainzReleaseBrowseRequestImpl(target, executor, "release-group", id);
    }

}

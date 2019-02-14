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
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withAreaId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withArtist(@NotNull Artist artist) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withArtistId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withCollection(@NotNull Collection collection) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withCollectionId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withLabel(@NotNull Label label) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withLabelId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withRecording(@NotNull Recording recording) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withRecordingId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withReleaseGroup(@NotNull ReleaseGroup releaseGroup) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzReleaseBrowseRequest withReleaseGroupId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

}

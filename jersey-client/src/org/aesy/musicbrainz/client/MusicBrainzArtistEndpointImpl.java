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
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withAreaId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withCollection(@NotNull Collection collection) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withCollectionId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withRecording(@NotNull Recording recording) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withRecordingId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withRelease(@NotNull Release release) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withReleaseId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withReleaseGroup(@NotNull ReleaseGroup releaseGroup) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withReleaseGroupId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withWork(@NotNull Work work) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzArtistBrowseRequest withWorkId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

}

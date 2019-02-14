package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.Release;
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
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withArtistId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withCollection(@NotNull Collection collection) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withCollectionId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withRelease(@NotNull Release release) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzRecordingBrowseRequest withReleaseId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

}

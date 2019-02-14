package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.DefAreaElementInner;
import org.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzLabelEndpointImpl
    implements MusicBrainzLabelEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/label";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzLabelEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzLabelLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzLabelLookupRequestImpl(target, executor, id);
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withArea(@NotNull DefAreaElementInner area) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withAreaId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withCollection(@NotNull Collection collection) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withCollectionId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withRelease(@NotNull Release release) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withReleaseId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

}

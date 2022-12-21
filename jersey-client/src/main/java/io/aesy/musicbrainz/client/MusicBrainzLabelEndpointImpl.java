package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.DefAreaElementInner;
import io.aesy.musicbrainz.entity.Release;
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
        UUID id = UUID.fromString(area.getId());

        return withAreaId(id);
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withAreaId(@NotNull UUID id) {
        return new MusicBrainzLabelBrowseRequestImpl(target, executor, "area", id);
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withCollection(@NotNull Collection collection) {
        UUID id = UUID.fromString(collection.getId());

        return withCollectionId(id);
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withCollectionId(@NotNull UUID id) {
        return new MusicBrainzLabelBrowseRequestImpl(target, executor, "collection", id);
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withRelease(@NotNull Release release) {
        UUID id = UUID.fromString(release.getId());

        return withReleaseId(id);
    }

    @NotNull
    @Override
    public MusicBrainzLabelBrowseRequest withReleaseId(@NotNull UUID id) {
        return new MusicBrainzLabelBrowseRequestImpl(target, executor, "release", id);
    }

}

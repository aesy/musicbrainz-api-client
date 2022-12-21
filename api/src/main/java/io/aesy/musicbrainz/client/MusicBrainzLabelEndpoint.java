package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.DefAreaElementInner;
import io.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzLabelEndpoint {

    @NotNull
    MusicBrainzLabelLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzLabelBrowseRequest withArea(@NotNull DefAreaElementInner area);

    @NotNull
    MusicBrainzLabelBrowseRequest withAreaId(@NotNull UUID id);

    @NotNull
    MusicBrainzLabelBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzLabelBrowseRequest withCollectionId(@NotNull UUID id);

    @NotNull
    MusicBrainzLabelBrowseRequest withRelease(@NotNull Release release);

    @NotNull
    MusicBrainzLabelBrowseRequest withReleaseId(@NotNull UUID id);

}

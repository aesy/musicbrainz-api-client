package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.DefAreaElementInner;
import org.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzLabelEndpoint {

    @NotNull
    MusicBrainzLabelSearchRequest query();

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

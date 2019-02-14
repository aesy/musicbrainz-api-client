package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Collection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzSeriesEndpoint {

    @NotNull
    MusicBrainzSeriesLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzSeriesBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzSeriesBrowseRequest withCollectionId(@NotNull UUID id);

}

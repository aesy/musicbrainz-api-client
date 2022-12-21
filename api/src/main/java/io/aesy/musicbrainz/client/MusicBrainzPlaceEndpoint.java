package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.DefAreaElementInner;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzPlaceEndpoint {

    @NotNull
    MusicBrainzPlaceLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzPlaceBrowseRequest withArea(@NotNull DefAreaElementInner area);

    @NotNull
    MusicBrainzPlaceBrowseRequest withAreaId(@NotNull UUID id);

    @NotNull
    MusicBrainzPlaceBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzPlaceBrowseRequest withCollectionId(@NotNull UUID id);

}

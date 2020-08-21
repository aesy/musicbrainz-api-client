package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Collection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzInstrumentEndpoint {

    @NotNull
    MusicBrainzInstrumentSearchRequest query();

    @NotNull
    MusicBrainzInstrumentLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzInstrumentBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzInstrumentBrowseRequest withCollectionId(@NotNull UUID id);

}

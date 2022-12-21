package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Collection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzInstrumentEndpoint {

    @NotNull
    MusicBrainzInstrumentLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzInstrumentBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzInstrumentBrowseRequest withCollectionId(@NotNull UUID id);

}

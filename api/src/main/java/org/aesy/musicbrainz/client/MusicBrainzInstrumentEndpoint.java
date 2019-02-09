package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzInstrumentEndpoint {

    @NotNull
    MusicBrainzInstrumentLookupRequest withId(@NotNull UUID id);

}

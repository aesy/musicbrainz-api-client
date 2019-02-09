package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzUrlEndpoint {

    @NotNull
    MusicBrainzUrlLookupRequest withId(@NotNull UUID id);

}

package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzPlaceEndpoint {

    @NotNull
    MusicBrainzPlaceLookupRequest withId(@NotNull UUID id);

}

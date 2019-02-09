package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzWorkEndpoint {

    @NotNull
    MusicBrainzWorkLookupRequest withId(@NotNull UUID id);

}

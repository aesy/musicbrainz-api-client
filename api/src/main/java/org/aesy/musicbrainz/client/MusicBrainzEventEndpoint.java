package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzEventEndpoint {

    @NotNull
    MusicBrainzEventLookupRequest withId(@NotNull UUID id);

}

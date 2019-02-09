package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzAreaEndpoint {

    @NotNull
    MusicBrainzAreaLookupRequest withId(@NotNull UUID id);

}

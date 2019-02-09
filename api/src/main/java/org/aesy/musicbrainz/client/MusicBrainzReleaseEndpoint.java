package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzReleaseEndpoint {

    @NotNull
    MusicBrainzReleaseLookupRequest withId(@NotNull UUID id);

}

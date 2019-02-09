package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzReleaseGroupEndpoint {

    @NotNull
    MusicBrainzReleaseGroupLookupRequest withId(@NotNull UUID id);

}

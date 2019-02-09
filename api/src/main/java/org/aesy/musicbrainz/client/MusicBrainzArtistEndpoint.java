package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzArtistEndpoint {

    @NotNull
    MusicBrainzArtistLookupRequest withId(@NotNull UUID id);

}

package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

public interface MusicBrainzClient {

    @NotNull
    MusicBrainzArtistEndpoint artist();

}

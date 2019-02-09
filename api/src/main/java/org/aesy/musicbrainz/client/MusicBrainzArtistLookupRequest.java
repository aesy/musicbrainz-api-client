package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzArtistLookupRequest
    extends MusicBrainzLookupRequest<Artist> {

    @NotNull
    MusicBrainzArtistLookupRequest include(@NotNull String... include);

}

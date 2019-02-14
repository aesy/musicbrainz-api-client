package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzArtistBrowseRequest
    extends MusicBrainzBrowseRequest<Artist> {

    @NotNull
    MusicBrainzArtistBrowseRequest include(@NotNull String... include);

}

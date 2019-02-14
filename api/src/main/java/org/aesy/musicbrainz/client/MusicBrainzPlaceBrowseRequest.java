package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Place;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzPlaceBrowseRequest
    extends MusicBrainzBrowseRequest<Place> {

    @NotNull
    MusicBrainzPlaceBrowseRequest include(@NotNull String... include);

}

package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Series;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzSeriesBrowseRequest
    extends MusicBrainzBrowseRequest<Series> {

    @NotNull
    MusicBrainzSeriesBrowseRequest include(@NotNull String... include);

}

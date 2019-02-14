package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Url;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzUrlBrowseRequest
    extends MusicBrainzBrowseRequest<Url> {

    @NotNull
    MusicBrainzUrlBrowseRequest include(@NotNull String... include);

}

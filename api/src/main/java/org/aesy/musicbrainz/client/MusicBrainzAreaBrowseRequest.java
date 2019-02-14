package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.DefAreaElementInner;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzAreaBrowseRequest
    extends MusicBrainzBrowseRequest<DefAreaElementInner> {

    @NotNull
    MusicBrainzAreaBrowseRequest include(@NotNull String... include);

}

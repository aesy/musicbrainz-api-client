package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Work;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzWorkBrowseRequest
    extends MusicBrainzBrowseRequest<Work> {

    @NotNull
    MusicBrainzWorkBrowseRequest include(@NotNull String... include);

}

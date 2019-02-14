package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.ReleaseGroup;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzReleaseGroupBrowseRequest
    extends MusicBrainzBrowseRequest<ReleaseGroup> {

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest include(@NotNull String... include);

}

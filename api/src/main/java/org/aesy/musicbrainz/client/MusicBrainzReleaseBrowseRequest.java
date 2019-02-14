package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzReleaseBrowseRequest
    extends MusicBrainzBrowseRequest<Release> {

    @NotNull
    MusicBrainzReleaseBrowseRequest include(@NotNull String... include);

}

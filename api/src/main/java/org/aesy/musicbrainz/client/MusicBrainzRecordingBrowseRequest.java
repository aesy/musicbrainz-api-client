package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Recording;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzRecordingBrowseRequest
    extends MusicBrainzBrowseRequest<Recording> {

    @NotNull
    MusicBrainzRecordingBrowseRequest include(@NotNull String... include);

}

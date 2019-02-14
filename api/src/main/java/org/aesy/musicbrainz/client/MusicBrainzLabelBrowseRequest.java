package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Label;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzLabelBrowseRequest
    extends MusicBrainzBrowseRequest<Label> {

    @NotNull
    MusicBrainzLabelBrowseRequest include(@NotNull String... include);

}

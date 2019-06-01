package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Label;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzLabelLookupRequest
    extends MusicBrainzLookupRequest<Label> {

    @NotNull
    MusicBrainzLabelLookupRequest includeReleases();

}

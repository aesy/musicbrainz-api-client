package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Label;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzLabelLookupRequest
    extends MusicBrainzLookupRequest<Label> {

    @NotNull
    MusicBrainzLabelLookupRequest includeReleases();

}

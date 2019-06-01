package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.ReleaseGroup;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzReleaseGroupLookupRequest
    extends MusicBrainzLookupRequest<ReleaseGroup> {

    @NotNull
    MusicBrainzReleaseGroupLookupRequest includeArtists();

    @NotNull
    MusicBrainzReleaseGroupLookupRequest includeReleases();

}

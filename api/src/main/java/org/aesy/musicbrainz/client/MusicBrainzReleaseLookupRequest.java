package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzReleaseLookupRequest
    extends MusicBrainzLookupRequest<Release> {

    @NotNull
    MusicBrainzReleaseLookupRequest includeArtists();

    @NotNull
    MusicBrainzReleaseLookupRequest includeCollections();

    @NotNull
    MusicBrainzReleaseLookupRequest includeLabels();

    @NotNull
    MusicBrainzReleaseLookupRequest includeRecordings();

    @NotNull
    MusicBrainzReleaseLookupRequest includeReleaseGroups();

}

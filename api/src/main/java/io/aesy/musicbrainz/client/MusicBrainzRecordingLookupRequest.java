package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Recording;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzRecordingLookupRequest
    extends MusicBrainzLookupRequest<Recording> {

    @NotNull
    MusicBrainzRecordingLookupRequest includeArtists();

    @NotNull
    MusicBrainzRecordingLookupRequest includeReleases();

}

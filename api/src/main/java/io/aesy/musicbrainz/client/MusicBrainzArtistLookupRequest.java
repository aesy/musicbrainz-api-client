package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Artist;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzArtistLookupRequest
    extends MusicBrainzLookupRequest<Artist> {

    @NotNull
    MusicBrainzArtistLookupRequest includeRecordings();

    @NotNull
    MusicBrainzArtistLookupRequest includeReleases();

    @NotNull
    MusicBrainzArtistLookupRequest includeReleaseGroups();

    @NotNull
    MusicBrainzArtistLookupRequest includeWorks();

}

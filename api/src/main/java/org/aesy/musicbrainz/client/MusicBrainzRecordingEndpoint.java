package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzRecordingEndpoint {

    @NotNull
    MusicBrainzRecordingLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzRecordingBrowseRequest withArtist(@NotNull Artist artist);

    @NotNull
    MusicBrainzRecordingBrowseRequest withArtistId(@NotNull UUID id);

    @NotNull
    MusicBrainzRecordingBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzRecordingBrowseRequest withCollectionId(@NotNull UUID id);

    @NotNull
    MusicBrainzRecordingBrowseRequest withRelease(@NotNull Release release);

    @NotNull
    MusicBrainzRecordingBrowseRequest withReleaseId(@NotNull UUID id);

}

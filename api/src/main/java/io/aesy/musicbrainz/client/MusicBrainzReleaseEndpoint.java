package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzReleaseEndpoint {

    @NotNull
    MusicBrainzReleaseLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseBrowseRequest withArea(@NotNull DefAreaElementInner area);

    @NotNull
    MusicBrainzReleaseBrowseRequest withAreaId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseBrowseRequest withArtist(@NotNull Artist artist);

    @NotNull
    MusicBrainzReleaseBrowseRequest withArtistId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzReleaseBrowseRequest withCollectionId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseBrowseRequest withLabel(@NotNull Label label);

    @NotNull
    MusicBrainzReleaseBrowseRequest withLabelId(@NotNull UUID id);

    // @NotNull
    // MusicBrainzReleaseBrowseRequest withTrack(@NotNull DefTrackData track);

    // @NotNull
    // MusicBrainzReleaseBrowseRequest withTrackId(@NotNull UUID id);

    // @NotNull
    // MusicBrainzReleaseBrowseRequest withTrackArtist(@NotNull Artist artist);

    // @NotNull
    // MusicBrainzReleaseBrowseRequest withTrackArtistId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseBrowseRequest withRecording(@NotNull Recording recording);

    @NotNull
    MusicBrainzReleaseBrowseRequest withRecordingId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseBrowseRequest withReleaseGroup(@NotNull ReleaseGroup releaseGroup);

    @NotNull
    MusicBrainzReleaseBrowseRequest withReleaseGroupId(@NotNull UUID id);

}

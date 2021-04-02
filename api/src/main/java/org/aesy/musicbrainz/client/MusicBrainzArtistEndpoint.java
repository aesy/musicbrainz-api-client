package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.*;
import org.apache.lucene.search.Query;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzArtistEndpoint {

    @NotNull
    MusicBrainzArtistSearchRequest query();

    @NotNull
    MusicBrainzArtistSearchRequest query(Query query);

    @NotNull
    MusicBrainzArtistLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistBrowseRequest withArea(@NotNull DefAreaElementInner area);

    @NotNull
    MusicBrainzArtistBrowseRequest withAreaId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzArtistBrowseRequest withCollectionId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistBrowseRequest withRecording(@NotNull Recording recording);

    @NotNull
    MusicBrainzArtistBrowseRequest withRecordingId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistBrowseRequest withRelease(@NotNull Release release);

    @NotNull
    MusicBrainzArtistBrowseRequest withReleaseId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistBrowseRequest withReleaseGroup(@NotNull ReleaseGroup releaseGroup);

    @NotNull
    MusicBrainzArtistBrowseRequest withReleaseGroupId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistBrowseRequest withWork(@NotNull Work work);

    @NotNull
    MusicBrainzArtistBrowseRequest withWorkId(@NotNull UUID id);

}

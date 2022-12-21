package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Artist;
import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.Release;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzReleaseGroupEndpoint {

    @NotNull
    MusicBrainzReleaseGroupLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest withArtist(@NotNull Artist artist);

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest withArtistId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest withCollectionId(@NotNull UUID id);

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest withRelease(@NotNull Release release);

    @NotNull
    MusicBrainzReleaseGroupBrowseRequest withReleaseId(@NotNull UUID id);

}

package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Artist;
import io.aesy.musicbrainz.entity.Collection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzWorkEndpoint {

    @NotNull
    MusicBrainzWorkLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzWorkBrowseRequest withArtist(@NotNull Artist artist);

    @NotNull
    MusicBrainzWorkBrowseRequest withArtistId(@NotNull UUID id);

    @NotNull
    MusicBrainzWorkBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzWorkBrowseRequest withCollectionId(@NotNull UUID id);

}

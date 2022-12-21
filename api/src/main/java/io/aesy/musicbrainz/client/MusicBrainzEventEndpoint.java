package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Artist;
import io.aesy.musicbrainz.entity.Collection;
import io.aesy.musicbrainz.entity.DefAreaElementInner;
import io.aesy.musicbrainz.entity.Place;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzEventEndpoint {

    @NotNull
    MusicBrainzEventLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzEventBrowseRequest withArea(@NotNull DefAreaElementInner area);

    @NotNull
    MusicBrainzEventBrowseRequest withAreaId(@NotNull UUID id);

    @NotNull
    MusicBrainzEventBrowseRequest withArtist(@NotNull Artist artist);

    @NotNull
    MusicBrainzEventBrowseRequest withArtistId(@NotNull UUID id);

    @NotNull
    MusicBrainzEventBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzEventBrowseRequest withCollectionId(@NotNull UUID id);

    @NotNull
    MusicBrainzEventBrowseRequest withPlace(@NotNull Place place);

    @NotNull
    MusicBrainzEventBrowseRequest withPlaceId(@NotNull UUID id);

}

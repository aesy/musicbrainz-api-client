package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.DefAreaElementInner;
import org.aesy.musicbrainz.entity.Place;
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

package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzArtistSearchRequest
    extends MusicBrainzSearchRequest<Artist> {

    @NotNull
    MusicBrainzArtistSearchRequest withAlias(@NotNull String alias);

    @NotNull
    MusicBrainzArtistSearchRequest withArea(@NotNull String area);

    @NotNull
    MusicBrainzArtistSearchRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzArtistSearchRequest withName(@NotNull String name);

    @NotNull
    MusicBrainzArtistSearchRequest withCountryCode(@NotNull String countryCode);

    @NotNull
    MusicBrainzArtistSearchRequest withGender(@NotNull ArtistGender gender);

    @NotNull
    MusicBrainzArtistSearchRequest withType(@NotNull ArtistType type);

}

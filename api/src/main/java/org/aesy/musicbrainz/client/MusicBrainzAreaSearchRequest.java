package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.jetbrains.annotations.NotNull;

public interface MusicBrainzAreaSearchRequest
    extends MusicBrainzSearchRequest<Artist> {

    @NotNull
    MusicBrainzAreaSearchRequest withAlias(@NotNull String alias);

    @NotNull
    MusicBrainzAreaSearchRequest withId(@NotNull String id);

    @NotNull
    MusicBrainzAreaSearchRequest withName(@NotNull String name);

    @NotNull
    MusicBrainzAreaSearchRequest withCountryCode(@NotNull String countryCode);

    @NotNull
    MusicBrainzAreaSearchRequest withType(@NotNull String type);

}

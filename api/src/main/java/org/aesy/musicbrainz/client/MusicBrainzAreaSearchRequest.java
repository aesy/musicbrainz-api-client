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
    MusicBrainzAreaSearchRequest withBeginDate(@NotNull String begindate);

    @NotNull
    MusicBrainzAreaSearchRequest withComment(@NotNull String comment);

    @NotNull
    MusicBrainzAreaSearchRequest withEndDate(@NotNull String enddate);

    @NotNull
    MusicBrainzAreaSearchRequest withIso(@NotNull String iso);

    @NotNull
    MusicBrainzAreaSearchRequest withSortName(@NotNull String sortname);

    @NotNull
    MusicBrainzAreaSearchRequest withType(@NotNull String type);

}

package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Collection;
import org.apache.lucene.search.Query;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzAreaEndpoint {

    @NotNull
    MusicBrainzAreaSearchRequest query();

    @NotNull
    MusicBrainzAreaSearchRequest query(Query query);

    @NotNull
    MusicBrainzAreaLookupRequest withId(@NotNull UUID id);

    @NotNull
    MusicBrainzAreaBrowseRequest withCollection(@NotNull Collection collection);

    @NotNull
    MusicBrainzAreaBrowseRequest withCollectionId(@NotNull UUID id);

}

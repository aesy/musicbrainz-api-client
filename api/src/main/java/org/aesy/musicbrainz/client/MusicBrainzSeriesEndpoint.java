package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzSeriesEndpoint {

    @NotNull
    MusicBrainzSeriesLookupRequest withId(@NotNull UUID id);

}

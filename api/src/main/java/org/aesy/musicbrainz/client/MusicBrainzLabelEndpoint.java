package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzLabelEndpoint {

    @NotNull
    MusicBrainzLabelLookupRequest withId(@NotNull UUID id);

}

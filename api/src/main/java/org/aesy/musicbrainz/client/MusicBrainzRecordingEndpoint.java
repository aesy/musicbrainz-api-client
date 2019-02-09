package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface MusicBrainzRecordingEndpoint {

    @NotNull
    MusicBrainzRecordingLookupRequest withId(@NotNull UUID id);

}

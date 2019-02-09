package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzRecordingEndpointImpl
    implements MusicBrainzRecordingEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/recording";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzRecordingEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzRecordingLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzRecordingLookupRequestImpl(target, id);
    }

}

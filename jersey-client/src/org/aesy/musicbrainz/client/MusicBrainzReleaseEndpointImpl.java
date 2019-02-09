package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzReleaseEndpointImpl
    implements MusicBrainzReleaseEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/release";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzReleaseEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzReleaseLookupRequestImpl(target, id);
    }

}

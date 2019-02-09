package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzEventEndpointImpl
    implements MusicBrainzEventEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/event";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzEventEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzEventLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzEventLookupRequestImpl(target, id);
    }

}

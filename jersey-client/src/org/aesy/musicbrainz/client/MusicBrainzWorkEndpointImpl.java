package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzWorkEndpointImpl
    implements MusicBrainzWorkEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/work";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzWorkEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzWorkLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzWorkLookupRequestImpl(target, id);
    }

}

package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzAreaEndpointImpl
    implements MusicBrainzAreaEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/area";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzAreaEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzAreaLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzAreaLookupRequestImpl(target, id);
    }

}

package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzUrlEndpointImpl
    implements MusicBrainzUrlEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/url";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzUrlEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzUrlLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzUrlLookupRequestImpl(target, id);
    }

}

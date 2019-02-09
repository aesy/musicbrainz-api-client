package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzLabelEndpointImpl
    implements MusicBrainzLabelEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/label";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzLabelEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzLabelLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzLabelLookupRequestImpl(target, id);
    }

}

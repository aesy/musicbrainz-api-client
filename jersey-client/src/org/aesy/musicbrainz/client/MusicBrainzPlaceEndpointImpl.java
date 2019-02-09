package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzPlaceEndpointImpl
    implements MusicBrainzPlaceEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/place";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzPlaceEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzPlaceLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzPlaceLookupRequestImpl(target, id);
    }

}

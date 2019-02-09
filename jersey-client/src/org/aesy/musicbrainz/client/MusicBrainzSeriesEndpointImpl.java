package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzSeriesEndpointImpl
    implements MusicBrainzSeriesEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/series";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzSeriesEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzSeriesLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzSeriesLookupRequestImpl(target, id);
    }

}

package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzArtistEndpointImpl
    implements MusicBrainzArtistEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/artist";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzArtistEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzArtistLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzArtistLookupRequestImpl(target, id);
    }

}

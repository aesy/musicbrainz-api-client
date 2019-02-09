package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;

/* package-private */ final class MusicBrainzReleaseGroupEndpointImpl
    implements MusicBrainzReleaseGroupEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/release-group";

    @NotNull
    private final WebTarget target;

    /* package-private */ MusicBrainzReleaseGroupEndpointImpl(
        @NotNull WebTarget target
    ) {
        this.target = target.path(ENDPOINT_PATH);
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzReleaseGroupLookupRequestImpl(target, id);
    }

}

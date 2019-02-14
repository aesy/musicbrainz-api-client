package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Collection;
import org.aesy.musicbrainz.entity.DefAreaElementInner;
import org.aesy.musicbrainz.entity.Place;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.WebTarget;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzEventEndpointImpl
    implements MusicBrainzEventEndpoint {

    @NotNull
    private static final String ENDPOINT_PATH = "/event";

    @NotNull
    private final WebTarget target;

    @NotNull
    private final Executor executor;

    /* package-private */ MusicBrainzEventEndpointImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.target = target.path(ENDPOINT_PATH);
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzEventLookupRequest withId(@NotNull UUID id) {
        return new MusicBrainzEventLookupRequestImpl(target, executor, id);
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withArea(@NotNull DefAreaElementInner area) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withAreaId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withArtist(@NotNull Artist artist) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withArtistId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withCollection(@NotNull Collection collection) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withCollectionId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withPlace(@NotNull Place place) {
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    public MusicBrainzEventBrowseRequest withPlaceId(@NotNull UUID id) {
        throw new RuntimeException("Not implemented");
    }

}

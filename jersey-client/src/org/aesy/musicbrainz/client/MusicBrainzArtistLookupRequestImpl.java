package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Artist;
import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzArtistLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<Artist>
    implements MusicBrainzArtistLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    @NotNull
    private final List<String> includes;

    /* package-private */ MusicBrainzArtistLookupRequestImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor,
        @NotNull UUID id
    ) {
        super(executor);

        this.target = target;
        this.id = id;
        this.includes = new ArrayList<>();
    }

    @NotNull
    @Override
    public MusicBrainzArtistLookupRequest include(@NotNull String... includes) {
        Collections.addAll(this.includes, includes);

        return this;
    }

    @NotNull
    @Override
    protected Response sendRequest()
        throws MusicBrainzNetworkException {

        WebTarget target = this.target.path(id.toString());

        if (!includes.isEmpty()) {
            target = target.queryParam("inc", String.join("+", includes));
        }

        Invocation invocation = target
            .request()
            .accept(MediaType.APPLICATION_XML)
            .buildGet();

        try {
            return invocation.invoke();
        } catch (ProcessingException exception) {
            throw new MusicBrainzNetworkException(exception);
        }
    }

    @NotNull
    @Override
    protected Artist mapResponse(@NotNull Response response)
        throws MusicBrainzEntityMappingException {

        Metadata metadata;

        try {
            metadata = response.readEntity(Metadata.class);
        } catch (ProcessingException exception) {
            throw new MusicBrainzEntityMappingException(exception);
        }

        if (metadata == null) {
            throw new MusicBrainzEntityMappingException("Metadata is null");
        }

        Artist artist = metadata.getArtist();

        if (artist == null) {
            throw new MusicBrainzEntityMappingException("Artist is null");
        }

        return artist;
    }

}

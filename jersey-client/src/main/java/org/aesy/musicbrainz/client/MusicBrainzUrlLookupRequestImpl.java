package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.entity.Url;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzUrlLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<Url>
    implements MusicBrainzUrlLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    /* package-private */ MusicBrainzUrlLookupRequestImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor,
        @NotNull UUID id
    ) {
        super(executor);

        this.target = target;
        this.id = id;
    }

    @NotNull
    @Override
    protected Response sendRequest()
        throws MusicBrainzNetworkException {

        Invocation invocation = target
            .path(id.toString())
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
    protected Url mapResponse(@NotNull Response response)
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

        Url url = metadata.getUrl();

        if (url == null) {
            throw new MusicBrainzEntityMappingException("Url is null");
        }

        return url;
    }

}

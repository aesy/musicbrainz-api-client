package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Metadata;
import io.aesy.musicbrainz.entity.Work;
import io.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import io.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzWorkLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<Work>
    implements MusicBrainzWorkLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    /* package-private */ MusicBrainzWorkLookupRequestImpl(
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
    protected Work mapResponse(@NotNull Response response)
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

        Work work = metadata.getWork();

        if (work == null) {
            throw new MusicBrainzEntityMappingException("Work is null");
        }

        return work;
    }

}

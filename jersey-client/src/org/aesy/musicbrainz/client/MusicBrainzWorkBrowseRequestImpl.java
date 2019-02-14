package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.entity.Work;
import org.aesy.musicbrainz.entity.WorkList;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzWorkBrowseRequestImpl
    extends MusicBrainzBrowseRequestImpl<Work>
    implements MusicBrainzWorkBrowseRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final String subQuery;

    @NotNull
    private final UUID id;

    /* package-private */ MusicBrainzWorkBrowseRequestImpl(
        @NotNull WebTarget target,
        @NotNull Executor executor,
        @NotNull String subQuery,
        @NotNull UUID id
    ) {
        super(executor);

        this.target = target;
        this.subQuery = subQuery;
        this.id = id;
    }

    @NotNull
    @Override
    public MusicBrainzWorkBrowseRequest include(@NotNull String... include) {
        // TODO
        throw new RuntimeException("Not implemented");
    }

    @NotNull
    @Override
    protected Response sendRequest(long limit, long offset)
        throws MusicBrainzNetworkException {

        Invocation invocation = target
            .queryParam(subQuery, id.toString())
            .queryParam("limit", limit)
            .queryParam("offset", offset)
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
    protected List<Work> mapResponse(@NotNull Response response)
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

        WorkList workList = metadata.getWorkList();

        if (workList == null) {
            throw new MusicBrainzEntityMappingException("Work list is null");
        }

        List<Work> work = workList.getWork();

        if (work == null) {
            throw new MusicBrainzEntityMappingException("Work list is null");
        }

        return work;
    }

}

package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Label;
import io.aesy.musicbrainz.entity.LabelList;
import io.aesy.musicbrainz.entity.Metadata;
import io.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import io.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzLabelBrowseRequestImpl
    extends MusicBrainzBrowseRequestImpl<Label>
    implements MusicBrainzLabelBrowseRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final String subQuery;

    @NotNull
    private final UUID id;

    /* package-private */ MusicBrainzLabelBrowseRequestImpl(
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
    protected List<Label> mapResponse(@NotNull Response response)
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

        LabelList labelList = metadata.getLabelList();

        if (labelList == null) {
            throw new MusicBrainzEntityMappingException("Label list is null");
        }

        List<Label> label = labelList.getLabel();

        if (label == null) {
            throw new MusicBrainzEntityMappingException("Label list is null");
        }

        return label;
    }

}

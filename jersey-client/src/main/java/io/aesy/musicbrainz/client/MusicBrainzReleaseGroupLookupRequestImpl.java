package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.entity.Metadata;
import io.aesy.musicbrainz.entity.ReleaseGroup;
import io.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import io.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* package-private */ final class MusicBrainzReleaseGroupLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<ReleaseGroup>
    implements MusicBrainzReleaseGroupLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    @NotNull
    private final List<String> includes;

    /* package-private */ MusicBrainzReleaseGroupLookupRequestImpl(
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
    public MusicBrainzReleaseGroupLookupRequest includeArtists() {
        includes.add("artists");

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupLookupRequest includeReleases() {
        includes.add("releases");

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
    protected ReleaseGroup mapResponse(@NotNull Response response)
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

        ReleaseGroup releaseGroup = metadata.getReleaseGroup();

        if (releaseGroup == null) {
            throw new MusicBrainzEntityMappingException("ReleaseGroup is null");
        }

        return releaseGroup;
    }

}

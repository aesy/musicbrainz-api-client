package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.entity.Release;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
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

/* package-private */ final class MusicBrainzReleaseLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<Release>
    implements MusicBrainzReleaseLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    @NotNull
    private final List<String> includes;

    /* package-private */ MusicBrainzReleaseLookupRequestImpl(
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
    public MusicBrainzReleaseLookupRequest includeArtists() {
        includes.add("artists");

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest includeCollections() {
        includes.add("collections");

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest includeLabels() {
        includes.add("labels");

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest includeRecordings() {
        includes.add("recordings");

        return this;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseLookupRequest includeReleaseGroups() {
        includes.add("release-groups");

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
    protected Release mapResponse(@NotNull Response response)
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

        Release release = metadata.getRelease();

        if (release == null) {
            throw new MusicBrainzEntityMappingException("Release is null");
        }

        return release;
    }

}

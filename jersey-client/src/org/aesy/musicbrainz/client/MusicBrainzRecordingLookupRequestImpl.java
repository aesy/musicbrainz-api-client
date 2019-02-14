package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.entity.Recording;
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

/* package-private */ final class MusicBrainzRecordingLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<Recording>
    implements MusicBrainzRecordingLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    /* package-private */ MusicBrainzRecordingLookupRequestImpl(
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
    protected Recording mapResponse(@NotNull Response response)
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

        Recording recording = metadata.getRecording();

        if (recording == null) {
            throw new MusicBrainzEntityMappingException("Recording is null");
        }

        return recording;
    }

}

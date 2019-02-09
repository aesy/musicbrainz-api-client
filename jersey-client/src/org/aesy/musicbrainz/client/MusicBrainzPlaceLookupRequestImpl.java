package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.entity.Metadata;
import org.aesy.musicbrainz.entity.Place;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/* package-private */ final class MusicBrainzPlaceLookupRequestImpl
    extends MusicBrainzLookupRequestImpl<Place>
    implements MusicBrainzPlaceLookupRequest {

    @NotNull
    private final WebTarget target;

    @NotNull
    private final UUID id;

    /* package-private */ MusicBrainzPlaceLookupRequestImpl(
        @NotNull WebTarget target,
        @NotNull UUID id
    ) {
        this.target = target;
        this.id = id;
    }

    @NotNull
    @Override
    protected Response sendRequest()
        throws MusicBrainzNetworkException {

        WebTarget target = this.target.path(id.toString());

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
    protected Place mapResponse(@NotNull Response response)
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

        Place place = metadata.getPlace();

        if (place == null) {
            throw new MusicBrainzEntityMappingException("Place is null");
        }

        return place;
    }

}

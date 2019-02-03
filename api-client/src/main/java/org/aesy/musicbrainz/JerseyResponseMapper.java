package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

/* package-private */ class JerseyResponseMapper<T>
    implements MusicBrainzResponseEntityMapper<Response, T> {

    @NotNull
    private final Class<T> entityType;

    /* package-private */ JerseyResponseMapper(
        @NotNull Class<T> entityType
    ) {
        this.entityType = entityType;
    }

    @Nullable
    @Override
    public T map(@Nullable Response response)
        throws MusicBrainzEntityMappingException {

        if (response == null) {
            return null;
        }

        try {
            return response.readEntity(entityType);
        } catch (ProcessingException exception) {
            throw new MusicBrainzEntityMappingException(exception);
        }
    }

}

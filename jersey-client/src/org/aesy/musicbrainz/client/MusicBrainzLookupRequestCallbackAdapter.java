package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

/**
 * Convenience adapter class which implements {@code MusicBrainzLookupRequest.Callback} with empty
 * method bodies.
 *
 * @param <T> The entity type of the response
 * @see MusicBrainzLookupRequest.Callback
 */
public abstract class MusicBrainzLookupRequestCallbackAdapter<T>
    implements MusicBrainzLookupRequest.Callback<T> {

    @Override
    public void onSuccess(@NotNull T entity) {
        // Intentionally left empty
    }

    @Override
    public void onFailure(int statusCode, @NotNull String message) {
        // Intentionally left empty
    }

    @Override
    public void onError(@NotNull MusicBrainzException exception) {
        // Intentionally left empty
    }

}

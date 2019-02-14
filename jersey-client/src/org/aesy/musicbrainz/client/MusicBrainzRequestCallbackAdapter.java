package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

/**
 * Convenience adapter class which implements {@code MusicBrainzRequestCallback} with empty
 * method bodies.
 *
 * @param <T> The entity type of the response
 * @see MusicBrainzRequestCallback
 */
public abstract class MusicBrainzRequestCallbackAdapter<T>
    implements MusicBrainzRequestCallback<T> {

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

package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

/**
 * Request callbacks.
 *
 * @param <T> The response entity type
 */
public interface MusicBrainzRequestCallback<T> {

    void onSuccess(@NotNull T entity);

    void onFailure(int statusCode, @NotNull String message);

    void onError(@NotNull MusicBrainzException exception);

}

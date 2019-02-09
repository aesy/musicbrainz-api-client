package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Represents an unsubmitted musicbrainz API lookup request.
 *
 * @param <T> The entity type of the request.
 */
public interface MusicBrainzLookupRequest<T> {

    /**
     * Executes the request synchronously.
     *
     * @return The response
     */
    @NotNull
    MusicBrainzResponse<T> execute();

    /**
     * Executes the request asynchronously.
     *
     * @return A future of a response
     */
    @NotNull
    CompletableFuture<MusicBrainzResponse<T>> executeAsync();

    /**
     * Executes the request asynchronously and uses the given callbacks when the request is
     * finished.
     *
     * @param callback An object containing callback functions
     * @see MusicBrainzLookupRequest.Callback
     */
    void executeAsync(@NotNull Callback<T> callback);

    /**
     * Request callbacks.
     *
     * @param <T> The response entity type
     */
    interface Callback<T> {

        void onSuccess(@NotNull T entity);

        void onFailure(int statusCode, @NotNull String message);

        void onError(@NotNull MusicBrainzException exception);

    }

}

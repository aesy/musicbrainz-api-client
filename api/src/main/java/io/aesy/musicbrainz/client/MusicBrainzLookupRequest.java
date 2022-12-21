package io.aesy.musicbrainz.client;

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
    MusicBrainzResponse<T> lookup();

    /**
     * Executes the request asynchronously.
     *
     * @return A future of a response
     */
    @NotNull
    CompletableFuture<MusicBrainzResponse<T>> lookupAsync();

    /**
     * Executes the request asynchronously and uses the given callbacks when the request is
     * finished.
     *
     * @param callback An object containing callback functions
     * @see MusicBrainzRequestCallback
     */
    void lookupAsync(@NotNull MusicBrainzRequestCallback<T> callback);

}

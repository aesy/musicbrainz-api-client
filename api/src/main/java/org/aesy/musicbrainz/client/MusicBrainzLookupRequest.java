package org.aesy.musicbrainz.client;

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

        /**
         * Called once after a successful request.
         *
         * <p>
         * A request is considered successful when no exceptions has occured and the response status
         * code is within range [200, 300).
         * </p>
         *
         * @param response The response, whose entity value is guaranteed to be present
         */
        void onSuccess(@NotNull MusicBrainzResponse.Success<T> response);

        /**
         * Called once after an unsuccessful request.
         *
         * <p>
         * A request is considered unsuccessful when no exceptions occured, but the response status
         * code is outside the range [200, 300).
         * </p>
         *
         * @param response The response, whose entity value is guaranteed to not be present
         */
        void onFailure(@NotNull MusicBrainzResponse.Failure<T> response);

        /**
         * Called once after an error occurred during a request.
         *
         * <p>
         * An error is something unexpected, such as a missing internet connection or unparsable
         * response body.
         * </p>
         *
         * <p>
         * The exception associated with this response should be a subclass of {@code
         * MusicBrainzException}. If that's not the case, then it's considered a bug. Please submit
         * the bug to our issue tracker.
         * </p>
         *
         * @param response The response, whose entity value is guaranteed to not be present
         */
        void onError(@NotNull MusicBrainzResponse.Error<T> response);

    }

}

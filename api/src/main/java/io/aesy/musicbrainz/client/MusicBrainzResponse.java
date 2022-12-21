package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * Represents the result of a request to a musicbrainz API.
 *
 * @param <T> The entity type of the response.
 */
public interface MusicBrainzResponse<T> {

    /**
     * Gets the status code associated with the response.
     *
     * @return The status code
     */
    int getStatusCode();

    /**
     * Checks whether the response contains any errors or unexpected status codes.
     *
     * @return {@code true} if no errors occured and status code is in range [200, 300)
     */
    boolean isSuccessful();

    /**
     * Returns the response entity value if present, otherwise throws a {@code
     * NoSuchElementException}.
     *
     * <p>
     * The response entity value is present if MusicBrainzResponse#isSuccessful() returns {@code
     * true}.
     * </p>
     *
     * @return The non-null response entity value
     * @throws NoSuchElementException If there is no response entity present
     * @see MusicBrainzResponse#isSuccessful()
     */
    @NotNull
    T get()
        throws NoSuchElementException;

    /**
     * Returns the response entity value if present, otherwise returns the given object.
     *
     * <p>
     * The response entity value is present if MusicBrainzResponse#isSuccessful() returns {@code
     * true}.
     * </p>
     *
     * @param other The object returned if no response entity is present
     * @return The non-null response entity value, or the given object if no response entity is
     *     present
     * @see MusicBrainzResponse#isSuccessful()
     */
    @NotNull
    T getOr(@NotNull T other);

    /**
     * Returns the response entity value if present, otherwise returns an object supplied by the
     * given supplier.
     *
     * <p>
     * The response entity value is present if MusicBrainzResponse#isSuccessful() returns {@code
     * true}.
     * </p>
     *
     * @param supplier The supplier used to get an object that's returned if no response entity
     *     is present
     * @return The non-null response entity value, or an object supplied by the given supplier if no
     *     response entity is present
     * @see MusicBrainzResponse#isSuccessful()
     */
    @NotNull
    T getOr(@NotNull Supplier<T> supplier);

    /**
     * Returns the response entity value if present, otherwise returns {@code null}.
     *
     * <p>
     * The response entity value is present if MusicBrainzResponse#isSuccessful() returns {@code
     * true}.
     * </p>
     *
     * @return The response entity value, or null if not present
     * @see MusicBrainzResponse#isSuccessful()
     */
    @Nullable
    T getOrNull();

    /**
     * Returns the response entity value if present, otherwise throws the given throwable.
     *
     * <p>
     * The response entity value is present if MusicBrainzResponse#isSuccessful() returns {@code
     * true}.
     * </p>
     *
     * @param <E> The type of throwable thrown by this method is no response entity is present
     * @param throwable The throwable thrown if no response entity is present
     * @return The non-null response entity value
     * @throws E If there is no response entity present
     * @see MusicBrainzResponse#isSuccessful()
     */
    @NotNull
    <E extends Throwable> T getOrThrow(@NotNull E throwable)
        throws E;

    /**
     * Returns the response entity value if present, otherwise throws the a throwable supplied by
     * the given supplier.
     *
     * <p>
     * The response entity value is present if MusicBrainzResponse#isSuccessful() returns {@code
     * true}.
     * </p>
     *
     * @param <E> The type of throwable supplied by the given supplier
     * @param supplier The supplier used to get a throwable to throw if no response entity is
     *     present
     * @return The non-null response entity value
     * @throws E If there is no response entity present
     * @see MusicBrainzResponse#isSuccessful()
     */
    @NotNull
    <E extends Throwable> T getOrThrow(@NotNull Supplier<E> supplier)
        throws E;

    /**
     * Represents the result of a successful request to a musicbrainz API.
     *
     * <p>
     * The response entity value is guaranteed to be present, meaning methods such as
     * MusicBrainzResponse#getOrNull, MusicBrainzResponse#getOrThrow and MusicBrainzResponse#get
     * will not throw any exceptions or return any {@code null} values.
     * </p>
     *
     * @param <T> The entity type of the response
     */
    interface Success<T>
        extends MusicBrainzResponse<T> {}

    /**
     * Represents the result of a request to a musicbrainz API that received a response, but with an
     * unexpected status code.
     *
     * <p>
     * The response entity value is guaranteed to not be present, meaning methods such as
     * MusicBrainzResponse#getOrNull, MusicBrainzResponse#getOrThrow and MusicBrainzResponse#get
     * will either throw an exception or return {@code null}.
     * </p>
     *
     * @param <T> The entity type of the response
     */
    interface Failure<T>
        extends MusicBrainzResponse<T> {

        /**
         * Gets a message describing the failure.
         *
         * @return A message about the failure. May return an empty string if no message is
         *     available
         */
        @NotNull
        String getMessage();

    }

    /**
     * Represents the result of a request to a musicbrainz API that either never received a
     * response, or failed to process the response.
     *
     * <p>
     * The response entity value is guaranteed to not be present, meaning methods such as
     * MusicBrainzResponse#getOrNull, MusicBrainzResponse#getOrThrow and MusicBrainzResponse#get
     * will either throw an exception or return {@code null}.
     * </p>
     *
     * @param <T> The entity type of the response
     */
    interface Error<T>
        extends MusicBrainzResponse<T> {

        /**
         * Gets the exception associated with this error response.
         *
         * @return An exception
         */
        @NotNull
        MusicBrainzException getException();

    }

}

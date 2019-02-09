package org.aesy.musicbrainz.exception;

import org.jetbrains.annotations.NotNull;

/**
 * {@code MusicBrainzException} is the super class used as base for all exceptions used in this
 * module.
 */
public class MusicBrainzException
    extends Exception {

    /**
     * Constructs a new exception with the given detail message.
     *
     * @param message The detail message
     * @see Exception#Exception(String)
     */
    public MusicBrainzException(@NotNull String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the given detail message and cause.
     *
     * @param message The detail message
     * @param cause The cause
     * @see Exception#Exception(String, Throwable)
     */
    public MusicBrainzException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the given cause.
     *
     * @param cause The cause
     * @see Exception#Exception(Throwable)
     */
    public MusicBrainzException(@NotNull Throwable cause) {
        super(cause);
    }

}

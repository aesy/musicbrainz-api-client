package org.easy.musicbrainz.exception;

public class MusicBrainzException extends RuntimeException {
    public MusicBrainzException() {}

    public MusicBrainzException(String message) {
        super(message);
    }

    public MusicBrainzException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicBrainzException(Throwable cause) {
        super(cause);
    }
}

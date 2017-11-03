package org.easy.musicbrainz.exception;

public class MusicBrainzNetworkException extends MusicBrainzException {
    public MusicBrainzNetworkException() {}

    public MusicBrainzNetworkException(String message) {
        super(message);
    }

    public MusicBrainzNetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicBrainzNetworkException(Throwable cause) {
        super(cause);
    }
}

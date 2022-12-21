package io.aesy.musicbrainz.exception;

public class MusicBrainzClientException
    extends MusicBrainzException {

    public MusicBrainzClientException(String message) {
        super(message);
    }

    public MusicBrainzClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicBrainzClientException(Throwable cause) {
        super(cause);
    }

}

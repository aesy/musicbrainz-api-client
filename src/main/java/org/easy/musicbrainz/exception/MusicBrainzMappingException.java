package org.easy.musicbrainz.exception;

public class MusicBrainzMappingException extends MusicBrainzException {
    public MusicBrainzMappingException() {}

    public MusicBrainzMappingException(String message) {
        super(message);
    }

    public MusicBrainzMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicBrainzMappingException(Throwable cause) {
        super(cause);
    }
}

package org.aesy.musicbrainz.exception;

public class MusicBrainzEntityMappingException
    extends MusicBrainzException {

    public MusicBrainzEntityMappingException(String message) {
        super(message);
    }

    public MusicBrainzEntityMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MusicBrainzEntityMappingException(Throwable cause) {
        super(cause);
    }

}

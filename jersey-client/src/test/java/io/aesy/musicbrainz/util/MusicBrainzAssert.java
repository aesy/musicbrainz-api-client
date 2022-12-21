package io.aesy.musicbrainz.util;

import io.aesy.musicbrainz.client.MusicBrainzResponse;
import io.aesy.musicbrainz.exception.MusicBrainzException;
import org.assertj.core.api.AbstractAssert;

public class MusicBrainzAssert<T>
    extends AbstractAssert<MusicBrainzAssert<T>, MusicBrainzResponse<T>> {

    private MusicBrainzAssert(MusicBrainzResponse<T> response) {
        super(response, MusicBrainzAssert.class);
    }

    public static <T> MusicBrainzAssert<T> assertThat(MusicBrainzResponse<T> response) {
        return new MusicBrainzAssert<>(response);
    }

    public MusicBrainzAssert<T> isSuccessful() {
        if (actual.isSuccessful()) {
            return myself;
        }

        int statusCode = actual.getStatusCode();

        if (actual instanceof MusicBrainzResponse.Error) {
            MusicBrainzException exception = ((MusicBrainzResponse.Error<T>) actual).getException();
            failWithMessage("Expected request to be successful, but failed with error <%s>", exception);
        } else if (actual instanceof MusicBrainzResponse.Failure) {
            String message = ((MusicBrainzResponse.Failure<T>) actual).getMessage();
            failWithMessage("Expected request to be successful, but failed with message '<%s>: <%s>'", statusCode, message);
        }

        failWithMessage("Expected request to be successful, but failed with status code <%s>", statusCode);

        return myself;
    }

}

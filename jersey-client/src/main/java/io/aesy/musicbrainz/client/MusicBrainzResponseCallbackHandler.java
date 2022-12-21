package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.exception.MusicBrainzClientException;
import io.aesy.musicbrainz.exception.MusicBrainzException;

import java.util.function.BiFunction;

/* package-private */ final class MusicBrainzResponseCallbackHandler<T>
    implements BiFunction<MusicBrainzResponse<T>, Throwable, MusicBrainzResponse<T>> {

    private final MusicBrainzRequestCallback<T> callback;

    /* package-private */ MusicBrainzResponseCallbackHandler(
        MusicBrainzRequestCallback<T> callback
    ) {
        this.callback = callback;
    }

    @Override
    public MusicBrainzResponse<T> apply(MusicBrainzResponse<T> response, Throwable throwable) {
        if (throwable != null) {
            MusicBrainzException exception = new MusicBrainzClientException(throwable);

            callback.onError(exception);

            return null;
        }

        if (response instanceof MusicBrainzResponse.Success) {
            T entity = response.get();

            callback.onSuccess(entity);
        } else if (response instanceof MusicBrainzResponse.Failure) {
            MusicBrainzResponse.Failure failure = (MusicBrainzResponse.Failure) response;
            int statusCode = failure.getStatusCode();
            String message = failure.getMessage();

            callback.onFailure(statusCode, message);
        } else if (response instanceof MusicBrainzResponse.Error) {
            MusicBrainzResponse.Error error = (MusicBrainzResponse.Error) response;
            MusicBrainzException exception = error.getException();

            callback.onError(exception);
        } else {
            String message = "Unknown response type " + response.getClass();
            MusicBrainzException exception = new MusicBrainzClientException(message);

            callback.onError(exception);
        }

        return response;
    }

}

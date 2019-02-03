package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzClientException;
import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/* package-private */ class MusicBrainzRequestCallbackInvoker<T>
    implements Consumer<MusicBrainzResponse<T>> {

    @NotNull
    private final MusicBrainzRequest.Callback<T> callback;

    /* package-private */ MusicBrainzRequestCallbackInvoker(
        @NotNull MusicBrainzRequest.Callback<T> callback
    ) {
        this.callback = callback;
    }

    @Override
    public void accept(MusicBrainzResponse<T> response) {
        if (response instanceof MusicBrainzResponse.Success) {
            callback.onSuccess((MusicBrainzResponse.Success<T>) response);
        } else if (response instanceof MusicBrainzResponse.Failure) {
            callback.onFailure((MusicBrainzResponse.Failure<T>) response);
        } else if (response instanceof MusicBrainzResponse.Error) {
            callback.onError((MusicBrainzResponse.Error<T>) response);
        } else {
            String message = "Unknown response type " + response.getClass();
            MusicBrainzException exception = new MusicBrainzClientException(message);
            callback.onError(new BasicMusicBrainzResponse.Error<>(exception));
        }
    }

}

package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzClientException;
import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/* package-private */ class FailedMusicBrainzRequest<T>
    implements MusicBrainzRequest<T> {

    @NotNull
    private final MusicBrainzException exception;

    /* package-private */ FailedMusicBrainzRequest(
        @NotNull MusicBrainzException exception
    ) {
        this.exception = exception;
    }

    /* package-private */ FailedMusicBrainzRequest(
        @NotNull Throwable exception
    ) {
        this.exception = new MusicBrainzClientException(exception);
    }

    @NotNull
    @Override
    public <R> MusicBrainzRequest<R> thenApply(
        @NotNull MusicBrainzResponseEntityMapper<T, R> mapper
    ) {
        return new MappedMusicBrainzRequest<>(this, mapper);
    }

    @NotNull
    @Override
    public MusicBrainzResponse<T> execute() {
        return new BasicMusicBrainzResponse.Error<T>(exception);
    }

    @NotNull
    @Override
    public CompletableFuture<MusicBrainzResponse<T>> executeAsync() {
        return CompletableFuture.completedFuture(execute());
    }

    @Override
    public void executeAsync(@NotNull Callback<T> callback) {
        executeAsync()
            .thenAccept(new MusicBrainzRequestCallbackInvoker<>(callback));
    }

}

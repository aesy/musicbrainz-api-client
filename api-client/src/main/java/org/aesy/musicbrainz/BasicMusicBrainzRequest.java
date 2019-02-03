package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

/* package-private */ class BasicMusicBrainzRequest
    implements MusicBrainzRequest<Response> {

    @NotNull
    private final MusicBrainzRequestInvoker<Response> requestInvoker;

    /* package-private */ BasicMusicBrainzRequest(
        @NotNull MusicBrainzRequestInvoker<Response> requestInvoker
    ) {
        this.requestInvoker = requestInvoker;
    }

    @NotNull
    @Override
    public <R> MusicBrainzRequest<R> thenApply(
        @NotNull MusicBrainzResponseEntityMapper<Response, R> mapper
    ) {
        return new MappedMusicBrainzRequest<>(this, mapper);
    }

    @NotNull
    @Override
    public MusicBrainzResponse<Response> execute() {
        Response response;

        try {
            response = requestInvoker.invoke();
        } catch (MusicBrainzNetworkException exception) {
            return new BasicMusicBrainzResponse.Error<>(exception);
        }

        int statusCode = response.getStatus();

        if (statusCode >= 200 && statusCode < 300) {
            return new BasicMusicBrainzResponse.Success<>(statusCode, response);
        }

        try {
            String message = response.readEntity(String.class);

            return new BasicMusicBrainzResponse.Failure<>(statusCode, message);
        } catch (ProcessingException exception) {
            return new BasicMusicBrainzResponse.Failure<>(statusCode);
        }
    }

    @NotNull
    @Override
    public CompletableFuture<MusicBrainzResponse<Response>> executeAsync() {
        return CompletableFuture.supplyAsync(this::execute);
    }

    @Override
    public void executeAsync(@NotNull Callback<Response> callback) {
        executeAsync()
            .thenAccept(new MusicBrainzRequestCallbackInvoker<>(callback));
    }

}

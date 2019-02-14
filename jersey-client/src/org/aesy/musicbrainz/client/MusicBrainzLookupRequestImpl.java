package org.aesy.musicbrainz.client;

import org.aesy.musicbrainz.exception.MusicBrainzClientException;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.aesy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import java.util.concurrent.*;

/* package-private */ abstract class MusicBrainzLookupRequestImpl<T>
    implements MusicBrainzLookupRequest<T> {

    @NotNull
    private final Executor executor;

    protected MusicBrainzLookupRequestImpl(
        @NotNull Executor executor
    ) {
        this.executor = executor;
    }

    @NotNull
    @Override
    public MusicBrainzResponse<T> lookup() {
        try {
            return lookupAsync().get();
        } catch (InterruptedException | ExecutionException exception) {
            MusicBrainzException wrappedException = new MusicBrainzClientException(exception);

            return new MusicBrainzResponseImpl.Error<>(wrappedException);
        }
    }

    @NotNull
    @Override
    public CompletableFuture<MusicBrainzResponse<T>> lookupAsync() {
        return CompletableFuture.supplyAsync(this::doLookup, executor);
    }

    @Override
    public void lookupAsync(@NotNull MusicBrainzRequestCallback<T> callback) {
        lookupAsync()
            .thenAccept(response -> {
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
            })
            .exceptionally(throwable -> {
                MusicBrainzException exception = new MusicBrainzClientException(throwable);

                callback.onError(exception);

                return null;
            });
    }

    @NotNull
    private MusicBrainzResponse<T> doLookup() {
        Response response;

        try {
            response = sendRequest();
        } catch (MusicBrainzNetworkException exception) {
            return new MusicBrainzResponseImpl.Error<>(exception);
        }

        int statusCode = response.getStatus();

        if (statusCode < 200 || statusCode >= 300) {
            try {
                String message = response.readEntity(String.class);

                return new MusicBrainzResponseImpl.Failure<>(statusCode, message);
            } catch (ProcessingException exception) {
                return new MusicBrainzResponseImpl.Failure<>(statusCode);
            }
        }

        T entity;

        try {
            entity = mapResponse(response);
        } catch (MusicBrainzEntityMappingException exception) {
            return new MusicBrainzResponseImpl.Error<>(exception);
        }

        return new MusicBrainzResponseImpl.Success<>(statusCode, entity);
    }

    @NotNull
    protected abstract Response sendRequest()
        throws MusicBrainzNetworkException;

    @NotNull
    protected abstract T mapResponse(@NotNull Response response)
        throws MusicBrainzEntityMappingException;

}

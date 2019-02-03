package org.aesy.musicbrainz;

import org.aesy.musicbrainz.exception.MusicBrainzClientException;
import org.aesy.musicbrainz.exception.MusicBrainzEntityMappingException;
import org.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

/* package-private */ class MappedMusicBrainzRequest<T, U>
    implements MusicBrainzRequest<U> {

    @NotNull
    private final MusicBrainzRequest<T> request;

    @NotNull
    private final MusicBrainzResponseEntityMapper<T, U> mapper;

    /* package-private */ MappedMusicBrainzRequest(
        @NotNull MusicBrainzRequest<T> request,
        @NotNull MusicBrainzResponseEntityMapper<T, U> mapper
    ) {
        this.request = request;
        this.mapper = mapper;
    }

    @NotNull
    @Override
    public <R> MusicBrainzRequest<R> thenApply(
        @NotNull MusicBrainzResponseEntityMapper<U, R> mapper
    ) {
        return new MappedMusicBrainzRequest<>(this, mapper);
    }

    @NotNull
    @Override
    public MusicBrainzResponse<U> execute() {
        MusicBrainzResponse<T> response = request.execute();

        return mapResponse(response);
    }

    @NotNull
    @Override
    public CompletableFuture<MusicBrainzResponse<U>> executeAsync() {
        return request.executeAsync()
                      .thenApply(this::mapResponse);
    }

    @Override
    public void executeAsync(@NotNull Callback<U> callback) {
        executeAsync()
            .thenAccept(new MusicBrainzRequestCallbackInvoker<>(callback));
    }

    @NotNull
    @SuppressWarnings("unchecked")
    private MusicBrainzResponse<U> mapResponse(@NotNull MusicBrainzResponse<T> response) {
        if (!response.isSuccessful()) {
            return (MusicBrainzResponse<U>) response;
        }

        U entity;

        try {
            entity = mapper.map(response.get());
        } catch (MusicBrainzEntityMappingException exception) {
            return new BasicMusicBrainzResponse.Error<>(exception);
        } catch (NoSuchElementException exception) {
            MusicBrainzException wrappedException = new MusicBrainzClientException(exception);

            return new BasicMusicBrainzResponse.Error<>(wrappedException);
        }

        if (entity == null) {
            String message = "Mapped value is null";
            MusicBrainzException exception = new MusicBrainzEntityMappingException(message);

            return new BasicMusicBrainzResponse.Error<>(exception);
        }

        return new BasicMusicBrainzResponse.Success<>(response.getStatusCode(), entity);
    }

}

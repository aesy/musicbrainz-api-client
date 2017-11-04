package org.easy.musicbrainz.api;

import org.easy.musicbrainz.exception.MusicBrainzMappingException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class MappedApiRequest<T, U> implements ApiRequest<U> {
    @NotNull
    private final ApiRequest<T> request;

    @NotNull
    private final Function<T, U> mapper;

    public MappedApiRequest(@NotNull ApiRequest<T> request, @NotNull Function<T, U> mapper) {
        this.request = request;
        this.mapper = mapper;
    }

    @NotNull
    @Override
    public <R> ApiRequest<R> thenApply(@NotNull Function<U, R> mapper) {
        return new MappedApiRequest<>(this, mapper);
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public ApiResponse<U> execute() {
        ApiResponse<T> response = request.execute();

        if (!response.isSuccessful()) {
            return (ApiResponse<U>) response;
        }

        U entity;

        try {
            entity = mapper.apply(response.getOrNull());
        } catch (MusicBrainzMappingException exception) {
            return new DefaultApiResponse.Error<>(exception);
        }

        return new DefaultApiResponse.Success<>(response.getStatusCode(), entity);
    }

    @NotNull
    @Override
    public CompletableFuture<ApiResponse<U>> executeAsync() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void executeAsync(@NotNull Callback<U> callback) {
        throw new RuntimeException("Not implemented");
    }
}

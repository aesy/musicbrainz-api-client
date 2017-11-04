package org.easy.musicbrainz.api;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface ApiRequest<T> {
    interface Callback<T> {
        void onSuccess(@NotNull DefaultApiResponse.Success<T> response);
        void onFailure(@NotNull DefaultApiResponse.Failure<T> response);
        void onError(@NotNull DefaultApiResponse.Error response);
    }

    @NotNull <R> ApiRequest<R> thenApply(@NotNull Function<T, R> mapper);
    @NotNull ApiResponse<T> execute();
    @NotNull CompletableFuture<ApiResponse<T>> executeAsync();
    void executeAsync(@NotNull Callback<T> callback);
}

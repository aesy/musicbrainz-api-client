package org.easy.musicbrainz.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface ApiRequest<T> {
    interface Callback<T> {
        void onSuccess(@NotNull ApiResponse<T> response);
        void onFailure(@NotNull ApiResponse<T> response);
        void onNetworkError(@NotNull IOException exception);
    }

    @NotNull ApiResponse<T> execute();
    @NotNull CompletableFuture<ApiResponse<T>> executeAsync();
    void executeAsync(@NotNull Callback<T> callback);
}

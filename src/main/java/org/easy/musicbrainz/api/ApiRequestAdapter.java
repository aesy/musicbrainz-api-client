package org.easy.musicbrainz.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class ApiRequestAdapter<T> implements ApiRequest.Callback<T> {
    @Override
    public void onSuccess(@NotNull ApiResponse<T> response) {
        // Intentionally left empty
    }

    @Override
    public void onFailure(@NotNull ApiResponse<T> response) {
        // Intentionally left empty
    }

    @Override
    public void onNetworkError(@NotNull IOException exception) {
        // Intentionally left empty
    }
}

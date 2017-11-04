package org.easy.musicbrainz.api;

import org.jetbrains.annotations.NotNull;

public abstract class ApiRequestCallbackAdapter<T> implements ApiRequest.Callback<T> {
    @Override
    public void onSuccess(@NotNull DefaultApiResponse.Success<T> response) {
        // Intentionally left empty
    }

    @Override
    public void onFailure(@NotNull DefaultApiResponse.Failure<T> response) {
        // Intentionally left empty
    }

    @Override
    public void onError(@NotNull DefaultApiResponse.Error exception) {
        // Intentionally left empty
    }
}

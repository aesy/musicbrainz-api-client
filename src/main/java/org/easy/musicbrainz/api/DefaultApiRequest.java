package org.easy.musicbrainz.api;

import org.easy.musicbrainz.exception.MusicBrainzMappingException;
import org.easy.musicbrainz.exception.MusicBrainzNetworkException;
import org.easy.musicbrainz.http.HttpClient;
import org.easy.musicbrainz.http.HttpRequest;
import org.easy.musicbrainz.http.HttpResponse;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class DefaultApiRequest<T> implements ApiRequest<T> {
    @NotNull
    private final HttpClient httpClient;

    @NotNull
    private final HttpRequest httpRequest;

    private Function<String, T> processor;

    public DefaultApiRequest(@NotNull HttpClient httpClient, @NotNull HttpRequest httpRequest) {
        this.httpClient = httpClient;
        this.httpRequest = httpRequest;
    }

    @NotNull
    @Override
    public ApiResponse<T> execute() {
        HttpResponse response;

        try {
             response = httpClient.execute(httpRequest);
        } catch (MusicBrainzNetworkException exception) {
            return new ApiResponse.Error<>(exception);
        }

        String responseText = response.getBody();

        if (!response.isSuccessful() || responseText == null) {
            return new ApiResponse.Failure<>(response.getStatusCode());
        }

        T artists;

        try {
            artists = processor.apply(responseText);
        } catch (MusicBrainzMappingException exception) {
            return new ApiResponse.Error<>(exception);
        }

        return new ApiResponse.Success<>(response.getStatusCode(), artists);
    }

    @NotNull
    @Override
    public CompletableFuture<ApiResponse<T>> executeAsync() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void executeAsync(@NotNull Callback<T> callback) {
        throw new RuntimeException("Not implemented");
    }
}

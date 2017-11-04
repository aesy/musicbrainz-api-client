package org.easy.musicbrainz.api;

import org.easy.musicbrainz.exception.MusicBrainzNetworkException;
import org.easy.musicbrainz.http.HttpClient;
import org.easy.musicbrainz.http.HttpRequest;
import org.easy.musicbrainz.http.HttpResponse;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class DefaultApiRequest implements ApiRequest<String> {
    @NotNull
    private final HttpClient httpClient;

    @NotNull
    private final HttpRequest httpRequest;

    public DefaultApiRequest(
        @NotNull HttpClient httpClient,
        @NotNull HttpRequest httpRequest
    ) {
        this.httpClient = httpClient;
        this.httpRequest = httpRequest;
    }

    @NotNull
    @Override
    public <R> ApiRequest<R> thenApply(@NotNull Function<String, R> mapper) {
        return new MappedApiRequest<>(this, mapper);
    }

    @NotNull
    @Override
    public ApiResponse<String> execute() {
        HttpResponse response;

        try {
             response = httpClient.execute(httpRequest);
        } catch (MusicBrainzNetworkException exception) {
            return new DefaultApiResponse.Error<>(exception);
        }

        String responseText = response.getBody();

        if (!response.isSuccessful()) {
            return new DefaultApiResponse.Failure<>(response.getStatusCode());
        }

        return new DefaultApiResponse.Success<>(response.getStatusCode(), responseText);
    }

    @NotNull
    @Override
    public CompletableFuture<ApiResponse<String>> executeAsync() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void executeAsync(@NotNull Callback<String> callback) {
        throw new RuntimeException("Not implemented");
    }
}

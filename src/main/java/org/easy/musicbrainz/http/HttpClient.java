package org.easy.musicbrainz.http;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {
    @NotNull HttpResponse execute(HttpRequest request);
    @NotNull CompletableFuture<HttpResponse> executeAsync(HttpRequest request);
}

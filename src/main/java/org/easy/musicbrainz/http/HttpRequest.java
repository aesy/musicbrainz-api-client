package org.easy.musicbrainz.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.Map;

public interface HttpRequest {
    @NotNull URL getUrl();
    @NotNull HttpMethod getMethod();
    @Nullable String getRequestBody();
    @NotNull Map<String, String> getHeaders();
}

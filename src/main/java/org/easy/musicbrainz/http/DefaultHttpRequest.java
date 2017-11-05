package org.easy.musicbrainz.http;

import org.easy.musicbrainz.exception.MusicBrainzNetworkException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DefaultHttpRequest implements HttpRequest {
    public static class Builder {
        private URL url;
        private HttpMethod method;
        private String body;
        private Map<String, String> headers;

        public Builder() {
            headers = new HashMap<>();
        }

        @NotNull
        public Builder setUrl(@NotNull String url) {
            try {
                this.url = new URL(url);
            } catch (MalformedURLException e) {
                throw new MusicBrainzNetworkException("Malformed URL");
            }

            return this;
        }

        @NotNull
        public Builder setUrl(@NotNull URL url) {
            this.url = url;

            return this;
        }

        @NotNull
        public Builder setMethod(@NotNull String method) {
            this.method = HttpMethod.fromString(method);

            return this;
        }

        @NotNull
        public Builder setMethod(@NotNull HttpMethod method) {
            this.method = method;

            return this;
        }

        @NotNull
        public Builder setBody(@NotNull String body) {
            this.body = body;

            return this;
        }

        @NotNull
        public Builder addHeader(@NotNull String key, @NotNull String value) {
            headers.put(key, value);

            return this;
        }

        @NotNull
        public DefaultHttpRequest build() {
            DefaultHttpRequest httpRequest = new DefaultHttpRequest();

            if (url == null || method == null) {
                throw new IllegalStateException("Missing required parameter");
            }

            httpRequest.url = url;
            httpRequest.method = method;
            httpRequest.body = body;
            httpRequest.headers = headers;

            return httpRequest;
        }
    }

    private URL url;
    private HttpMethod method;
    private String body;
    private Map<String, String> headers;

    private DefaultHttpRequest() {}

    @NotNull
    @Override
    public URL getUrl() {
        return url;
    }

    @NotNull
    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Nullable
    @Override
    public String getRequestBody() {
        return body;
    }

    @NotNull
    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }
}

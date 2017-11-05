package org.easy.musicbrainz.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultHttpResponse implements HttpResponse {
    public static class Builder {
        @Nullable
        private Integer statusCode;

        @Nullable
        private String body;

        @NotNull
        public Builder setStatusCode(int statusCode) {
            this.statusCode = statusCode;

            return this;
        }

        @NotNull
        public Builder setBody(@Nullable String body) {
            this.body = body;

            return this;
        }

        @NotNull
        public DefaultHttpResponse build() {
            DefaultHttpResponse httpResponse = new DefaultHttpResponse();

            if (statusCode == null) {
                throw new IllegalStateException("Missing required parameter");
            }

            httpResponse.statusCode = statusCode;
            httpResponse.body = body;

            return httpResponse;
        }
    }

    private int statusCode;

    @Nullable
    private String body;

    private DefaultHttpResponse() {}

    public DefaultHttpResponse(int statusCode, @Nullable String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 300;
    }

    @Nullable
    @Override
    public String getBody() {
        return body;
    }
}

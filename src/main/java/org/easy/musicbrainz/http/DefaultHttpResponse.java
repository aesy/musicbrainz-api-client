package org.easy.musicbrainz.http;

import org.jetbrains.annotations.Nullable;

public class DefaultHttpResponse implements HttpResponse {
    private final int statusCode;
    private final String responseText;

    public DefaultHttpResponse(int statusCode) {
        this(statusCode, null);
    }

    public DefaultHttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.responseText = body;
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
        return responseText;
    }
}

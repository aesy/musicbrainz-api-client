package org.easy.musicbrainz.http;

public enum HttpMethod {
    GET("GET"), POST("POST"), PATCH("PATCH"), PUT("PUT"), DELETE("DELETE"), HEAD("HEAD");

    private final String string;

    HttpMethod(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}

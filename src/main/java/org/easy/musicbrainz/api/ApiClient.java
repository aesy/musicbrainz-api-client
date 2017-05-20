package org.easy.musicbrainz.api;

public abstract class ApiClient implements ApiBase {

    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getUrl() {
        return baseUrl;
    }

}

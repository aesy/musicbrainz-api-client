package org.easy.musicbrainz.endpoint;

import org.easy.musicbrainz.api.ApiBase;

abstract class ApiEndpoint implements ApiBase {

    private final ApiBase base;
    private final String endpointPath;

    public ApiEndpoint(ApiBase base, String subUrl) {
        this.base = base;
        this.endpointPath = subUrl;
    }

    @Override
    public String getUrl() {
        return base.getUrl() + endpointPath;
    }

}

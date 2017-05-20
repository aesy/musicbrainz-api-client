package org.easy.musicbrainz;

import org.easy.musicbrainz.api.ApiClient;
import org.easy.musicbrainz.endpoint.ArtistEndpoint;

public class MusicBrainzApiClient extends ApiClient {

    private static final String API_BASE_URL = "https://musicbrainz.org/ws/2";

    public final ArtistEndpoint artist = new ArtistEndpoint(this);

    private final String userAgent;

    public MusicBrainzApiClient(String userAgent) {
        super(API_BASE_URL);

        this.userAgent = userAgent;
    }

}

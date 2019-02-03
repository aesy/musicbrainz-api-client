package org.aesy.musicbrainz;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

// TODO Ability to change musicbrainz host
// TODO paginated requests
// TODO lucene query building
// TODO add all endpoints

/**
 * TODO.
 */
@SuppressWarnings("VisibilityModifier")
public final class MusicBrainzApiClient {

    /**
     * TODO.
     */
    @NotNull
    public final ArtistRequestBuilder artist;

    /**
     * TODO.
     */
    private MusicBrainzApiClient(WebTarget target) {
        this.artist = new ArtistRequestBuilder(target);
    }

    /**
     * TODO.
     */
    public static MusicBrainzApiClient createWithDefaults() {
        return builder()
            .create();
    }

    /**
     * TODO.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * TODO.
     */
    public static final class Builder {

        private static final String DEFAULT_API_BASE_URL = "https://musicbrainz.org/ws/2";

        @NotNull
        private Client client = ClientBuilder.newClient();

        @NotNull
        private String baseUrl = DEFAULT_API_BASE_URL;

        private Builder() {}

        public void setClient(@NotNull Client client) {
            this.client = client;
        }

        public void setBaseUrl(@NotNull String baseUrl) {
            this.baseUrl = baseUrl;
        }

        /**
         * TODO.
         */
        public MusicBrainzApiClient create() {
            WebTarget target = client.target(baseUrl);

            return new MusicBrainzApiClient(target);
        }

    }

}

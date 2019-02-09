package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public final class MusicBrainzJerseyClient
    implements MusicBrainzClient {

    @NotNull
    private final MusicBrainzArtistEndpoint artist;

    private MusicBrainzJerseyClient(
        @NotNull WebTarget target
    ) {
        this.artist = new MusicBrainzArtistEndpointImpl(target);
    }

    @NotNull
    @Override
    public MusicBrainzArtistEndpoint artist() {
        return artist;
    }

    @NotNull
    public static MusicBrainzClient createWithDefaults() {
        return builder()
            .build();
    }

    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private static final String DEFAULT_API_BASE_URL = "https://musicbrainz.org/ws/2";

        @Nullable
        private String baseUrl;

        private Builder() {}

        @NotNull
        public Builder baseUrl(@NotNull String baseUrl) {
            this.baseUrl = baseUrl;

            return this;
        }

        @NotNull
        public MusicBrainzClient build() {
            Client client = ClientBuilder.newClient();

            String baseUrl;

            if (this.baseUrl != null) {
                baseUrl = this.baseUrl;
            } else {
                baseUrl = DEFAULT_API_BASE_URL;
            }

            WebTarget target = client.target(baseUrl);

            return new MusicBrainzJerseyClient(target);
        }

    }

}

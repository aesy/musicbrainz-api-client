package org.aesy.musicbrainz.client;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import java.net.MalformedURLException;
import java.net.URL;

public final class MusicBrainzJerseyClient
    implements MusicBrainzClient {

    @NotNull
    private static final String APPLICATION_NAME;

    @NotNull
    private static final String VERSION;

    @NotNull
    private static final String URL;

    static {
        APPLICATION_NAME = "musicbrainz-api-client";
        VERSION = "1.0.0-SNAPSHOT";
        URL = "https://github.com/aesy/musicbrainz-api-client";
    }

    @NotNull
    private final MusicBrainzAreaEndpoint area;

    @NotNull
    private final MusicBrainzArtistEndpoint artist;

    @NotNull
    private final MusicBrainzEventEndpoint event;

    @NotNull
    private final MusicBrainzInstrumentEndpoint instrument;

    @NotNull
    private final MusicBrainzLabelEndpoint label;

    @NotNull
    private final MusicBrainzPlaceEndpoint place;

    @NotNull
    private final MusicBrainzRecordingEndpoint recording;

    @NotNull
    private final MusicBrainzReleaseEndpoint release;

    @NotNull
    private final MusicBrainzReleaseGroupEndpoint releaseGroup;

    @NotNull
    private final MusicBrainzSeriesEndpoint series;

    @NotNull
    private final MusicBrainzWorkEndpoint work;

    @NotNull
    private final MusicBrainzUrlEndpoint url;

    private MusicBrainzJerseyClient(
        @NotNull WebTarget target
    ) {
        this.area = new MusicBrainzAreaEndpointImpl(target);
        this.artist = new MusicBrainzArtistEndpointImpl(target);
        this.event = new MusicBrainzEventEndpointImpl(target);
        this.instrument = new MusicBrainzInstrumentEndpointImpl(target);
        this.label = new MusicBrainzLabelEndpointImpl(target);
        this.place = new MusicBrainzPlaceEndpointImpl(target);
        this.recording = new MusicBrainzRecordingEndpointImpl(target);
        this.release = new MusicBrainzReleaseEndpointImpl(target);
        this.releaseGroup = new MusicBrainzReleaseGroupEndpointImpl(target);
        this.series = new MusicBrainzSeriesEndpointImpl(target);
        this.work = new MusicBrainzWorkEndpointImpl(target);
        this.url = new MusicBrainzUrlEndpointImpl(target);
    }

    @NotNull
    @Override
    public MusicBrainzAreaEndpoint area() {
        return area;
    }

    @NotNull
    @Override
    public MusicBrainzArtistEndpoint artist() {
        return artist;
    }

    @NotNull
    @Override
    public MusicBrainzEventEndpoint event() {
        return event;
    }

    @NotNull
    @Override
    public MusicBrainzInstrumentEndpoint instrument() {
        return instrument;
    }

    @NotNull
    @Override
    public MusicBrainzLabelEndpoint label() {
        return label;
    }

    @NotNull
    @Override
    public MusicBrainzPlaceEndpoint place() {
        return place;
    }

    @NotNull
    @Override
    public MusicBrainzRecordingEndpoint recording() {
        return recording;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseEndpoint release() {
        return release;
    }

    @NotNull
    @Override
    public MusicBrainzReleaseGroupEndpoint releaseGroup() {
        return releaseGroup;
    }

    @NotNull
    @Override
    public MusicBrainzSeriesEndpoint series() {
        return series;
    }

    @NotNull
    @Override
    public MusicBrainzWorkEndpoint work() {
        return work;
    }

    @NotNull
    @Override
    public MusicBrainzUrlEndpoint url() {
        return url;
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

        @NotNull
        private static final Client DEFAULT_CLIENT;

        @NotNull
        private static final String DEFAULT_API_BASE_URL;

        @NotNull
        private static final String DEFAULT_USER_AGENT;

        static {
            DEFAULT_CLIENT = ClientBuilder.newClient();
            DEFAULT_API_BASE_URL = "https://musicbrainz.org/ws/2";
            DEFAULT_USER_AGENT = String.format("%s/%s (%s)", APPLICATION_NAME, VERSION, URL);
        }

        @Nullable
        private Client client;

        @Nullable
        private String baseUrl;

        @Nullable
        private String userAgent;

        @Nullable
        private String username;

        @Nullable
        private String password;

        private Builder() {}

        @NotNull
        public Builder client(@NotNull Client client) {
            this.client = client;

            return this;
        }

        @NotNull
        public Builder baseUrl(@NotNull String baseUrl) {
            try {
                new URL(baseUrl);
            } catch (MalformedURLException exception) {
                throw new IllegalArgumentException("Invalid base URL", exception);
            }

            this.baseUrl = baseUrl;

            return this;
        }

        @NotNull
        public Builder userAgent(@NotNull String userAgent) {
            this.userAgent = userAgent;

            return this;
        }

        @NotNull
        public Builder userAgent(@NotNull String applciation, @NotNull String version) {
            this.userAgent = String.format("%s/%s", applciation, version);

            return this;
        }

        @NotNull
        public Builder userAgent(
            @NotNull String applciation,
            @NotNull String version,
            @NotNull String url
        ) {
            this.userAgent = String.format("%s/%s (%s)", applciation, version, url);

            return this;
        }

        @NotNull
        public Builder authentication(@NotNull String username, @NotNull String password) {
            this.username = username;
            this.password = password;

            return this;
        }

        @NotNull
        public MusicBrainzClient build() {
            Client client = getOrDefault(this.client, DEFAULT_CLIENT);
            String userAgent = getOrDefault(this.userAgent, DEFAULT_USER_AGENT);

            client.register(new JerseyClientUserAgentFilter(userAgent));

            if (username != null && password != null) {
                Feature auth = HttpAuthenticationFeature.digest(username, password);

                client.register(auth);
            }

            String baseUrl = getOrDefault(this.baseUrl, DEFAULT_API_BASE_URL);
            WebTarget target = client.target(baseUrl);

            return new MusicBrainzJerseyClient(target);
        }

        @NotNull
        private static <T> T getOrDefault(@Nullable T first, @NotNull T second) {
            if (first != null) {
                return first;
            }

            return second;
        }

    }

}

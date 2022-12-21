package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.concurrent.RateLimitedExecutor;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public final class MusicBrainzJerseyClient
    implements MusicBrainzClient {

    @NotNull
    public static final String MUSICBRAINZ_API_URL;

    @NotNull
    public static final String MUSICBRAINZ_API_TEST_URL;

    @NotNull
    private static final String APPLICATION_NAME;

    @NotNull
    private static final String VERSION;

    @NotNull
    private static final String URL;

    static {
        MUSICBRAINZ_API_URL = "https://musicbrainz.org/ws/2";
        MUSICBRAINZ_API_TEST_URL = "https://test.musicbrainz.org/ws/2";
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
        @NotNull WebTarget target,
        @NotNull Executor executor
    ) {
        this.area = new MusicBrainzAreaEndpointImpl(target, executor);
        this.artist = new MusicBrainzArtistEndpointImpl(target, executor);
        this.event = new MusicBrainzEventEndpointImpl(target, executor);
        this.instrument = new MusicBrainzInstrumentEndpointImpl(target, executor);
        this.label = new MusicBrainzLabelEndpointImpl(target, executor);
        this.place = new MusicBrainzPlaceEndpointImpl(target, executor);
        this.recording = new MusicBrainzRecordingEndpointImpl(target, executor);
        this.release = new MusicBrainzReleaseEndpointImpl(target, executor);
        this.releaseGroup = new MusicBrainzReleaseGroupEndpointImpl(target, executor);
        this.series = new MusicBrainzSeriesEndpointImpl(target, executor);
        this.work = new MusicBrainzWorkEndpointImpl(target, executor);
        this.url = new MusicBrainzUrlEndpointImpl(target, executor);
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
        private static final Supplier<Client> DEFAULT_CLIENT_SUPPLIER;

        @NotNull
        private static final String DEFAULT_API_BASE_URL;

        @NotNull
        private static final String DEFAULT_USER_AGENT;

        @NotNull
        private static final Supplier<Executor> DEFAULT_EXECUTOR_SUPPLIER;

        static {
            DEFAULT_CLIENT_SUPPLIER = ClientBuilder::newClient;
            DEFAULT_API_BASE_URL = MUSICBRAINZ_API_URL;
            DEFAULT_USER_AGENT = String.format("%s/%s (%s)", APPLICATION_NAME, VERSION, URL);
            DEFAULT_EXECUTOR_SUPPLIER = () -> new RateLimitedExecutor(
                1, TimeUnit.SECONDS, "musicbrainz-api-client");
        }

        @Nullable
        private Supplier<Client> clientSupplier;

        @Nullable
        private String baseUrl;

        @Nullable
        private String userAgent;

        @Nullable
        private String username;

        @Nullable
        private String password;

        @Nullable
        private Supplier<Executor> executorSupplier;

        private Builder() {}

        @NotNull
        public Builder client(@NotNull Client client) {
            this.clientSupplier = () -> client;

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
        public Builder userAgent(@NotNull String application, @NotNull String version) {
            this.userAgent = String.format("%s/%s", application, version);

            return this;
        }

        @NotNull
        public Builder userAgent(
            @NotNull String application,
            @NotNull String version,
            @NotNull String url
        ) {
            this.userAgent = String.format("%s/%s (%s)", application, version, url);

            return this;
        }

        @NotNull
        public Builder authentication(@NotNull String username, @NotNull String password) {
            this.username = username;
            this.password = password;

            return this;
        }

        @NotNull
        public Builder executor(@NotNull Executor executor) {
            this.executorSupplier = () -> executor;

            return this;
        }

        @NotNull
        public MusicBrainzClient build() {
            String userAgent = getOrDefault(this.userAgent, DEFAULT_USER_AGENT);
            Client client = getOrDefault(this.clientSupplier, DEFAULT_CLIENT_SUPPLIER).get();

            client.register(new JerseyClientUserAgentFilter(userAgent));

            if (username != null && password != null) {
                Feature auth = HttpAuthenticationFeature.digest(username, password);

                client.register(auth);
            }

            String baseUrl = getOrDefault(this.baseUrl, DEFAULT_API_BASE_URL);
            WebTarget target = client.target(baseUrl);
            Executor executor = getOrDefault(this.executorSupplier, DEFAULT_EXECUTOR_SUPPLIER).get();

            return new MusicBrainzJerseyClient(target, executor);
        }

        @NotNull
        private static <T> T getOrDefault(@Nullable T primary, @NotNull T alternative) {
            if (primary != null) {
                return primary;
            }

            return alternative;
        }

    }

}

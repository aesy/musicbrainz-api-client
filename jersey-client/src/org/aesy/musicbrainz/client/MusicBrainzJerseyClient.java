package org.aesy.musicbrainz.client;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;

public final class MusicBrainzJerseyClient
    implements MusicBrainzClient {

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

        private static final String DEFAULT_API_BASE_URL = "https://musicbrainz.org/ws/2";

        @Nullable
        private String baseUrl;

        @Nullable
        private String username;

        @Nullable
        private String password;

        private Builder() {}

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
        public Builder authentication(@NotNull String username, @NotNull String password) {
            this.username = username;
            this.password = password;

            return this;
        }

        @NotNull
        public MusicBrainzClient build() {
            Client client = ClientBuilder.newClient();

            if (username != null && password != null) {
                Feature auth = HttpAuthenticationFeature.digest(username, password);

                client.register(auth);
            }

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

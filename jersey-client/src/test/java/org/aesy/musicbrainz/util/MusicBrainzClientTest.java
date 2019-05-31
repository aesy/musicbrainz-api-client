package org.aesy.musicbrainz.util;

import io.specto.hoverfly.junit.dsl.RequestMatcherBuilder;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.aesy.musicbrainz.client.MusicBrainzClient;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ExtendWith(HoverflyExtension.class)
public abstract class MusicBrainzClientTest {

    public String resource(String name) {
        URL resource = getClass().getClassLoader().getResource(name);

        if (resource == null) {
            IOException exception = new IOException("Resource not found: '" + name + "'");

            throw new UncheckedIOException(exception);
        }

        Path path;

        try {
            path = Paths.get(resource.toURI());
        } catch (URISyntaxException exception) {
            throw new UncheckedIOException(new IOException(exception));
        }

        try {
            byte[] bytes = Files.readAllBytes(path);

            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    public abstract MusicBrainzClient client();

    public abstract RequestMatcherBuilder get(String path);

}

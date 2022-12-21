package io.aesy.musicbrainz.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public abstract class Resources {

    private Resources() {}

    public static String readString(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found " + path);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

}

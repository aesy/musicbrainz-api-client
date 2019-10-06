package org.aesy.musicbrainz.util;

import org.assertj.core.api.WithAssertions;
import org.assertj.core.api.WithAssumptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public abstract class Test
    implements WithAssertions, WithAssumptions {

    protected String resource(String path) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found " + path);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

}

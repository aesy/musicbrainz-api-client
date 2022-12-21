package io.aesy.musicbrainz.client;

import io.aesy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

/* package-private */ abstract class MusicBrainzResponseImpl<T>
    implements MusicBrainzResponse<T> {

    private final int statusCode;

    protected MusicBrainzResponseImpl(
        int statusCode
    ) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public boolean isSuccessful() {
        return this instanceof MusicBrainzResponse.Success;
    }

    @NotNull
    @Override
    public T get()
        throws NoSuchElementException {

        T value = getOrNull();

        if (value == null) {
            throw new NoSuchElementException();
        }

        return value;
    }

    @NotNull
    @Override
    public T getOr(@NotNull T other) {
        T value = getOrNull();

        if (value == null) {
            return other;
        }

        return value;
    }

    @NotNull
    @Override
    public T getOr(@NotNull Supplier<T> supplier) {
        T value = getOrNull();

        if (value == null) {
            return supplier.get();
        }

        return value;
    }

    @Nullable
    @Override
    public abstract T getOrNull();

    @NotNull
    @Override
    public <E extends Throwable> T getOrThrow(@NotNull E throwable)
        throws E {

        T value = getOrNull();

        if (value == null) {
            throw throwable;
        }

        return value;
    }

    @NotNull
    @Override
    public <E extends Throwable> T getOrThrow(@NotNull Supplier<E> supplier)
        throws E {

        T value = getOrNull();

        if (value == null) {
            throw supplier.get();
        }

        return value;
    }

    /* package-private */ static final class Success<T>
        extends MusicBrainzResponseImpl<T>
        implements MusicBrainzResponse.Success<T> {

        @NotNull
        private final T entity;

        /* package-private */ Success(int statusCode, @NotNull T entity) {
            super(statusCode);

            this.entity = entity;
        }

        @NotNull
        @Override
        public T getOrNull() {
            return entity;
        }

    }

    /* package-private */ static final class Failure<T>
        extends MusicBrainzResponseImpl<T>
        implements MusicBrainzResponse.Failure<T> {

        @NotNull
        private final String message;

        /* package-private */ Failure(int statusCode) {
            this(statusCode, "");
        }

        /* package-private */ Failure(int statusCode, @NotNull String message) {
            super(statusCode);

            this.message = message;
        }

        @NotNull
        @Override
        public String getMessage() {
            return message;
        }

        @Nullable
        @Override
        public T getOrNull() {
            return null;
        }

    }

    /* package-private */ static final class Error<T>
        extends MusicBrainzResponseImpl<T>
        implements MusicBrainzResponse.Error<T> {

        @NotNull
        private final MusicBrainzException exception;

        /* package-private */ Error(@NotNull MusicBrainzException exception) {
            super(-1);

            this.exception = exception;
        }

        @NotNull
        @Override
        public MusicBrainzException getException() {
            return exception;
        }

        @Nullable
        @Override
        public T getOrNull() {
            return null;
        }

    }

}

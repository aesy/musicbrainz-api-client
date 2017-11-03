package org.easy.musicbrainz.api;

import org.easy.musicbrainz.exception.MusicBrainzException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public abstract class ApiResponse<T> {
    public static final class Success<T> extends ApiResponse<T> {
        @Nullable
        private final T entity;

        public Success(int statusCode) {
            this(statusCode, null);
        }

        public Success(int statusCode, @Nullable T entity) {
            super(statusCode);

            this.entity = entity;
        }

        @Nullable
        @Override
        public T getOrNull() {
            return entity;
        }
    }

    public static final class Failure<T> extends ApiResponse<T> {
        public Failure(int statusCode) {
            super(statusCode);
        }

        @Nullable
        @Override
        public T getOrNull() {
            return null;
        }
    }

    public static final class Error<T> extends ApiResponse<T> {
        @NotNull
        private final MusicBrainzException exception;

        public Error(@NotNull MusicBrainzException exception) {
            super(-1);

            this.exception = exception;
        }

        @NotNull
        public MusicBrainzException getException() {
            return exception;
        }

        @Nullable
        @Override
        public T getOrNull() {
            return null;
        }
    }

    private final int statusCode;

    private ApiResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    @NotNull
    public static <T> ApiResponse<T> fromStatusCode(int statusCode) {
        return fromStatusCode(statusCode, null);
    }

    @NotNull
    public static <T> ApiResponse<T> fromStatusCode(int statusCode, @Nullable T entity) {
        if (statusCode >= 200 && statusCode < 300) {
            return new Success<>(statusCode, entity);
        }

        return new Failure<>(statusCode);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccessful() {
        return this instanceof Success;
    }

    @NotNull
    public T get() {
        T value = getOrNull();

        if (value == null) {
            throw new NoSuchElementException();
        }

        return value;
    }

    @NotNull
    public T getOr(@NotNull T other) {
        T value = getOrNull();

        if (value == null) {
            return other;
        }

        return value;
    }

    @NotNull
    public T getOr(@NotNull Supplier<T> supplier) {
        T value = getOrNull();

        if (value == null) {
            return supplier.get();
        }

        return value;
    }

    @Nullable
    public abstract T getOrNull();

    @NotNull
    public <E extends Throwable> T getOrThrow(@NotNull E throwable) throws E {
        T value = getOrNull();

        if (value == null) {
            throw throwable;
        }

        return value;
    }

    @NotNull
    public <E extends Throwable> T getOrThrow(@NotNull Supplier<E> supplier) throws E {
        T value = getOrNull();

        if (value == null) {
            throw supplier.get();
        }

        return value;
    }
}

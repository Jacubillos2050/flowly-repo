package com.project.flowly.shared.result;

import java.util.function.Function;

public sealed interface Result<T> permits Result.Success, Result.Failure {

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> failure(String errorMessage) {
        return new Failure<>(errorMessage);
    }

    boolean isSuccess();
    boolean isFailure();
    T get();
    String getError();

    // Transformaciones
    <R> Result<R> map(Function<T, R> mapper);
    <R> Result<R> flatMap(Function<T, Result<R>> mapper);

    record Success<T>(T value) implements Result<T> {
        @Override public boolean isSuccess() { return true; }
        @Override public boolean isFailure() { return false; }
        @Override public T get() { return value; }
        @Override public String getError() { throw new IllegalStateException("No error in success"); }
        @Override public <R> Result<R> map(Function<T, R> mapper) {
            return Result.success(mapper.apply(value));
        }
        @Override public <R> Result<R> flatMap(Function<T, Result<R>> mapper) {
            return mapper.apply(value);
        }
    }

    record Failure<T>(String error) implements Result<T> {
        @Override public boolean isSuccess() { return false; }
        @Override public boolean isFailure() { return true; }
        @Override public T get() { throw new IllegalStateException("No value in failure: " + error); }
        @Override public String getError() { return error; }
        @Override public <R> Result<R> map(Function<T, R> mapper) {
            return Result.failure(error);
        }
        @Override public <R> Result<R> flatMap(Function<T, Result<R>> mapper) {
            return Result.failure(error);
        }
    }
}
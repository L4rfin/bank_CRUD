package org.example;

public class Result<T> {

    private final T value;
    private final String error;

    private Result(T value, String error) {
        this.value = value;
        this.error = error;
    }

    public static <V> Result<V> success(V value) {
        return new Result<>(value, null);
    }

    public static <V> Result<V> error(String error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public T getValue() {
        return value;
    }

    public String getError() {
        return error;
    }
}

package com.virtual_hex.editor;

public interface SomeVersion<T> extends Comparable<T> {

    Type getType();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    String toString();

    abstract String serializeToString();

    String serializeToStringPretty();

    String serializeToFileString(String fileName, String fileExt);

    enum Type {
        NONE_EXISTANT_VERSION,
        SIMPLE_VERSION,
        COMPLEX_VERSION
    }
}

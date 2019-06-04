package com.mr00anderson.jawe.components;

public class SomeLocation {

    public Type type;
    public String path;

    public SomeLocation() {
    }

    public SomeLocation(Type type, String path) {
        this.type = type;
        this.path = path;
    }

    public enum Type {
        CODE,
        CLASSPATH_FILE,
        FILE,
        URL
    }
}

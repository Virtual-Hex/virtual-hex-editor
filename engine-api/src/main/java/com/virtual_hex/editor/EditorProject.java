package com.virtual_hex.editor;

public interface EditorProject<T> {

    String name();
    SomeVersion<T> version();

    // Assets
    // Libs

    // Can retrieve by url, file, open file, maven repo through eather
}

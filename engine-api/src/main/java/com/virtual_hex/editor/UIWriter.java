package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

import java.nio.file.Path;

public interface UIWriter<T> {
    /**
     * This will write the root instance out
     *
     * @param out to write to
     */
    void write(T out);

    /**
     * This is for the UIWriter to use to call to write subwidgets
     *
     * @param out to write to
     * @param uiComponent something that extends UIComponent
     */
    void write(T out, UIComponent uiComponent);


    void setPluginDataPath(Path path);
    Path getPluginDataPath(String folder);

    // Need a way to pass properties?
    Object setProperty(String key, Object property);
    <T2> T2 getProperty(String key);

    void dispose();
}

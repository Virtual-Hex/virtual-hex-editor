package com.virtual_hex.editor;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to run the editor
 */
public class EditorConfiguration implements Disposable {

    // Used to initially or upon restart configure the editor to a preferred setup
    // Also the Widgets

    /**
     * Will be called if this class needs to self update
     */
    public Refreshable refreshable;

    /**
     * Loaded widgets in this configuration
     */
    public List<ClassHolder> uiComponents;

    /**
     * Loaded UIComponent uiWriters in this configuration
     */
    public List<ClassHolder> uiComponentWriters;

    /**
     * Active instantiated uiComponentWriters
     */
    public List<UIWriter> uiWriters;

    public EditorConfiguration(Refreshable refreshable) {
        this.refreshable = refreshable;
        this.uiComponents = new ArrayList<>();
        this.uiComponentWriters = new ArrayList<>();
        this.uiWriters = new ArrayList<>();
        refresh();
    }

    // Merge configurations
    public EditorConfiguration merge(EditorConfiguration defaultImpl) {
        return null;
    }

    public void dispose() {

        for (UIWriter writer : uiWriters) {
            writer.dispose();
        }

    }

    public void refresh(){
        refreshable.refresh(this);
    }
}

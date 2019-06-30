package com.virtual_hex.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Used to run the editor
 */
public class EditorConfiguration implements Disposable {

    /**
     * Will be called if this class needs to self update
     */
    public List<Refreshable> refreshables;

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
        this.refreshables = new ArrayList<>();
        this.uiComponents = new ArrayList<>();
        this.uiComponentWriters = new ArrayList<>();
        this.uiWriters = new ArrayList<>();

        Collections.addAll(refreshables, refreshable);

        refresh();
    }

    /**
     * Merges another configuration onto this one and returns this one
     *
     * @param defaultImpl
     * @return
     */
    public EditorConfiguration merge(EditorConfiguration defaultImpl) {
        this.refreshables.addAll(defaultImpl.refreshables);
        this.uiComponents.addAll(defaultImpl.uiComponents);
        this.uiComponentWriters.addAll(defaultImpl.uiComponentWriters);
        this.uiWriters.addAll(defaultImpl.uiWriters);
        return this;
    }

    /**
     * Some widgets may use objects that needs to be manually disposed of
     */
    public void dispose() {

        for (UIWriter writer : uiWriters) {
            writer.dispose();
        }

    }

    /**
     * This is used to currently update this classes fields, maybe not the best long term solution.
     *
     * Really just need a way to refresh the data in this class, but generically due to generic class loading
     * and extensibility of the editor
     */
    public void refresh(){
        refreshables.forEach(r -> r.refresh(this));
    }
}

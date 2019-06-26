package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;
import com.virtual_hex.editor.data.UIComponent;

import java.nio.file.Path;
import java.util.UUID;

public class EnhancedPluginManager extends DefaultPluginManager implements UIComponent {

    /**
     * Used for lookups
     */
    public UUID id = UUID.randomUUID();

    public EnhancedPluginManager(Path pluginDataPath, String target) {
        super(pluginDataPath, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractUIComponent uiComponent = (AbstractUIComponent) o;

        return id.equals(uiComponent.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public UUID getId() {
        return id;
    }
}

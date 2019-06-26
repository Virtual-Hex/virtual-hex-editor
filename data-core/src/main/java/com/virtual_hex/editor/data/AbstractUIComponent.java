package com.virtual_hex.editor.data;

import java.util.UUID;

public abstract class AbstractUIComponent implements UIComponent {
    /**
     * Used for lookups
     */
    public UUID id = UUID.randomUUID();

    public AbstractUIComponent() {
    }

    @Override
    public UUID getId() {
        return id;
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
}





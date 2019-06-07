package com.virtual_hex.data;

import java.util.UUID;

public class UIComponent {

    /**
     * Used for lookups
     */
    public UUID id = UUID.randomUUID();

    public UIComponent() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UIComponent uiComponent = (UIComponent) o;

        return id.equals(uiComponent.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}





package com.virtual_hex.data;

import java.util.UUID;

public class UIData {

    /**
     * For now we just need a way to reference drawables in arrays without hashcode overkill on nested tree
     *
     */
    public transient UUID id = UUID.randomUUID();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UIData uiData = (UIData) o;

        return id.equals(uiData.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}





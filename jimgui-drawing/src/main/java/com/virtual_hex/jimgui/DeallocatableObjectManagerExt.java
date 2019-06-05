package com.virtual_hex.jimgui;

import org.ice1000.jimgui.cpp.DeallocatableObject;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

public class DeallocatableObjectManagerExt extends DeallocatableObjectManager {

    public final UUID uuid = UUID.randomUUID();

    public DeallocatableObjectManagerExt(int initialCapacity) {
        super(initialCapacity);
    }

    public DeallocatableObjectManagerExt() {
    }

    public DeallocatableObjectManagerExt(@NotNull Collection<? extends DeallocatableObject> c) {
        super(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeallocatableObjectManagerExt that = (DeallocatableObjectManagerExt) o;

        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}

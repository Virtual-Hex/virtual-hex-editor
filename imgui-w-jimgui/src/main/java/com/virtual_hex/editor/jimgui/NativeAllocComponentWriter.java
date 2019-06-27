package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.UIComponentWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;

public abstract class NativeAllocComponentWriter implements UIComponentWriter<JImGui, DefaultUIWriter> {

    protected DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    @Override
    public void dispose() {
        deallocatableObjectManager.deallocateAll();
    }
}

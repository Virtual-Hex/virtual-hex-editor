package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

public class UIComponentHolder {

    // Hold enough data that we can reload

    public Class<? extends UIComponent> aClass;
    public String fullPath;
    public UIComponent uiComponent;

}

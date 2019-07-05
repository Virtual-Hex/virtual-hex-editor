package com.virtual_hex.editor.data;

import java.util.UUID;

public interface UIComponent {

    UUID NO_UUID = new UUID(0,0);

    UIComponent EMPTY_COMPONENT = () -> NO_UUID;
    UIComponent[] EMPTY_COMPONENTS = new UIComponent[0];

    UUID getId();
}

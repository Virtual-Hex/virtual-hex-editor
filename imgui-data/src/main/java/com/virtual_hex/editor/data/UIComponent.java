package com.virtual_hex.editor.data;

import java.util.UUID;

public interface UIComponent {

    UIComponent EMPTY_COMPONENT = () -> UUID.fromString("");

    UUID getId();
}

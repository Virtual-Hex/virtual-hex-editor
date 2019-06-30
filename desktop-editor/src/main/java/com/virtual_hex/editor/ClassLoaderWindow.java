package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import com.virtual_hex.editor.data.WindowDecorated;

public class ClassLoaderWindow extends WindowDecorated {

    public ClassLoaderWindow() {
    }

    public ClassLoaderWindow(String label, boolean open, int flags) {
        super(label, open, flags);
    }


    public ClassLoaderWindow(String label, boolean open, int flags, UIComponents... components) {
        super(label, open, flags, components);
    }

    public ClassLoaderWindow(String label, boolean open, int flags, UIComponent... components) {
        super(label, open, flags, components);
    }
}

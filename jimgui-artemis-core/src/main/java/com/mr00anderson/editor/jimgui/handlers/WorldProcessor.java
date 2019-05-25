package com.mr00anderson.editor.jimgui.handlers;

import com.artemis.World;

@FunctionalInterface
public interface WorldProcessor {
    void process(Operation operation, World world);

    enum Operation {
        CREATED_NEW,
        PRE_DISPOSE,
        POST_DISPOSE
    }
}

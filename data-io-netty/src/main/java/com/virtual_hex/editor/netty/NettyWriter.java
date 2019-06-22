package com.virtual_hex.editor.netty;

import com.virtual_hex.editor.io.UIComponentWriter;
import io.netty.buffer.ByteBuf;

public interface NettyWriter extends UIComponentWriter<ByteBuf> {
    default void dispose() {
        // Nothing Intended
    }
}

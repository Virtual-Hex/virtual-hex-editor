package com.virtual_hex.editor.netty;


import com.virtual_hex.editor.UIComponentWriter;
import io.netty.buffer.ByteBuf;

public interface NettyWriter extends UIComponentWriter<ByteBuf, NettyWriter> {
    default private void dispose() {
        // Nothing Intended
    }
}

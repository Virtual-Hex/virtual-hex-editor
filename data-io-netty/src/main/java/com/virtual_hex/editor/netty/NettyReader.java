package com.virtual_hex.editor.netty;

import com.virtual_hex.editor.io.UIComponentReader;
import io.netty.buffer.ByteBuf;

/**
 * The byte buffer can be compressed after by tools or a new method to try, byte pattern replacement, table, insert index and pattern map
 */
public interface NettyReader extends UIComponentReader<ByteBuf> {
    default void dispose() {
        // Nothing Intended
    }
}

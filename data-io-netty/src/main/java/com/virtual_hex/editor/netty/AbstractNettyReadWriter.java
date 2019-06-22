package com.virtual_hex.editor.netty;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.netty.utils.UUIDUtils;
import com.virtual_hex.editor.netty.utils.VLQDecoder;
import com.virtual_hex.editor.netty.utils.VLQEncoder;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

/**
 * w - Write
 * r - Read (Safe) / Buffer checks
 * ru - Read  (Unsafe) / No buffer checks, assume it was already completed by programmer
 */
public abstract class AbstractNettyReadWriter implements NettyReader, NettyWriter {

    public static void wString(ByteBuf out, String s) {
        VLQEncoder.encodeString(out, s);
    }

    public static void wInt(ByteBuf out, int i) {
        VLQEncoder.encodeInteger(out, i);
    }

    public static void wId(ByteBuf out, UIComponent uiComponent) {
        UUIDUtils.write(out, uiComponent.id);
    }

    public static String ruString(ByteBuf in) {
        return VLQDecoder.unsafeDecodeString(in);
    }

    public static int ruInt(ByteBuf in) {
        return VLQDecoder.unsafeDecodeInteger(in);
    }

    public static UUID ruId(ByteBuf in) {
        return UUIDUtils.read(in);
    }

    @Override
    public void dispose() {
        // Nothing Intended
    }
}

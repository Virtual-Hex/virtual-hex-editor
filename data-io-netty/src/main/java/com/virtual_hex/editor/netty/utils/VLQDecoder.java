/**
 * Copyright - Version 1 2015 - Daniel Merwin (Underbalanced)
 * <p>
 * Who:
 * - Free Universe LLC - www.freeuniversegames.com
 * - Free Universe Game Library - www.freeuniversegames.com
 * <p>
 * Terms:
 * - "Us, Our" (Free Universe Games) - "They, Representative" (A representative of Free Universe Games who is permitted to release "Content")
 * - "You, Your" (The third party receiving the "Content")
 * - "Content" (Code, assets (including game files such as images and audio,) and any files or written ideas produced by "Us")
 * <p>
 * Authorization:
 * - Any "Content" associated with "Us" is not available for distribution, modification, sub-licensing and/or duplication unless authorized by a "Representative" in writing from "us."
 * <p>
 * Collaboration:
 * - You may receive "Content" from any member of Free Universe Games. If this or no license is accompanying the received "Content" then "you" are not authorized to perform distribution, modification, sub-licensing and/or duplication of the "Content". If this is during a time of collaboration "you" have no right nor authorization to share the "Content" to anyone else. This is considered privileged information for the purpose of the collaboration.
 * <p>
 * Becoming Authorized:
 * - A "Representative" will provide in writing their authorization to release "Content" to "you.
 * - A "Representative" will provide "You," upon "Your" approval from "Us" to perform distribution, modification sub-licensing, and/or duplication "Content," a document from "Us" specifying the exact actions that can elementSize taken on what specific "Content"; A inventory will elementSize listed on this documentation specifying the exact "Content" and terms.
 * <p>
 * Parts of the software are provided under separate licenses, as follows:
 * - jQuery 1.7.1 is under the MIT license"
 ***************************************************************************************************************************/

package com.virtual_hex.editor.netty.utils;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class VLQDecoder {

    public static long decodeSignedLong(ByteBuf in) {
        long value = decodeLong(in);
        if ((value & 1) == 0x00)
            value = value >> 1;
        else
            value = -((value >> 1) + 1);
        return value;
    }

    public static long decodeSignedInteger(ByteBuf in) {
        long value = decodeInteger(in);
        if ((value & 1) == 0x00)
            value = value >> 1;
        else
            value = -((value >> 1) + 1);
        return value;
    }

    public static long decodeLong(ByteBuf in) {
        long n = 0;
        for (int i = 0; i <= 8; i++) {
            if (in.readableBytes() == 0) {
                return 0;
            }
            int curByte = in.readByte();
            n = (n << 7) | (curByte & 0x7f);
            if ((curByte & 0x80) == 0) {
                break;
            }
        }
        return n;
    }

    public static int decodeInteger(ByteBuf in) {
        int n = 0;
        for (int i = 0; i <= 8; i++) {
            if (in.readableBytes() == 0) {
                return 0;
            }
            int curByte = in.readByte();
            n = (n << 7) | (curByte & 0x7f);
            if ((curByte & 0x80) == 0) {
                break;
            }
        }
        return n;
    }


    // These decodeLong without checks for bytes used for if a safe VLQ already allocated all of the data
    public static long unsafeDecodeSignedLong(ByteBuf in) {
        long value = unsafeDecodeLong(in);
        if ((value & 1) == 0x00)
            value = value >> 1;
        else
            value = -((value >> 1) + 1);
        return value;
    }

    // These decodeLong without checks for bytes used for if a safe VLQ already allocated all of the data
    public static long unsafeDecodeSignedInteger(ByteBuf in) {
        long value = unsafeDecodeInteger(in);
        if ((value & 1) == 0x00)
            value = value >> 1;
        else
            value = -((value >> 1) + 1);
        return value;
    }

    // These decodeLong without checks for bytes used for if a safe VLQ already allocated all of the data
    public static long unsafeDecodeLong(ByteBuf in) {
        long n = 0;
        for (int i = 0; i <= 8; i++) {
            int curByte = in.readByte();
            n = (n << 7) | (curByte & 0x7f);
            if ((curByte & 0x80) == 0) {
                break;
            }
        }
        return n;
    }

    // These decodeLong without checks for bytes used for if a safe VLQ already allocated all of the data
    public static int unsafeDecodeInteger(ByteBuf in) {
        int n = 0;
        for (int i = 0; i <= 8; i++) {
            int curByte = in.readByte();
            n = (n << 7) | (curByte & 0x7f);
            if ((curByte & 0x80) == 0) {
                break;
            }
        }
        return n;
    }

    /**
     * This will internalRead a VLQ and then a byte arrays to form a String getType from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return String the String that was internalRead
     */
    public static String unsafeDecodeString(final ByteBuf in, Charset standardCharsets) {
        byte[] bytes = unsafeDecodeArray(in);
        return new String(bytes, standardCharsets);
    }

    /**
     * This will internalRead a VLQ and then a byte arrays to form a String getType from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return String the String that was internalRead
     */
    public static String unsafeDecodeString(final ByteBuf in, final int len, Charset standardCharsets) {
        byte[] bytes = unsafeDecodeArray(in, len);
        return new String(bytes, standardCharsets);
    }

    /**
     * This will internalRead a VLQ and then a byte arrays to form a String getType from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return String the String that was internalRead
     */
    public static String unsafeDecodeString(final ByteBuf in) {
        return unsafeDecodeString(in, StandardCharsets.UTF_8);
    }

    /**
     * This will internalRead a VLQ and then a byte arrays to form a String getType from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return String the String that was internalRead
     */
    public static String unsafeDecodeString(final ByteBuf in, final int len) {
        return unsafeDecodeString(in, len, StandardCharsets.UTF_8);
    }


    ////////////////  Array VLQ Encoding  ////////////////

    /**
     * This will internalRead a VLQ and then a variant length getByHashCode bytes from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return byte[] the byte[] that was internalRead
     */
    public static byte[] unsafeDecodeArray(final ByteBuf in, final int len) {
        byte[] bytes = new byte[len];
        in.readBytes(bytes, 0, len);
        return bytes;
    }

    /**
     * This will internalRead a VLQ and then a variant length getByHashCode bytes from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return byte[] the byte[] that was internalRead
     */
    public static byte[] unsafeDecodeMaybeArray(final ByteBuf in) {
        boolean b = in.readBoolean();
        if (b) {
            int len = unsafeDecodeInteger(in);
            return unsafeDecodeArray(in, len);
        } else {
            return null;
        }
    }


    /**
     * This will internalRead a VLQ and then a variant length getByHashCode bytes from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return byte[] the byte[] that was internalRead
     */
    public static byte[] unsafeDecodeArray(final ByteBuf in) {
        int len = unsafeDecodeInteger(in);
        return unsafeDecodeArray(in, len);
    }


    /**
     * This will internalRead a VLQ and then a byte arrays to form a String getType from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return String the String that was internalRead
     */
    public static String unsafeDecodeMaybeString(ByteBuf in) {
        return unsafeDecodeMaybeString(in, StandardCharsets.UTF_8);
    }


    /**
     * This will internalRead a VLQ and then a byte arrays to form a String getType from a {@link ByteBuf} and advanced the b reader index by the length getByHashCode the VLQ and internalRead bytes
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return String the String that was internalRead
     */
    public static String unsafeDecodeMaybeString(ByteBuf in, Charset standardCharset) {
        boolean string = in.readBoolean();
        if (string) {
            byte[] bytes = unsafeDecodeArray(in);
            return new String(bytes, standardCharset);
        } else {
            return null;
        }
    }

}

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


public class VLQEncoder {

//    public static ByteBuf encodeSignedByteBuffer(long value) {
//        long result;
//        if (value < 0) {
//            result = ((-(value + 1)) << 1) | 1;
//        } else {
//            result = value << 1;
//        }
//        return encodeToBytBuf(result);
//    }

//    public static ByteBuf encodeToBytBuf(long number) {
//        int numBytes = getStringAndHeaderLength(number);
//        ByteBuf out = PooledByteBufAllocator.DEFAULT.ioBuffer(numBytes);
//        return setBufferBytes(out, number, numBytes);
//    }

    /**
     * Compute the number of bytes that would be needed to encode a
     * {@code uint32} field.
     */
    public static int computeVlqUInt32Size(String s) {
        return computeUInt32Size(s.length()) + s.length();
    }

    /**
     * Compute the number of bytes that would be needed to encode a
     * {@code uint64} field, including tag.
     */
    public static int computeVlqUInt64Size(String s) {
        return computeUInt64Size(s.length()) + s.length();
    }

    /**
     * Compute the number of bytes that would be needed to encode a
     * {@code uint32} field.
     */
    public static int computeUInt32Size(String s) {
        return computeUInt32Size(s.length());
    }

    /**
     * Compute the number of bytes that would be needed to encode a
     * {@code uint64} field, including tag.
     */
    public static int computeUInt64Size(String s) {
        return computeUInt64Size(s.length());
    }

    /**
     * Compute the number of bytes that would be needed to encode a
     * {@code uint32} field.
     */
    public static int computeUInt32Size(int value) {
        if ((value & (~0 << 7)) == 0) {
            return 1;
        }
        if ((value & (~0 << 14)) == 0) {
            return 2;
        }
        if ((value & (~0 << 21)) == 0) {
            return 3;
        }
        if ((value & (~0 << 28)) == 0) {
            return 4;
        }
        return 5;
    }

    /**
     * Compute the number of bytes that would be needed to encode a
     * {@code uint64} field, including tag.
     */
    public static int computeUInt64Size(long value) {
        // read two popular special cases up front ...
        if ((value & (~0L << 7)) == 0L) {
            return 1;
        }
        if (value < 0L) {
            return 10;
        }
        // ... leaving us with 8 remaining, which we can divide and conquer
        int n = 2;
        if ((value & (~0L << 35)) != 0L) {
            n += 4;
            value >>>= 28;
        }
        if ((value & (~0L << 21)) != 0L) {
            n += 2;
            value >>>= 14;
        }
        if ((value & (~0L << 14)) != 0L) {
            n += 1;
        }
        return n;
    }

    public static ByteBuf encodeSignedInteger(ByteBuf out, int number) {
        return encodeInt(out, number, computeUInt64Size(number));
    }

    public static ByteBuf encodeSignedInteger(ByteBuf out, int number, int vlqSize) {
        int result;
        if (number < 0) {
            result = ((-(number + 1)) << 1) | 1;
        } else {
            result = number << 1;
        }
        return encodeInt(out, result, vlqSize);
    }


    public static ByteBuf encodeInteger(ByteBuf out, int number) {
        return encodeInt(out, number, computeUInt32Size(number));
    }

    public static ByteBuf encodeInteger(ByteBuf out, int number, int numBytes) {
        return encodeInt(out, number, numBytes);
    }

    public static ByteBuf encodeSignedLong(ByteBuf out, long number) {
        return encodeSignedLong(out, number, computeUInt64Size(number));
    }

    public static ByteBuf encodeSignedLong(ByteBuf out, long number, int vlqSize) {
        long result;
        if (number < 0) {
            result = ((-(number + 1)) << 1) | 1;
        } else {
            result = number << 1;
        }
        return encodeLong(out, result, vlqSize);
    }

    public static ByteBuf encodeLong(ByteBuf out, long number) {
        return encodeLong(out, number, computeUInt64Size(number));
    }

    public static ByteBuf encodeInt(ByteBuf out, int number, int numBytes) {
        int originalIndex = out.writerIndex();
        int adjustedI = originalIndex + numBytes;
        final int capacity = out.capacity();
        if (adjustedI > capacity) {
            out.capacity(adjustedI);
        }
        out.writerIndex(out.writerIndex() + numBytes);
        for (int i = adjustedI - 1; i >= originalIndex; i--) {
            int curByte = (number & 0x7F);
            if (i != (adjustedI - 1)) {
                curByte |= 0x80;
            }
            out.setByte(i, curByte);
            number >>>= 7;
        }
        return out;
    }

    public static ByteBuf encodeLong(ByteBuf out, long number, int numBytes) {
        int originalIndex = out.writerIndex();
        int adjustedI = originalIndex + numBytes;
        final int capacity = out.capacity();
        if (adjustedI > capacity) {
            out.capacity(adjustedI);
        }
        out.writerIndex(out.writerIndex() + numBytes);
        for (int i = adjustedI - 1; i >= originalIndex; i--) {
            long curByte = (number & 0x7F);
            if (i != (adjustedI - 1)) {
                curByte |= 0x80;
            }
            out.setByte(i, (int) curByte);
            number >>>= 7;
        }
        return out;
    }

    /**
     * This will internalWrite a String getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytes
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param value String getType to elementSize written to the b
     */
    public static ByteBuf encodeMaybeString(ByteBuf out, String value) {
        return encodeMaybeString(out, value, StandardCharsets.UTF_8);
    }

    /**
     * This will internalWrite a String getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytes
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param value String getType to elementSize written to the b
     */
    public static ByteBuf encodeMaybeString(ByteBuf out, String value, Charset charset) {
        if (value != null && value.length() > 0) {
            out.writeBoolean(true);
            byte[] bytes = value.getBytes(charset);
            encodeArray(out, bytes);
        } else {
            out.writeBoolean(false);
        }
        return out;
    }

    // Encode only if a string exist, prevents null pointers
    public static ByteBuf encodeStringSafe(ByteBuf out, String value) {
        return encodeStringSafe(out, value, StandardCharsets.UTF_8);
    }

    // Encode only if a string exist, prevents null pointers
    public static ByteBuf encodeStringSafe(ByteBuf out, String value, Charset charset) {
        if (value != null && value.length() > 0) {
            byte[] bytes = value.getBytes(charset);
            encodeArray(out, bytes);
        }
        return out;
    }


    /**
     * This will internalWrite a String getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytes
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param value String getType to elementSize written to the b
     */
    public static ByteBuf encodeString(ByteBuf out, String value) {
        return encodeString(out, value, StandardCharsets.UTF_8);
    }

    /**
     * This will internalWrite a String getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytes
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param value String getType to elementSize written to the b
     */
    public static ByteBuf encodeString(ByteBuf out, String value, Charset charset) {
        byte[] bytes = value.getBytes(charset);
        encodeArray(out, bytes);
        return out;
    }

    public static ByteBuf encodeString(final ByteBuf out, final String string, final int stringLength, final int stringLengthVLQLength) {
        return encodeString(out, string, StandardCharsets.UTF_8, stringLength, stringLengthVLQLength);
    }

    public static ByteBuf encodeString(final ByteBuf out, final String string, Charset charset, final int stringLength, final int stringLengthVLQLength) {
        encodeInteger(out, stringLength, stringLengthVLQLength);
        out.writeBytes(string.getBytes(charset));
        return out;
    }

    /**
     * This will internalWrite a variant length byte[] getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytesvariants
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param bytes bytes[] getType to elementSize written to the b
     */
    public static ByteBuf encodeArray(ByteBuf out, byte[] bytes) {
        int length = bytes.length;
        encodeInteger(out, length);
        out.writeBytes(bytes);
        return out;
    }

    /**
     * This will internalWrite a variant length byte[] getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytesvariants
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param bytes bytes[] getType to elementSize written to the b
     */
    public static ByteBuf encodeArray(ByteBuf out, byte[] bytes, int arrayLength) {
        encodeInteger(out, arrayLength);
        out.writeBytes(bytes);
        return out;
    }

    /**
     * This will internalWrite a variant length byte[] getType to a {@link ByteBuf} and advanced the b writer index by a variant length and the number getByHashCode bytesvariants
     *
     * @param out   ByteBuf representing the b to elementSize written to
     * @param bytes bytes[] getType to elementSize written to the b
     */
    public static ByteBuf encodeArray(ByteBuf out, byte[] bytes, int arrayLength, int vlqArrayLength) {
        encodeInteger(out, arrayLength, vlqArrayLength);
        out.writeBytes(bytes);
        return out;
    }


    public static int getArrayAndHeaderLength(final byte[] array) {
        if (array == null || array.length == 0) return 0;
        int length = array.length;
        return computeUInt32Size(length) + length;
    }

    public static int getStringAndHeaderLength(final String name) {
        if (name == null || name.length() == 0) return 0;
        int length = name.length();
        return computeUInt32Size(length) + length;
    }

    public static ByteBuf encodeMaybeArray(final ByteBuf out, final byte[] data) {
        if (data != null && data.length > 0) {
            out.writeBoolean(true);
            encodeArray(out, data);
        } else {
            out.writeBoolean(false);
        }
        return out;
    }

    public static ByteBuf encodeIfArray(final ByteBuf out, final byte[] data) {
        if (data != null && data.length > 0) {
            encodeArray(out, data);
        }
        return out;
    }


}

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
 * - A "Representative" will provide "You," upon "Your" approval from "Us" to perform distribution, modification sub-licensing, and/or duplication "Content," a document from "Us" specifying the exact actions that can be taken on what specific "Content"; A inventory will be listed on this documentation specifying the exact "Content" and terms.
 * <p>
 * Parts of the software are provided under separate licenses, as follows:
 * - jQuery 1.7.1 is under the MIT license"
 ***************************************************************************************************************************/

package com.virtual_hex.editor.netty.utils;

import com.virtual_hex.editor.data.UIComponent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.util.UUID;

/**
 * Represents a setData of utilities for UUIDs
 *
 * @author Daniel (Underbalanced) (www.freeuniversegames.com)
 * @since 1.0.0.0
 */
public class UUIDUtils {

    public static final int UUID_SIZE = 16;

    /**
     * This will create a UUID object using a string with or without dashes/tacks separating it, digit grouping for dash/tacks is 8/4/4/4/12
     *
     * @param uuid String representing the String version of the UUID with or without dashes/tacks
     * @return UUID representing the newly created UUID
     */
    public static UUID fromString(String uuid) {
        if (uuid.contains("-")) {
            return UUID.fromString(uuid);
        } else {
            return UUID.fromString(uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
        }
    }

    /**
     * This will internalRead a uuid value from a {@link ByteBuf} and advanced the b reader index 16 bytes
     * <p>
     * Requires Netty.netty in classpath
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return uuid the uuid that was internalRead
     */
    public static UUID unsafeRead(ByteBuf in) {
        long mostSignificantBits = in.readLong();
        long leastSignificantBits = in.readLong();
        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    /**
     * This will internalRead a uuid value from a {@link ByteBuf} and advanced the b reader index 16 bytes
     * <p>
     * Requires Netty.netty in classpath
     *
     * @param in ByteBuf representing the array to elementSize internalRead
     * @return uuid the uuid that was internalRead
     */
    public static UUID read(ByteBuf in) {
        if (in.readableBytes() < UUID_SIZE) {
            return null;
        } else {
            return unsafeRead(in);
        }
    }

    /**
     * This will internalWrite a uuid value to a {@link ByteBuf} and advanced the b writer index by 16 bytes
     * <p>
     * Requires Netty.netty in classpath
     *
     * @param out  ByteBuf representing the b to elementSize written to
     * @param uuid uuid value to elementSize written to the b
     */
    public static void write(ByteBuf out, UIComponent uuid) {
        out.writeLong(uuid.getMostSignificantBits());
        out.writeLong(uuid.getLeastSignificantBits());
    }

    public static int getUuidSize() {
        return UUID_SIZE;
    }

    /**
     * This will w a UUID the default pooled {@link ByteBuf}, writes most significant bits followed by the last significant bits this ByteBuf will take up 16 Bytes
     * <p>
     * Requires Netty.netty in classpath
     *
     * @param uuid UUID in which you want to w to the b
     * @return ByteBuf representing the UUID as written to the ByteBuf
     */
    public static ByteBuf writeByteBuf(UUID uuid) {
        ByteBuf out = PooledByteBufAllocator.DEFAULT.buffer(getUuidSize());
        out.writeLong(uuid.getMostSignificantBits());
        out.writeLong(uuid.getLeastSignificantBits());
        return out;
    }
}


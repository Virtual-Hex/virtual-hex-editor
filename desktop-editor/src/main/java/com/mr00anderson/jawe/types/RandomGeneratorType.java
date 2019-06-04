package com.mr00anderson.jawe.types;

import it.unimi.dsi.util.XoShiRo256PlusRandom;
import it.unimi.dsi.util.XoShiRo256StarStarRandom;

import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public enum RandomGeneratorType {

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
     */
    JDK,

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadLocalRandom.html
     */
    JDK_THREAD_LOCAL,

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/util/SplittableRandom.html
     */
    JDK_SPLITTABLE,

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html
     */
    JDK_SECURE,

    /**
     * Best for floating point numbers.
     *
     * http://dsiutils.di.unimi.it/docs/it/unimi/dsi/util/XoShiRo256PlusRandom.html
     */
    XO_SHI_RO_256_PLUS_RANDOM,


    /**
     * Best for integer numbers
     *
     * http://dsiutils.di.unimi.it/docs/it/unimi/dsi/util/XoShiRo256StarStarRandom.html#XoShiRo256StarStarRandom--
     */
    XO_SHI_RO_256_STAR_STAR_RANDOM

    ;


    public <T> T getnewInstance(){
        switch (this) {
            case JDK: return (T) new Random();
            case JDK_THREAD_LOCAL: return (T) ThreadLocalRandom.current();
            case JDK_SPLITTABLE: return (T)  new SplittableRandom();
            case JDK_SECURE: return (T) new SecureRandom();
            case XO_SHI_RO_256_PLUS_RANDOM: return (T) new XoShiRo256PlusRandom();
            case XO_SHI_RO_256_STAR_STAR_RANDOM: return (T) new XoShiRo256StarStarRandom();
            default: return (T) new XoShiRo256StarStarRandom();
        }
    }

    public <T> T getnewInstance(int seed){
        switch (this) {
            case JDK: return (T) new Random(seed);
            case JDK_THREAD_LOCAL: {
                ThreadLocalRandom current = ThreadLocalRandom.current();
                current.setSeed(seed);
                return (T) current;
            }
            case JDK_SPLITTABLE: return (T)  new SplittableRandom(seed);
            case JDK_SECURE: return (T) new SecureRandom(new byte[] {(byte) seed});
            case XO_SHI_RO_256_PLUS_RANDOM: return (T) new XoShiRo256PlusRandom(seed);
            case XO_SHI_RO_256_STAR_STAR_RANDOM: return (T) new XoShiRo256StarStarRandom(seed);
            default: return (T) new XoShiRo256StarStarRandom();
        }
    }
}

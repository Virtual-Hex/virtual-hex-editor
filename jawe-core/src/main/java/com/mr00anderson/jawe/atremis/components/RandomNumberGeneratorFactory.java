package com.mr00anderson.jawe.atremis.components;

import it.unimi.dsi.util.XoShiRo256PlusRandom;
import it.unimi.dsi.util.XoShiRo256StarStarRandom;

import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGeneratorFactory {

    /**
     * -1 is to mark random seed
     */
    public int seed;
    public RandomGeneratorType randomGeneratorType;

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

        public SplittableRandom getnewInstanceSplitableRandom(){
            return new SplittableRandom();
        }

        public SplittableRandom getnewInstanceSplitableRandom(int seed){
            return new SplittableRandom(seed);
        }

        public Random getnewInstance(){
            switch (this) {
                case JDK: return new Random();
                case JDK_THREAD_LOCAL: return ThreadLocalRandom.current();
                case JDK_SECURE: return new SecureRandom();
                case XO_SHI_RO_256_PLUS_RANDOM: return new XoShiRo256PlusRandom();
                case XO_SHI_RO_256_STAR_STAR_RANDOM: return new XoShiRo256StarStarRandom();
                default: return new XoShiRo256StarStarRandom();
            }
        }

        public Random getnewInstance(int seed){
            switch (this) {
                case JDK: return new Random(seed);
                case JDK_THREAD_LOCAL: {
                    ThreadLocalRandom current = ThreadLocalRandom.current();
                    current.setSeed(seed);
                    return current;
                }
                case JDK_SECURE: return new SecureRandom(new byte[] {(byte) seed});
                case XO_SHI_RO_256_PLUS_RANDOM: return new XoShiRo256PlusRandom(seed);
                case XO_SHI_RO_256_STAR_STAR_RANDOM: return new XoShiRo256StarStarRandom(seed);
                default: return new XoShiRo256StarStarRandom();
            }
        }
    }

}

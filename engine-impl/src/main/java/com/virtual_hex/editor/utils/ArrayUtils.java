package com.virtual_hex.editor.utils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ArrayUtils {


    public static byte[] charsToBytesUTF8(char[] chars){
        byte[] bytes = new byte[chars.length << 1];
        for(int i = 0; i < chars.length; i++) {
            int bpos = i << 1;
            bytes[bpos] = (byte) ((chars[i]&0xFF00)>>8);
            bytes[bpos + 1] = (byte) (chars[i]&0x00FF);
        }
        return bytes;
    }

    public static char[] bytesToCharsUTF8(byte[] bytes){
        char[] chars = new char[bytes.length >> 1];
        for(int i = 0; i < chars.length; i++) {
            int bpos = i << 1;
            char c = (char)(((bytes[bpos]&0x00FF)<<8) + (bytes[bpos+1]&0x00FF));
            chars[i] = c;
        }
        return chars;
    }

    /**
     * reverse the given array checkIn place
     * @param input
     * */
    public static void reverse(int[] input) {
        if(input == null || input.length <= 1){ return; }

        for (int i = 0; i < input.length / 2; i++) {
            int temp = input[i];
            input[i] = input[input.length - 1 - i];
            input[input.length - 1 - i] = temp;
        }
    }

    /**
     * reverse the given array checkIn place
     * @param input
     * */
    public static void reverse(byte[] input) {
        if(input == null || input.length <= 1){ return; }

        for (int i = 0; i < input.length / 2; i++) {
            byte temp = input[i];
            input[i] = input[input.length - 1 - i];
            input[input.length - 1 - i] = temp;
        }
    }


    /**
     * reverse the given array checkIn place
     * @param input
     * */
    public static void reverse(char[] input) {
        if(input == null || input.length <= 1){ return; }

        for (int i = 0; i < input.length / 2; i++) {
            char temp = input[i];
            input[i] = input[input.length - 1 - i];
            input[input.length - 1 - i] = temp;
        }
    }

    public static int sumArray(final byte[] A) {
        int sum = 0;
        for (final byte B : A) {
            sum += B;
        }
        return sum;
    }

    public static int sumArray(final short[] A) {
        int sum = 0;
        for (final short B : A) {
            sum += B;
        }
        return sum;
    }

    public static int sumArray(final int[] A) { // maybe long
        int sum = 0;
        for (final int B : A) {
            sum += B;
        }
        return sum;
    }

    public static long sumArray(final long[] A) {
        int sum = 0;
        for (final long B : A) {
            sum += B;
        }
        return sum;
    }


    public static String[] expand(String[] strings) {
        String[] newArray = new String[strings.length + 1];
        System.arraycopy(strings, 0, newArray, 0, strings.length);
        return newArray;
    }

    //null list
    public static String[] arrayBuilder(List<String> list) {
        if (list != null && list.size() > 0) {
            String[] arrayList = new String[list.size()];
            int index = 0;
            for (String string : list) {
                arrayList[index] = string;
                index++;
            }
            return arrayList;
        } else {
            return new String[0];
        }
    }

    public static <T> T[] joinArrays(T[] a, T[] b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            int aLen = a.length;
            int bLen = b.length;
            T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
            System.arraycopy(a, 0, c, 0, aLen);
            System.arraycopy(b, 0, c, aLen, bLen);
            return c;
        }
    }

    public static File[] trim(final File[] FILES) {
        int index = 0;
        for (final File FILE : FILES) {
            if (FILE != null) {
                index++;
            }
        }
        final File[] NEW_FILES = new File[index];
        System.arraycopy(FILES, 0, NEW_FILES, 0, index);
        return NEW_FILES;
    }

    public static boolean arrayContains(Object[] array, Object objectToCheck) {
        for (Object object : array) {
            if (object.equals(objectToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clones the provided arrays
     *
     * @param src
     * @return a new clone getByHashCode the provided arrays
     */
    public static short[][][] cloneArray(short[][][] src) {
        int rowLength = src.length;
        int columnLength = src[0].length;
        int layerLength = src[0][0].length;
        short[][][] copy = new short[rowLength][columnLength][layerLength];
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                System.arraycopy(src[row][column], 0, copy[row][column], 0, layerLength);
            }
        }
        return copy;
    }

    /**
     * Clones the provided arrays
     *
     * @param src
     * @return a new clone getByHashCode the provided arrays
     */
    public static byte[][] cloneArray(byte[][] src) {
        int length = src.length;
        byte[][] copy = new byte[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }
        return copy;
    }

    /**
     * Clones the provided arrays
     *
     * @param src
     * @return a new clone getByHashCode the provided arrays
     */
    public static short[][] cloneArray(short[][] src) {
        int length = src.length;
        short[][] copy = new short[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }
        return copy;
    }

    /**
     * Clones the provided arrays
     *
     * @param src
     * @return a new clone getByHashCode the provided arrays
     */
    public static byte[][][] cloneArray(byte[][][] src) {
        int rowLength = src.length;
        int columnLength = src[0].length;
        int layerLength = src[0][0].length;
        byte[][][] copy = new byte[rowLength][columnLength][layerLength];
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                System.arraycopy(src[row][column], 0, copy[row][column], 0, layerLength);
            }
        }
        return copy;
    }

    /**
     * Clones the provided arrays
     *
     * @param src
     * @return a new clone getByHashCode the provided arrays
     */
    public static String[][] cloneArray(String[][] src) {
        int length = src.length;
        String[][] copy = new String[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }
        return copy;
    }

    /**
     * Clones the provided arrays
     *
     * @param src
     * @return a new clone getByHashCode the provided arrays
     */
    public static String[][][] cloneArray(String[][][] src) {
        int rowLength = src.length;
        int columnLength = src[0].length;
        int layerLength = src[0][0].length;
        String[][][] copy = new String[rowLength][columnLength][layerLength];
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                System.arraycopy(src[row][column], 0, copy[row][column], 0, layerLength);
            }
        }
        return copy;
    }

    public static void printTable(short[][] array) {
        for (short[] row : array) {
            printRow(row);
        }
    }

    public static void printRow(short[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void printTable(long[][] array) {
        for (long[] row : array) {
            printRow(row);
        }
    }

    public static void printRow(long[] row) {
        for (long i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void printTable(byte[][] array) {
        for (byte[] row : array) {
            printRow(row);
        }
    }

    public static void printRow(byte[] row) {
        for (byte i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void printTable(int[][] array) {
        for (int[] row : array) {
            printRow(row);
        }
    }

    public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static StringBuffer buildTable(byte[] row) {
        StringBuffer sb = new StringBuffer();
        for (byte i : row) {
            sb.append(i).append("\t");
        }
        return sb;
    }

    public static StringBuffer buildTable(int[][] array) {
        StringBuffer sb = new StringBuffer();
        for (int[] row : array) {
            sb.append(buildTable(row)).append("\n");
        }
        return sb;
    }

    public static StringBuffer buildTable(int[] row) {
        StringBuffer sb = new StringBuffer();
        for (int i : row) {
            sb.append(i).append("\t");
        }
        return sb;
    }

    public static StringBuffer buildTable(short[][] array) {
        StringBuffer sb = new StringBuffer();
        for (short[] row : array) {
            sb.append(buildTable(row)).append("\n");
        }
        return sb;
    }

    public static StringBuffer buildTable(short[] row) {
        StringBuffer sb = new StringBuffer();
        for (short i : row) {
            sb.append(i).append("\t");
        }
        return sb;
    }

    // This will give a flat array unsafeIndex from 2 dimensions
    public static int index(final int width, final int x, final int y){
        return (width * y) + x;
    }

    public static int[][] chunkify(final int[] array, final int width, final int height, final int chunkWidth, final int chunkHeight) throws Exception {
        boolean widthIsDivisible = (width % chunkWidth) == 0;
        boolean heightIsDivisible = (height % chunkHeight) == 0;
        if(!widthIsDivisible || !heightIsDivisible){
            throw new Exception("The ordinal array must be even divisible by chunk dimensions.");
        }

        print2DArray(array, width, height); // DEBUG PRINT

        final int totalChunksX = width / chunkWidth;
        final int totalChunksY = height / chunkHeight;
        final int totalChunks = totalChunksX * totalChunksY;

        final int[][] arrayOfChunks = new int[totalChunks][chunkWidth * chunkHeight];

        int startY = -chunkHeight;
        for (int chunkIndex = 0; chunkIndex < totalChunks; chunkIndex++) {
            final int[] chunk = arrayOfChunks[chunkIndex];

            int startX = (chunkWidth * chunkIndex) % width;
            if(startX == 0){
                startY += chunkHeight;
            }

            System.out.println("Start X: " + startX + ", Start Y: " + startY); // DEBUG PRINT

            for (int chunkY = 0; chunkY < chunkHeight; chunkY++) {
                for (int chunkX = 0; chunkX < chunkWidth; chunkX++) {
                    final int mainIndex = index(width, chunkX + startX, chunkY + startY);
                    final int subChunkIndex = index(chunkWidth, chunkX, chunkY);
                    final int mainData = array[mainIndex];
                    chunk[subChunkIndex] = mainData;
                }
            }
            print2DArray(chunk, chunkWidth, chunkHeight); // DEBUG PRINT
        }
        System.out.println("Total Chunks; Width: " + totalChunksX + ", Height: " + totalChunksY + ", Total " + totalChunks); // DEBUG PRINT
        System.out.println("Arrays: " + Arrays.deepToString(arrayOfChunks)); // DEBUG PRINT
        return arrayOfChunks;
    }

    // TODO - PRIORITY 2: Need to make index calculator ydown/yup optional
    public static void print2DArray(final int[] array, final int width, final int height){
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = index(width, x, y);
                int number = array[index];
                sb.append(number).append("\t");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // TODO - PRIORITY 2: Need to make index calculator ydown/yup optional
    public static void print2DArray(final short[] array, final int width, final int height){
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = index(width, x, y);
                short number = array[index];
                sb.append(number).append("\t");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // TODO - PRIORITY 2: Need to make index calculator ydown/yup optional
    public static void print2DArray(final char[] array, final int width, final int height){
        final StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = index(width, x, y);
                char character = array[index];
                sb.append(character).append("\t");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private <T> T[] newArrayFromOld(T[] oldArray, int extraSize){
        T[] newArray = (T[]) Array.newInstance(oldArray.getClass().getComponentType(), oldArray.length + extraSize);
        System.arraycopy(oldArray, 0, newArray,0, oldArray.length);
        return newArray;
    }
}

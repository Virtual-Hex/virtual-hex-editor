package com.virtual_hex.editor.utils;

import com.virtual_hex.editor.data.UIComponent;

import java.util.Arrays;

public class UIUtils {
    // Function to merge multiple arrays in Java
    public static UIComponent[] merge(UIComponent[]... uiComponentArrays) {
        int finalLength = 0;
        for (UIComponent[] array : uiComponentArrays) {
            finalLength += array.length;
        }

        UIComponent[] dest = null;
        int destPos = 0;

        for (UIComponent[] array : uiComponentArrays)
        {
            if (dest == null) {
                dest = Arrays.copyOf(array, finalLength);
                destPos = array.length;
            } else {
                System.arraycopy(array, 0, dest, destPos, array.length);
                destPos += array.length;
            }
        }
        return dest;
    }

    public static UIComponent[] merge(UIComponent... uiComponents) {
        return uiComponents;
    }

}

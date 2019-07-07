package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

import java.util.List;

public interface UIComponentWriter<T, E> {
    /**
     * Do not use on LinkedList
     *
     * @param components
     */
    static <T, E extends UIWriter<T>>  void processList(T out, List<UIComponent> components, E writer){
        int size = components.size();
        // Must be index for removal and add operations to be while list is being iterated
        for (int i = 0; i < size; i++) {
            UIComponent uiComponent = components.get(i);
            writer.write(out, uiComponent);
        }
    }

    /**
     * Do not use on LinkedList
     *
     * @param components
     */
    static <T, E extends UIWriter<T>> void processArray(T out, UIComponent[] components, E writer){
        int size = components.length;
        // Must be index for removal and add operations to be while list is being iterated
        for (int i = 0; i < size; i++) {
            UIComponent uiComponent =components[i];
            writer.write(out, uiComponent);
        }
    }

    void write(T out, UIComponent uiComponent, E writer);
}

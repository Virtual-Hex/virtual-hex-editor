package com.virtual_hex.editor.utils;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import com.virtual_hex.editor.UIWriter;

import java.util.List;

public class UIComponentsUtils {

    public static void processAddRemoveComponent(UIComponents uiComponents) {
        for (UIComponent element; (element = uiComponents.addComponentQueue.poll()) != null; ) {
            uiComponents.uiComponents.add(element);
        }

        for (UIComponent element; (element = uiComponents.removeComponentQueue.poll()) != null; ) {
            uiComponents.uiComponents.remove(element);
        }
    }

    public static <T> void processUiDataList(T out, UIComponents uiComponents, UIWriter<T> writer) {
        processAddRemoveComponent(uiComponents);
        loopThroughList(out, uiComponents, writer);
    }

    public static <T> void loopThroughList(T out, UIComponents uiComponents, UIWriter<T> writer) {
        List<UIComponent> componentsList = uiComponents.uiComponents;
        for (int i = 0; i < componentsList.size(); i++) {
            UIComponent component = componentsList.get(i);
            if (component != null) {
                writer.write(out, component);
            }
        }
    }
}

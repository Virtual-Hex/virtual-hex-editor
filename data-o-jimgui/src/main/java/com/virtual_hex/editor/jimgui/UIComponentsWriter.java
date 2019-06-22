package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

import java.util.List;

@ComponentRegister(typeKey = UIComponents.class)
public class UIComponentsWriter extends JImGuiComponentWriter {

    public static void processAddRemoveComponent(UIComponents uiComponents) {
        for (UIComponent element; (element = uiComponents.addComponentQueue.poll()) != null; ) {
            uiComponents.uiComponents.add(element);
        }

        for (UIComponent element; (element = uiComponents.removeComponentQueue.poll()) != null; ) {
            uiComponents.uiComponents.remove(element);
        }
    }

    public static void processUiDataList(JImGui out, UIComponents uiComponents, UIWriter<JImGui> writer) {
        processAddRemoveComponent(uiComponents);
        loopThroughList(out, uiComponents, writer);
    }

    public static void loopThroughList(JImGui out, UIComponents uiComponents, UIWriter<JImGui> writer) {
        List<UIComponent> componentsList = uiComponents.uiComponents;
        for (int i = 0; i < componentsList.size(); i++) {
            UIComponent component = componentsList.get(i);
            if (component != null) {
                writer.write(out, component);
            }
        }
    }

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        UIComponents component = (UIComponents) uiComponent;
        processUiDataList(out, component, writer);
    }
}

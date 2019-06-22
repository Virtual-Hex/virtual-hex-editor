package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InputText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = InputText.class)
public class TextInputWriter extends ByteArrayComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InputText component = (InputText) uiComponent;
        byte[] buffer = getCachedBuffer(component.id, component.label, component.bufferSize);
        copyStringIntoBuffer(component.value, buffer);
        boolean fieldChanged = out.inputText(component.label, buffer, component.flags);

        if (fieldChanged) {
            // TODO potential caching here
            component.value = new String(buffer, 0, bufferEndIndex(buffer));
            // Handle the input if needed
            writer.handleStateChange(out, component, writer);
        }

    }
}

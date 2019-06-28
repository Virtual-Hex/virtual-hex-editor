package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ShowStyleEditor;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStyle;

// / add style editor block (not a window). you can pass in a reference ImGuiStyle structure to compare to,
// revert to and save to (else it uses the default style)
@ComponentRegister(typeKey = ShowStyleEditor.class)
public class ShowStyleEditorWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowStyleEditor<JImStyle> component = (ShowStyleEditor) uiComponent;

        // TODO This can be more complex
        // TODO persisting

        if(component.style == null){
            out.showStyleEditor();
        } else {
            out.showStyleEditor(component.style);
        }

    }
}

package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.UIComponentsRegister;
import com.virtual_hex.editor.data.*;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;
import org.ice1000.jimgui.NativeBool;

import java.util.List;

//@formatter:off        - IntelliJ
/* @formatter:off       - Eclipse */

//@formatter:on        - IntelliJ
/* @formatter:on       - Eclipse */
@UIComponentsRegister
public class BuiltInWriters {

    // Required for class scanning
    public BuiltInWriters() {
    }

    @UIComponentRegister(typeKey = ArrowButton.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> arrowButton = (out, uiComponent, writer) -> {
        ArrowButton<JImStr> component = (ArrowButton) uiComponent;
        boolean pressed = out.arrowButton(component.label, component.direction.index);
        if (pressed) writer.handleActivation(out, uiComponent, writer);
    };


    @UIComponentRegister(typeKey = BulletText.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> bulletText =
            (out, uiComponent, writer) -> out.bulletText(((BulletText<JImStr>) uiComponent).label);

    @UIComponentRegister(typeKey = Button.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> button = (out, uiComponent, writer) -> {
        Button<JImStr> uiComponent0 = (Button) uiComponent;
        boolean pressed = out.button(uiComponent0.label, uiComponent0.width, uiComponent0.height);
        if (pressed) writer.handleActivation(out, uiComponent, writer);
    };

    @UIComponentRegister(typeKey = CheckBox.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> checkBox = (out, uiComponent, writer) -> {
        CheckBox<JImStr> component = (CheckBox) uiComponent;
        // getting a Boolean
        NativeBool value = writer.getCachedNativeBool("checked", component);
        // Modify it to reflect the components buffer
        value.modifyValue(component.checked);
        boolean changed = out.checkbox(component.label, value);
        if(changed){
            component.checked = value.accessValue();
            // Been modified notify
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = Child.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> child = (out, uiComponent, writer) -> {
        Child<JImStr> component = (Child) uiComponent;

        // Not clipped or collapsed
        boolean visible = out.beginChild0(component.label, component.width, component.height, component.border, component.flags);
        if (visible) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
        }
    };

    @UIComponentRegister(typeKey = CollapsingHeaderDecorated.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> collapsingHeaderDecorated = (out, uiComponent, writer) -> {
        CollapsingHeaderDecorated<JImStr> component = (CollapsingHeaderDecorated) uiComponent;
        NativeBool value = writer.getCachedNativeBool("open", component);
        // Not clipped or collapsed
        value.modifyValue(component.open);
        boolean open = out.collapsingHeader(component.label, value, component.flags);
        component.open = value.accessValue();
        if (open) UIComponentWriter.processArray(out, component.uiComponents, writer);

        // Trigger handler only when X is pressed on collapsing header
        if (!component.open) writer.handleActivation(out, component, writer);

        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = CollapsingHeader.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> collapsingHeader = (out, uiComponent, writer) -> {
        CollapsingHeader component = (CollapsingHeader) uiComponent;
        boolean open = out.collapsingHeader(component.label);
        if (open) UIComponentWriter.processArray(out, component.uiComponents, writer);
        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = ColorButton.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> colorButton = (out, uiComponent, writer) -> {
        ColorButton<JImStr, JImVec4> component = (ColorButton) uiComponent;
        boolean pressed = out.colorButton(component.label, component.color, component.flags, component.width, component.height);
        if(pressed) writer.handleActivation(out, uiComponent, writer);
    };

    @UIComponentRegister(typeKey = ColorEdit3.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> colorEdit3 = (out, uiComponent, writer) -> {
        ColorEdit3<JImStr, JImVec4> component = (ColorEdit3) uiComponent;
        boolean stateChange = out.colorEdit3(component.label, component.color, component.flags);
        if(stateChange)writer.handleStateChange(out, uiComponent, writer);
    };

    @UIComponentRegister(typeKey = ColorEdit4.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> colorEdit4 = (out, uiComponent, writer) -> {
        ColorEdit4<JImStr, JImVec4> component = (ColorEdit4) uiComponent;
        boolean stateChange = out.colorEdit4(component.label, component.color, component.flags);
        if(stateChange) writer.handleStateChange(out, uiComponent, writer);
    };

    @UIComponentRegister(typeKey = ColorPicker3.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> colorPicker3 = (out, uiComponent, writer) -> {
        ColorPicker3<JImStr, JImVec4> component = (ColorPicker3) uiComponent;
        boolean stateChange = out.colorPicker3(component.label, component.color, component.flags);
        if(stateChange )writer.handleStateChange(out, uiComponent, writer);
    };

    @UIComponentRegister(typeKey = ColorPicker4.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> colorPicker4 = (out, uiComponent, writer) -> {
        ColorPicker4<JImStr, JImVec4> component = (ColorPicker4) uiComponent;
        boolean stateChange = out.colorPicker4(component.label, component.color, component.flags);
        if(stateChange) writer.handleStateChange(out, uiComponent, writer);
    };

    @UIComponentRegister(typeKey = ColorText.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> colorText = (out, uiComponent, writer) -> {
        ColorText<JImVec4> component = (ColorText) uiComponent;
        out.textColored(component.color, component.label);
    };

    @UIComponentRegister(typeKey = Columns.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> columns = (out, uiComponent, writer) -> {
        Columns component = (Columns) uiComponent;
        out.columns(component.count, component.stringId, component.border);
    };

    @UIComponentRegister(typeKey = Combo.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> combo = (out, uiComponent, writer) -> {
        Combo<JImStr> component = (Combo) uiComponent;

        boolean selected = out.beginCombo(component.label, component.currentSelectable.label, component.flags);
        if (selected) {
            // Loop through selectables here
            List<UIComponent> components = component.uiComponents;
            for (UIComponent uiSelectable : components) {
                Selectable<JImStr> selectable = (Selectable) uiSelectable;
                boolean isSelected = selectable.equals(component.currentSelectable);
                if (out.selectable0(selectable.label, isSelected, selectable.flags, selectable.width, selectable.height)) {
                    component.currentSelectable = selectable;
                }
                if (selected) out.setItemDefaultFocus();
            }
            // TODO Before after combo..
            writer.handleStateChange(out, uiComponent, writer);
            out.endCombo();
        }
    };

}

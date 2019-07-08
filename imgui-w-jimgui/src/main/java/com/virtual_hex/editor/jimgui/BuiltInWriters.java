package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.UIComponentsRegister;
import com.virtual_hex.editor.data.*;
import org.ice1000.jimgui.*;

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

    @UIComponentRegister(typeKey = DragFloatRange.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> dragFloatRange2 = (out, uiComponent, writer) -> {
        DragFloatRange<JImStr> component = (DragFloatRange) uiComponent;

        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);

        NativeFloat nativeValue2 = writer.getCachedNativeFloat("value2", component);
        nativeValue2.modifyValue(component.value2);

        boolean stateChanged = out.dragFloatRange2(component.label, nativeValue, nativeValue2, component.valueSpeed, component.valueMin, component.valueMax, component.format, component.format2, component.power);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            component.value2 = nativeValue2.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = DragFloat.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> dragFloat = (out, uiComponent, writer) -> {
        DragFloat<JImStr> component = (DragFloat) uiComponent;

        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean stateChanged = out.dragFloat(component.label, nativeValue, component.valueSpeed, component.valueMin, component.valueMax, component.format, component.power);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = DragIntRange.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> dragIntRange2 = (out, uiComponent, writer) -> {
        DragIntRange<JImStr> component = (DragIntRange) uiComponent;

        NativeInt nativeValue = writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);

        NativeInt nativeValue2 =writer.getCachedNativeInt("value2", component);
        nativeValue2.modifyValue(component.value2);

        boolean stateChanged = out.dragIntRange2(component.label, nativeValue, nativeValue2, component.valueSpeed, component.valueMin, component.valueMax, component.format);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            component.value2 = nativeValue2.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = DragInt.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> dragInt = (out, uiComponent, writer) -> {
        DragInt<JImStr> component = (DragInt) uiComponent;

        NativeInt nativeValue = writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);
        boolean stateChanged = out.dragInt(component.label, nativeValue, component.valueSpeed, component.valueMin, component.valueMax, component.format);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = DragVec4.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> dragVec4 = (out, uiComponent, writer) -> {
        DragVec4<JImStr, JImVec4> component = (DragVec4) uiComponent;
        out.dragVec4(component.label, component.bounds, component.speed, component.min, component.max);
    };

    @UIComponentRegister(typeKey = Dummy.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> dummy = (out, uiComponent, writer) -> {
        Dummy component = (Dummy) uiComponent;
        JImGuiGen.dummy(component.width, component.height);

    };

    @UIComponentRegister(typeKey = Group.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> group = (out, uiComponent, writer) -> {
            Group component = (Group) uiComponent;
            out.beginGroup();
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.endGroup();
    };

    @UIComponentRegister(typeKey = ImageButton.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> imageButton = (out, uiComponent, writer) -> {
        ImageButton<JImStr, Object> component = (ImageButton) uiComponent;
        JImTextureID textureId = writer.getTextureId(component, writer);
        if(textureId != null) {
            boolean pressed = out.imageButton(textureId, component.width, component.height, component.uv0x, component.uv0y, component.uv1x, component.uv1y, component.framePadding);
            if(pressed) {
                writer.handleActivation(out, uiComponent, writer);
            }
        } else {
            out.text(String.format("Image loading error. Could not load from type %s at %s.", component.from.getClass(), component.from.toString()));
        }
    };

    @UIComponentRegister(typeKey = Image.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> image = (out, uiComponent, writer) -> {
        Image component = (Image) uiComponent;
        JImTextureID textureId = writer.getTextureId(component, writer);
        if(textureId != null) {
            out.image(textureId, component.width, component.height, component.uv0x, component.uv0y, component.uv1x, component.uv1y);
        } else {
            out.text(String.format("Image loading error. Could not load from type %s at %s.", component.from.getClass(), component.from.toString()));
        }
    };

    @UIComponentRegister(typeKey = Indent.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> indent = (out, uiComponent, writer) -> {
        Indent component = (Indent) uiComponent;
        out.indent(component.value);
    };


    @NativeExchange
    @UIComponentRegister(typeKey = InputDoubleStepped.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputDoubleStepped = (out, uiComponent, writer) -> {
        InputDoubleStepped<String> component = (InputDoubleStepped) uiComponent;
        NativeDouble nativeValue = writer.getCachedNativeDouble("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputDouble(component.label, nativeValue, component.step, component.stepFast, component.format);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @NativeExchange
    @UIComponentRegister(typeKey = InputDouble.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputDouble = (out, uiComponent, writer) -> {
        InputDouble<String> component = (InputDouble) uiComponent;
        NativeDouble nativeValue = writer.getCachedNativeDouble("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputDouble(component.label, nativeValue);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @NativeExchange
    @UIComponentRegister(typeKey = InputFloatStepped.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputFloatStepped = (out, uiComponent, writer) -> {
        InputFloatStepped<JImStr> component = (InputFloatStepped) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputFloat(component.label, nativeValue, component.step, component.stepFast, component.format, component.flags);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @NativeExchange
    @UIComponentRegister(typeKey = InputFloat.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputFloat = (out, uiComponent, writer) -> {
        InputFloat<String> component = (InputFloat) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputFloat(component.label, nativeValue);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @NativeExchange
    @UIComponentRegister(typeKey = InputIntStepped.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputIntStepped = (out, uiComponent, writer) -> {
        InputIntStepped<JImStr> component = (InputIntStepped) uiComponent;
        NativeInt nativeValue =writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputInt(component.label, nativeValue, component.step, component.stepFast, component.flags);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @NativeExchange
    @UIComponentRegister(typeKey = InputInt.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputInt = (out, uiComponent, writer) -> {
        InputInt<String> component = (InputInt) uiComponent;
        NativeInt nativeValue =writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);
        boolean stateChanged = out.inputInt(component.label, nativeValue);
        if (stateChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };


    @UIComponentRegister(typeKey = InputText.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> inputText = (out, uiComponent, writer) -> {
        InputText component = (InputText) uiComponent;
        boolean fieldChanged = out.inputText(component.label, component.buffer, component.flags);
        if (fieldChanged) {
            // Handle the input if needed
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = InvisibleButton.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> invisibleButton = (out, uiComponent, writer) -> {
        InvisibleButton<JImStr> component = (InvisibleButton) uiComponent;
        boolean pressed = out.invisibleButton(component.label, component.width, component.height);
        if (pressed) writer.handleActivation(out, component, writer);
    };

    // See StringLabelWriter for sample of the two types, ones more efficient then the other which is the JImStr
    @UIComponentRegister(typeKey = LabelText.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> labelText = (out, uiComponent, writer) -> {
            LabelText<JImStr> component = (LabelText) uiComponent;
            out.labelText(component.label, component.text);

    };

    @UIComponentRegister(typeKey = ListBox0.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> listBox0 = (out, uiComponent, writer) -> {
        ListBox0<JImStr> component = (ListBox0) uiComponent;

        boolean draw = out.listBoxHeader0(component.label, component.width, component.height);
        if(draw){
            // TODO Into Selectables Group
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.listBoxFooter();
        }
    };

    @UIComponentRegister(typeKey = ListBox.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> listBox = (out, uiComponent, writer) -> {
        ListBox<JImStr> component = (ListBox) uiComponent;

        boolean draw = out.listBoxHeader(component.label, component.itemsCount, component.heightInItems);
        if(draw){
            // TODO Into Selectables Group
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.listBoxFooter();
        }
    };


    @UIComponentRegister(typeKey = MainMenuBar.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> mainMenuBar = (out, uiComponent, writer) -> {
        MainMenuBar component = (MainMenuBar) uiComponent;
        boolean open = out.beginMainMenuBar();
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.endMainMenuBar();
        }
    };


    @UIComponentRegister(typeKey = MenuItemSelectable.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> menuItemSelectable = (out, uiComponent, writer) -> {
        MenuItemSelectable<JImStr> component = (MenuItemSelectable) uiComponent;
        NativeBool value = writer.getCachedNativeBool("selected", component);

        value.modifyValue(component.selected);
        boolean activated = out.menuItem(component.label, component.shortcut, value, component.enabled);

        // Returns true when activated
        if (activated) {
            component.selected = value.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = MenuItem.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> menuItem = (out, uiComponent, writer) -> {
            MenuItem component = (MenuItem) uiComponent;
            // Returns true when activated
            boolean value = out.menuItem(component.label, component.shortcut);
            if (value) writer.handleActivation(out, component, writer);
    };

    @UIComponentRegister(typeKey = Menu.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> menu = (out, uiComponent, writer) -> {
        Menu<JImStr> component = (Menu) uiComponent;
        boolean open = out.beginMenu(component.label, component.enabled);
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.endMenu();
        }
    };

    @UIComponentRegister(typeKey = NewLine.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> newLine =
            (out, uiComponent, writer) -> out.newLine();

    @UIComponentRegister(typeKey = NextColumn.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> nextColumn =
            (out, uiComponent, writer) -> out.nextColumn();

    @UIComponentRegister(typeKey = PlotHistogram.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> plotHistogram = (out, uiComponent, writer) -> {
        PlotHistogram component = (PlotHistogram) uiComponent;
        out.plotHistogram(component.label, component.values, component.valueOffset, component.valuesLength,
                component.overlayText,
                component.scaleMin, component.scaleMax,
                component.graphWidth, component.graphHeight);
    };

    @UIComponentRegister(typeKey = PlotLines.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> plotLines = (out, uiComponent, writer) -> {
        PlotLines component = (PlotLines) uiComponent;
        out.plotLines(component.label, component.values, component.valueOffset, component.valuesLength,
                component.overlayText,
                component.scaleMin, component.scaleMax,
                component.graphWidth, component.graphHeight);
    };

    @UIComponentRegister(typeKey = PopId.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> popId =
            (out, uiComponent, writer) -> out.popID();
;

    @UIComponentRegister(typeKey = Popup.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> popup = (out, uiComponent, writer) -> {
        Popup<JImStr> component = (Popup) uiComponent;
        // Not clipped or collapsed
        boolean visible = out.beginPopup(component.label, component.flags);
        if (visible) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            JImGuiGen.endPopup();
        }
    };

    @UIComponentRegister(typeKey = ProgressBar.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> progressBar = (out, uiComponent, writer) -> {
        ProgressBar component = (ProgressBar) uiComponent;
        out.progressBar(component.fraction, component.width, component.height, component.overlay);
    };

    @UIComponentRegister(typeKey = PushId.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> pushId = (out, uiComponent, writer) -> {
        PushId component = (PushId) uiComponent;
        out.pushID(component.id);
    };

    @UIComponentRegister(typeKey = RadioButton.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> radioButton = (out, uiComponent, writer) -> {
        RadioButton<JImStr> component = (RadioButton) uiComponent;

        NativeInt nativeValue = writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);

        for (int i = 0; i < component.stringLabels.length; i++) {
            JImStr stringLabel = component.stringLabels[i];
            boolean selected = out.radioButton(stringLabel, nativeValue, i);
            if(selected){
                nativeValue.modifyValue(i);
                component.value = i;
                // Was selected we should call a state change handler
                writer.handleStateChange(out, uiComponent, writer);
            }
        }
    };

    @UIComponentRegister(typeKey = RadioButton0.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> radioButton0 = (out, uiComponent, writer) -> {
        RadioButton0<JImStr> component = (RadioButton0) uiComponent;
        for (int i = 0; i < component.stringLabels.length; i++) {
            JImStr stringLabel = component.stringLabels[i];
            boolean selected = out.radioButton0(stringLabel, component.value == i);
            System.out.println(selected);
            if(selected){
                component.value = i;
                // Was selected we should call a state change handler
                writer.handleStateChange(out, uiComponent, writer);
            }
        }
    };

    @UIComponentRegister(typeKey = SameLine.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> sameLine = (out, uiComponent, writer) -> {
        SameLine component = (SameLine) uiComponent;
        JImGuiGen.sameLine(component.positionX, component.spacingWidth);
    };

    @UIComponentRegister(typeKey = Selectable.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> selectable = (out, uiComponent, writer) -> {
        Selectable<JImStr> component = (Selectable) uiComponent;
        boolean selected = out.selectable0(component.label, component.selected, component.flags, component.width, component.height);

        if (selected) {
            component.selected = !component.selected;
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = Separator.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> seperator =
            (out, uiComponent, writer) -> JImGuiGen.separator();

    @UIComponentRegister(typeKey = SetToolTip.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> setToolTip = (out, uiComponent, writer) -> {
        SetToolTip<JImStr> component = (SetToolTip) uiComponent;
        if(out.isItemHovered()) out.setTooltip(component.label);
    };

    @UIComponentRegister(typeKey = ShowAboutWindow.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showAboutWindow = (out, uiComponent, writer) -> {
        ShowAboutWindow component = (ShowAboutWindow) uiComponent;
        if(component.open) out.showAboutWindow();
    };

    @UIComponentRegister(typeKey = ShowDemoWindow.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showDemoWindow = (out, uiComponent, writer) -> {
        ShowDemoWindow component = (ShowDemoWindow) uiComponent;
        if (component.open) out.showDemoWindow();
    };

    @UIComponentRegister(typeKey = ShowFontSelector.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showFontSelectorWindow = (out, uiComponent, writer) -> {
        ShowFontSelector<JImStr> component = (ShowFontSelector) uiComponent;
        out.showFontSelector(component.label);
    };

    @UIComponentRegister(typeKey = ShowMetricsWindow.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showMetricsWindow = (out, uiComponent, writer) -> {
        ShowMetricsWindow component = (ShowMetricsWindow) uiComponent;
        if(component.open) out.showMetricsWindow();
    };

    @UIComponentRegister(typeKey = ShowStyleEditor.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showStyleEditorWindow = (out, uiComponent, writer) -> {
        ShowStyleEditor<JImStyle> component = (ShowStyleEditor) uiComponent;
        // / add style editor block (not a window). you can pass in a reference ImGuiStyle structure to compare to,
        // revert to and save to (else it uses the default style)
        // TODO This can be more complex
        // TODO persisting

        if(component.style == null){
            out.showStyleEditor();
        } else {
            out.showStyleEditor(component.style);
        }

    };

    @UIComponentRegister(typeKey = ShowStyleSelector.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showStyleSelectorWindow = (out, uiComponent, writer) -> {
        ShowStyleSelector<JImStr> component = (ShowStyleSelector) uiComponent;
        out.showStyleSelector(component.label);
    };

    @UIComponentRegister(typeKey = ShowUserGuide.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> showUserGuideWindow =
            (out, uiComponent, writer) -> out.showUserGuide();

    @UIComponentRegister(typeKey = SliderAngle.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> sliderAngle = (out, uiComponent, writer) -> {
        SliderAngle<JImStr> component = (SliderAngle) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.sliderAngle(component.label, nativeValue, component.valueDegreeMin, component.valueDegreeMax);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = SliderFloat.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> sliderFloat = (out, uiComponent, writer) -> {
        SliderFloat<JImStr> component = (SliderFloat) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.sliderFloat(component.label, nativeValue, component.valueMin, component.valueMax, component.format, component.power);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = SliderInt.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> sliderInt = (out, uiComponent, writer) -> {
        SliderInt<JImStr> component = (SliderInt) uiComponent;
        NativeInt nativeValue = writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.sliderInt(component.label, nativeValue, component.valueMin, component.valueMax, component.format);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = SliderVec4.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> sliderVec4 = (out, uiComponent, writer) -> {
        SliderVec4<JImStr, JImVec4> component = (SliderVec4) uiComponent;
        out.sliderVec4(component.label, component.value, component.valueMin, component.valueMax);
        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = Spacing.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> spacing =
            (out, uiComponent, writer) -> out.spacing();

    @UIComponentRegister(typeKey = TabBarDecorated.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> tabBarDecorated = (out, uiComponent, writer) -> {
        TabBarDecorated<JImStr> component = (TabBarDecorated) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.beginTabBar(component.label, component.flags);
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
                JImGuiGen.endTabBar();
            }
        }
    };

    @UIComponentRegister(typeKey = TabBar.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> tabBar = (out, uiComponent, writer) -> {
        TabBar component = (TabBar) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.beginTabBar(component.label);
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
                JImGuiGen.endTabBar();
            }
        }
    };

    @UIComponentRegister(typeKey = TabItemDecorated.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> tabItemDecorated = (out, uiComponent, writer) -> {
        TabItemDecorated<JImStr> component = (TabItemDecorated) uiComponent;
        if (component.open) {
            NativeBool value = writer.getCachedNativeBool("open", component);
            // Not clipped or collapsed
            value.modifyValue(component.open);
            boolean visible = out.beginTabItem(component.label, value, component.flags);
            component.open = value.accessValue();
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
                JImGuiGen.endTabItem();
            }
        }
        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = TabItem.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> tabItem = (out, uiComponent, writer) -> {
        TabItem component = (TabItem) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.beginTabItem(component.label);
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
                JImGuiGen.endTabItem();
            }
        }
        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = TextDisabled.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> textDisabled = (out, uiComponent, writer) -> {
        TextDisabled component = (TextDisabled) uiComponent;
        out.textDisabled(component.label);
    };

    @UIComponentRegister(typeKey = TextUnformatted.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> textUnformatted = (out, uiComponent, writer) -> {
        TextUnformatted component = (TextUnformatted) uiComponent;
        out.textUnformatted(component.label);
    };

    @UIComponentRegister(typeKey = TextWrapped.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> textWrapped = (out, uiComponent, writer) -> {
        TextWrapped<JImStr> component = (TextWrapped) uiComponent;
        out.textWrapped(component.label);
    };

    @UIComponentRegister(typeKey = Text.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> text = (out, uiComponent, writer) -> {
        Text component = (Text) uiComponent;
        out.text(component.label);
    };

    @UIComponentRegister(typeKey = ToolTip.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> toolTip = (out, uiComponent, writer) -> {
        ToolTip component = (ToolTip) uiComponent;
        if(out.isItemHovered()) {
            out.beginTooltip();
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.endTooltip();
        }
    };

    @UIComponentRegister(typeKey = TreeNodeEx.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> treeNodeEx = (out, uiComponent, writer) -> {
        TreeNodeEx<JImStr> component = (TreeNodeEx) uiComponent;
        boolean open = out.treeNodeEx(component.label, component.flags);
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.treePop();
        }
        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = TreeNode.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> treeNode = (out, uiComponent, writer) -> {
        TreeNode<JImStr> component = (TreeNode) uiComponent;
        boolean open = out.treeNode(component.label);
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.treePop();
        }
        writer.handleStateChange(out, component, writer);
    };

    @UIComponentRegister(typeKey = Unindent.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> unindent = (out, uiComponent, writer) -> {
        Unindent component = (Unindent) uiComponent;
        out.unindent(component.value);
    };

    @UIComponentRegister(typeKey = VSliderFloat.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> vSliderFloat = (out, uiComponent, writer) -> {
        VSliderFloat<JImStr> component = (VSliderFloat) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.vSliderFloat(component.label, component.width, component.height, nativeValue, component.valueMin, component.valueMax, component.format, component.power);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    };

    @UIComponentRegister(typeKey = WindowDecorated.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> windowDecorated = (out, uiComponent, writer) -> {
        WindowDecorated<JImStr> component = (WindowDecorated) uiComponent;
        if (component.open) {
            NativeBool value = writer.getCachedNativeBool("open", component);
            // Not clipped or collapsed
            value.modifyValue(component.open);
            boolean visible = out.begin(component.label, value, component.flags);
            component.open = value.accessValue();
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
            }
            JImGuiGen.end();
        }
    };

    @UIComponentRegister(typeKey = Window.class)
    public static final UIComponentWriter<JImGui, DefaultUIWriter> window = (out, uiComponent, writer) -> {
        Window component = (Window) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.begin(component.label);
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
            }
            JImGuiGen.end();
        }
    };
}

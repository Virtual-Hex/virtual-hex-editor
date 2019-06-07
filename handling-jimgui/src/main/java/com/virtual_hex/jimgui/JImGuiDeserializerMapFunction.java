package com.virtual_hex.jimgui;

import com.virtual_hex.data.Button;
import com.virtual_hex.data.OpenableFlags;
import com.virtual_hex.data.UIApp;
import com.virtual_hex.handling.ComponentHandler;
import com.virtual_hex.jimgui.handlers.ButtonHandler;
import com.virtual_hex.jimgui.handlers.OpenableFlagHandler;
import com.virtual_hex.jimgui.handlers.UIAppHandler;
import org.ice1000.jimgui.JImGui;

import java.util.Map;
import java.util.function.Consumer;


// Parent would have the deserializer


public class JImGuiDeserializerMapFunction implements Consumer<Map<Class<?>, ComponentHandler<JImGui>>> {

    @Override
    public void accept(Map<Class<?>, ComponentHandler<JImGui>> typeDrawers) {
        // Non JImGui Specific Types
        typeDrawers.put(OpenableFlags.class, new OpenableFlagHandler());
        typeDrawers.put(UIApp.class, new UIAppHandler());

        // JImGui Specific Types
        typeDrawers.put(Button.class, new ButtonHandler());



//        classComponentHandlers.put(BeginMenu.class, JImGuiUIDataDeserializer::beginMenu);

//
//        classComponentHandlers.put(CheckBox.class, JImGuiUIDataDeserializer::checkbox);
//        classComponentHandlers.put(ColorText.class, JImGuiUIDataDeserializer::colorText);
//        classComponentHandlers.put(Columns.class, JImGuiUIDataDeserializer::columns);
//        classComponentHandlers.put(Dummy.class, JImGuiUIDataDeserializer::dummy);
//        // TODO COMBO
//        classComponentHandlers.put(InputDouble.class, JImGuiUIDataDeserializer::inputDouble);
//        classComponentHandlers.put(InputDoubleStepped.class, JImGuiUIDataDeserializer::inputDoubleStepped);
//        classComponentHandlers.put(InputFloat.class, JImGuiUIDataDeserializer::inputFloat);
//        classComponentHandlers.put(InputFloatStepped.class, JImGuiUIDataDeserializer::inputFloatStepped);
//        classComponentHandlers.put(InputInt.class, JImGuiUIDataDeserializer::inputInt);
//        classComponentHandlers.put(InputIntStepped.class, JImGuiUIDataDeserializer::inputIntStepped);
//
//        classComponentHandlers.put(InvisibleButton.class, JImGuiUIDataDeserializer::invisibleButton);
//        // TODO MENU
//        // TODO MENU ITEM
//        classComponentHandlers.put(NewLine.class, JImGuiUIDataDeserializer::newLine);
//        classComponentHandlers.put(NextColumn.class, JImGuiUIDataDeserializer::nextColumn);
//        // TODO OPEN POPUP
//        classComponentHandlers.put(SameLine.class, JImGuiUIDataDeserializer::sameLine);
//        classComponentHandlers.put(Selectable.class, JImGuiUIDataDeserializer::selectable);
//        classComponentHandlers.put(Separator.class, JImGuiUIDataDeserializer::seperator);
//        classComponentHandlers.put(SmallButton.class, JImGuiUIDataDeserializer::smallButton);
//        classComponentHandlers.put(Spacing.class, JImGuiUIDataDeserializer::spacing);
//        classComponentHandlers.put(Text.class, JImGuiUIDataDeserializer::text);
//        classComponentHandlers.put(TextInput.class, JImGuiUIDataDeserializer::inputText);
//        classComponentHandlers.put(TreeNodeEx.class, JImGuiUIDataDeserializer::treeNodeEx);
//
//
//
//        // This will stay because its needed to structured the handling
//        classComponentHandlers.put(UIComponentArray.class, JImGuiUIDataDeserializer::jaweDrawables);
//
//
//        classComponentHandlers.put(UIApp.class, JImGuiUIDataDeserializer::uiApp);


// Editable

    }
}
// TODO All items


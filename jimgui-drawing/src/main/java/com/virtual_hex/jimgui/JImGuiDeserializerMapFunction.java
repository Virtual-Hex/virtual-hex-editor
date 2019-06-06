package com.virtual_hex.jimgui;

import com.virtual_hex.data.*;
import com.virtual_hex.jimgui.handlers.ButtonHandler;
import com.virtual_hex.jimgui.handlers.OpenableFlagHandler;
import com.virtual_hex.jimgui.handlers.UIAppHandler;
import org.ice1000.jimgui.JImGui;

import java.util.Map;
import java.util.function.Consumer;

public class JImGuiDeserializerMapFunction implements Consumer<Map<Class<?>, ComponentHandler<JImGui>>> {

    @Override
    public void accept(Map<Class<?>, ComponentHandler<JImGui>> typeDrawers) {
        // Non JImGui Specific Types
        typeDrawers.put(OpenableFlags.class, OpenableFlagHandler.INSTANCE);
        typeDrawers.put(UIApp.class, UIAppHandler.INSANCE);

        // JImGui Specific Types
        typeDrawers.put(Button.class, ButtonHandler.INSTANCE);



//        typeDrawers.put(BeginMenu.class, JImGuiUIDataDeserializer::beginMenu);

//
//        typeDrawers.put(CheckBox.class, JImGuiUIDataDeserializer::checkbox);
//        typeDrawers.put(ColorText.class, JImGuiUIDataDeserializer::colorText);
//        typeDrawers.put(Columns.class, JImGuiUIDataDeserializer::columns);
//        typeDrawers.put(Dummy.class, JImGuiUIDataDeserializer::dummy);
//        // TODO COMBO
//        typeDrawers.put(InputDouble.class, JImGuiUIDataDeserializer::inputDouble);
//        typeDrawers.put(InputDoubleStepped.class, JImGuiUIDataDeserializer::inputDoubleStepped);
//        typeDrawers.put(InputFloat.class, JImGuiUIDataDeserializer::inputFloat);
//        typeDrawers.put(InputFloatStepped.class, JImGuiUIDataDeserializer::inputFloatStepped);
//        typeDrawers.put(InputInt.class, JImGuiUIDataDeserializer::inputInt);
//        typeDrawers.put(InputIntStepped.class, JImGuiUIDataDeserializer::inputIntStepped);
//
//        typeDrawers.put(InvisibleButton.class, JImGuiUIDataDeserializer::invisibleButton);
//        // TODO MENU
//        // TODO MENU ITEM
//        typeDrawers.put(NewLine.class, JImGuiUIDataDeserializer::newLine);
//        typeDrawers.put(NextColumn.class, JImGuiUIDataDeserializer::nextColumn);
//        // TODO OPEN POPUP
//        typeDrawers.put(SameLine.class, JImGuiUIDataDeserializer::sameLine);
//        typeDrawers.put(Selectable.class, JImGuiUIDataDeserializer::selectable);
//        typeDrawers.put(Separator.class, JImGuiUIDataDeserializer::seperator);
//        typeDrawers.put(SmallButton.class, JImGuiUIDataDeserializer::smallButton);
//        typeDrawers.put(Spacing.class, JImGuiUIDataDeserializer::spacing);
//        typeDrawers.put(Text.class, JImGuiUIDataDeserializer::text);
//        typeDrawers.put(TextInput.class, JImGuiUIDataDeserializer::inputText);
//        typeDrawers.put(TreeNodeEx.class, JImGuiUIDataDeserializer::treeNodeEx);
//
//
//
//        // This will stay because its needed to structured the drawing
//        typeDrawers.put(UIComponentArray.class, JImGuiUIDataDeserializer::jaweDrawables);
//
//
//        typeDrawers.put(UIApp.class, JImGuiUIDataDeserializer::uiApp);


// Editable
        return typeDrawers;
    }
}
// TODO All items


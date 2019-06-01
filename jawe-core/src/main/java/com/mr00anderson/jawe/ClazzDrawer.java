package com.mr00anderson.jawe;

import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.drawers.DefaultJaweDrawers;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ClazzDrawer {

    protected Map<Class<?>, BiConsumer<JImGui, Object>> typeDrawer = new HashMap<>();
    // TODO Type and Field Type Drawer

    // Serialization can be on a class bases for drawing with this, but then alot of new refernces, similar to the static final issue
    public ClazzDrawer() {
        init();
    }

    private void init() {
        typeDrawer.put(JaweBeginMenu.class, DefaultJaweDrawers::drawBeginMenu);
        typeDrawer.put(JaweBeginMenuItem.class, DefaultJaweDrawers::drawBeginMenuItem);
        typeDrawer.put(JaweButton.class, DefaultJaweDrawers::drawButton);
        typeDrawer.put(JaweCheckBox.class, DefaultJaweDrawers::drawCheckbox);
        typeDrawer.put(JaweCollapsingHeader.class, DefaultJaweDrawers::drawCollapsingHeader);
        typeDrawer.put(JaweColorText.class, DefaultJaweDrawers::drawColorText);

        typeDrawer.put(JaweNewLine.class, DefaultJaweDrawers::drawNewLine);
        typeDrawer.put(JaweSameLine.class, DefaultJaweDrawers::drawSameLine);
        typeDrawer.put(JaweSelectable.class, DefaultJaweDrawers::drawSelectable);
        typeDrawer.put(JaweTreeNodeEx.class, DefaultJaweDrawers::drawTreeNodeEx);
        typeDrawer.put(JaweText.class, DefaultJaweDrawers::drawText);
        typeDrawer.put(JaweWindow.class, DefaultJaweDrawers::drawWindow);


        typeDrawer.put(String.class, DefaultJaweDrawers::drawText);
    }


    public void draw(JImGui imGui, Object objectToDraw) {
        Class<?> aClass = objectToDraw.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        int declaredFieldsLength = declaredFields.length;
        for (int i = 0; i < declaredFieldsLength; i++) {
            Field field = declaredFields[i];
            // Check field specific for type
            // Get generic type
            BiConsumer<JImGui, Object> jaweDrawable = typeDrawer.get(field.getType());
            if(jaweDrawable != null) {
                jaweDrawable.accept(imGui, jaweDrawable);
            }
        }
    }
}

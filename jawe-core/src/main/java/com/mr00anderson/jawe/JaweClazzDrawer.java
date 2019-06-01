package com.mr00anderson.jawe;

import com.mr00anderson.jawe.components.JaweOrderedDrawables;
import com.mr00anderson.jawe.components.WorldsJaweComponent;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.drawers.DefaultJaweDrawers;
import org.ice1000.jimgui.JImGui;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class JaweClazzDrawer {

    protected Map<Class<?>, BiConsumer<JImGui, Object>> typeDrawer = new HashMap<>();
    // TODO Type and Field Type Drawer

    // Serialization can be on a class bases for drawing with this, but then alot of new refernces, similar to the static final issue
    public JaweClazzDrawer() {
        init();
    }

    private void init() {
        typeDrawer.put(JaweBeginMenu.class, DefaultJaweDrawers::beginMenu);
        typeDrawer.put(JaweBeginMenuItem.class, DefaultJaweDrawers::beginMenuItem);
        typeDrawer.put(JaweButton.class, DefaultJaweDrawers::button);
        typeDrawer.put(JaweCheckBox.class, DefaultJaweDrawers::checkbox);
        typeDrawer.put(JaweCollapsingHeader.class, DefaultJaweDrawers::collapsingHeader);
        typeDrawer.put(JaweColorText.class, DefaultJaweDrawers::colorText);

        typeDrawer.put(JaweNewLine.class, DefaultJaweDrawers::newLine);
        typeDrawer.put(JaweSameLine.class, DefaultJaweDrawers::sameLine);
        typeDrawer.put(JaweSelectable.class, DefaultJaweDrawers::selectable);
        typeDrawer.put(JaweTreeNodeEx.class, DefaultJaweDrawers::treeNodeEx);
        typeDrawer.put(JaweText.class, DefaultJaweDrawers::text);
        typeDrawer.put(JaweWindow.class, DefaultJaweDrawers::window);

        typeDrawer.put(WorldsJaweComponent.class, DefaultJaweDrawers::worldsJaweComponent);
        typeDrawer.put(JaweOrderedDrawables.class, DefaultJaweDrawers::jaweOrderedDrawables);

    }


    public void draw(JImGui imGui, Object objectToDraw) {
        Class<?> aClass = objectToDraw.getClass();
        BiConsumer<JImGui, Object> orDefault = typeDrawer.getOrDefault(aClass, DefaultJaweDrawers::emptyDrawable);
        orDefault.accept(imGui, objectToDraw);
    }
}

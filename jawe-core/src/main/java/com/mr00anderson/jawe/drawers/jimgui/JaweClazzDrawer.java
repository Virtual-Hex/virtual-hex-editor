package com.mr00anderson.jawe.drawers.jimgui;

import com.mr00anderson.jawe.drawables.*;
import org.ice1000.jimgui.JImGui;

import java.util.*;
import java.util.function.BiConsumer;


/**
 * Depends on JImGui // Could be extracted out one layer and friendly to any backend in the future
 */
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
        typeDrawer.put(JaweColumns.class, DefaultJaweDrawers::columns);
        // TODO COMBO
        typeDrawer.put(JaweDummy.class, DefaultJaweDrawers::dummy);
        typeDrawer.put(JaweInvisibleButton.class, DefaultJaweDrawers::invisibleButton);
        // TODO MENU
        // TODO MENU ITEM
        typeDrawer.put(JaweNewLine.class, DefaultJaweDrawers::newLine);
        typeDrawer.put(JaweNextColumn.class, DefaultJaweDrawers::nextColumn);
        // TODO OPEN POPUP
        typeDrawer.put(JaweSameLine.class, DefaultJaweDrawers::sameLine);
        typeDrawer.put(JaweSelectable.class, DefaultJaweDrawers::selectable);
        typeDrawer.put(JaweSeparator.class, DefaultJaweDrawers::seperator);
        typeDrawer.put(JaweSmallButton.class, DefaultJaweDrawers::smallButton);
        typeDrawer.put(JaweSpacing.class, DefaultJaweDrawers::spacing);
        // TODO TAB
        typeDrawer.put(JaweText.class, DefaultJaweDrawers::text);
        typeDrawer.put(JaweTreeNodeEx.class, DefaultJaweDrawers::treeNodeEx);
        typeDrawer.put(JaweTreeNodeExNoPop.class, DefaultJaweDrawers::treeNodeExNoPop);
        typeDrawer.put(JaweWindow.class, DefaultJaweDrawers::window);

        // This will stay because its needed to structured the drawing
        typeDrawer.put(JaweDrawables.class, DefaultJaweDrawers::jaweOrderedDrawables);

        typeDrawer.put(ArrayList.class, this::list);
        typeDrawer.put(LinkedList.class, this::list);

        // Todo determine way to get subtypes, look at serialization libs, so we dont have to register everything
        // when we can just register a subtype

        typeDrawer.put(List.class, this::list);
    }


    public void draw(JImGui imGui, Object objectToDraw) {
        Class<?> aClass = objectToDraw.getClass();
        BiConsumer<JImGui, Object> objectDrawer = typeDrawer.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
        objectDrawer.accept(imGui, objectToDraw);
    }

    public void list(JImGui imGui, Object drawable0) {
        List list = (List) drawable0;
        list.forEach(d -> draw(imGui, d));
    }

    // Check iterations and sub-typing through testing
    public BiConsumer<JImGui, Object> checkSubtype(Class<?> aSubTypeClazz) {
        BiConsumer<JImGui, Object> biConsumer = typeDrawer.get(aSubTypeClazz);
        return biConsumer == null ? DefaultJaweDrawers::emptyDrawable : biConsumer;
    }
}

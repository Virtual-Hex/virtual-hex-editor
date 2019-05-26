package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.*;
import com.mr00anderson.jawe.types.Disposable;
import com.mr00anderson.jawe.types.InstancePurge;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.ice1000.jimgui.JImGui.nextColumn;
import static org.ice1000.jimgui.JImGui.separator;


/**
 * Per world instance, This must be disposed of, this draws fields on an object
 */
public class JaweClazzDraw implements Disposable, InstancePurge {

    // Should turn this into a proper drawable? if i did then it would need to have a instance for each entity
    // TODO
    // world reference
    // entity reference
    //  Would have to make the data types elevated and this into a manager of worlds, entities,
    // then this could be a proper drawable that can be encapsulated into a window for
    // serialization

    /**
     * Will share this out and allow overriding of type mappings
     */
    protected Map<Class<?>, JImGuiTypeHandler> typeHandlerMap = new HashMap<>();

    public JaweClazzDraw() {
        init();
    }

    private void init() {
        typeHandlerMap.put(boolean.class, new DefaultJImGuiBooleanPrimTypeHandler());
        typeHandlerMap.put(byte.class, new DefaultJImGuiBytePrimTypeHandler());
        typeHandlerMap.put(short.class, new DefaultJImGuiShortPrimTypeHandler());
        typeHandlerMap.put(int.class, new DefaultJImGuiIntPrimTypeHandler());
        typeHandlerMap.put(long.class, new DefaultJImGuiLongPrimTypeHandler());
        typeHandlerMap.put(float.class, new DefaultJImGuiFloatPrimTypeHandler());
        typeHandlerMap.put(double.class, new DefaultJImGuiDoublePrimTypeHandler());
        typeHandlerMap.put(String.class, new DefaultJImGuiStringPrimTypeHandler());
    }

    @Override
    public void dispose() {
        typeHandlerMap.forEach((k,v) -> v.dispose());
        typeHandlerMap.clear();
    }

    @Override
    public void purge(int instance){
        typeHandlerMap.forEach((k,v) -> v.purge(instance));
    }

    public void drawSlowJavaClazz(JImGui imGui, int instanceId, Object objectToDraw) {
        Class<?> aClass = objectToDraw.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        // TODO Need to refresh variables or not use caching anymore with the get because of changinging the data other then the UI will not refresh in the UI
        imGui.columns(3); // 4-ways, with border
        separator();
        imGui.text("Value & Field Name");
        nextColumn();
        imGui.text("Type");
        nextColumn();
        imGui.text("Size (b)");
        nextColumn();
        separator();

        int declaredFieldsLength = declaredFields.length;
        for (int i = 0; i < declaredFieldsLength; i++) {
            Field field = declaredFields[i];

            Class<?> clazz = field.getType();
            typeHandlerMap.computeIfPresent(clazz, (aClass1, jImGuiTypeHandler) -> {
                jImGuiTypeHandler.handle(imGui, declaredFieldsLength, field, instanceId, objectToDraw);
                return jImGuiTypeHandler;
            });
        }
    }
}

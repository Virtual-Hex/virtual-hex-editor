package com.mr00anderson.jawe;

import com.mr00anderson.data.Drawables;
import com.mr00anderson.jawe.handlers.*;
import com.mr00anderson.jawe.types.InstancePurge;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * Per worldWrapper instance, This must be disposed of, this draws fields on an object
 */
public class EditableClazzDraw extends Drawables implements InstancePurge {

    // Reflective class drawer


    // Should make this an entity drawers instead, with a prescan of fields for type handling?

    // Should turn this into a proper drawable? if i did then it would need to have a instance for each entity
    // TODO
    // worldWrapper reference
    // entity reference
    //  Would have to make the nativeData types elevated and this into a manager of worlds, entities,
    // then this could be a proper drawable that can be encapsulated into a window for
    // serialization

    /**
     * Will share this out and allow overriding of type mappings
     */
    protected Map<Class<?>, JImGuiTypeHandler> typeHandlerMap = new HashMap<>();



    // Serialization can be on a class bases for drawing with this, but then alot of new refernces, similar to the static final issue
    public EditableClazzDraw() {
        init();
    }

    private void init() {

        // TODO use default static final serializers but then optional override
        typeHandlerMap.put(boolean.class, new DefaultJImGuiBooleanPrimTypeHandler());
        typeHandlerMap.put(byte.class, new DefaultJImGuiBytePrimTypeHandler());
        typeHandlerMap.put(short.class, new DefaultJImGuiShortPrimTypeHandler());
        typeHandlerMap.put(int.class, new DefaultJImGuiIntPrimTypeHandler());
        typeHandlerMap.put(long.class, new DefaultJImGuiLongPrimTypeHandler());
        typeHandlerMap.put(float.class, new DefaultJImGuiFloatPrimTypeHandler());
        typeHandlerMap.put(double.class, new DefaultJImGuiDoublePrimTypeHandler());
        typeHandlerMap.put(String.class, new DefaultJImGuiStringPrimTypeHandler());


    }


    // TODO move this, since native types will be managed by editor automatically
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

        // TODO Need to refresh variables or not use caching anymore with the get because of changinging the nativeData other then the UI will not refresh in the UI
        imGui.columns(3); // 4-ways, with border
        JImGuiGen.separator();
        imGui.text("Value & Field Name");
        JImGuiGen.nextColumn();
        imGui.text("Type");
        JImGuiGen.nextColumn();
        imGui.text("Size (b)");
        JImGuiGen.nextColumn();
        JImGuiGen.separator();

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


    public void draw(JImGui imGui) {

    }
}

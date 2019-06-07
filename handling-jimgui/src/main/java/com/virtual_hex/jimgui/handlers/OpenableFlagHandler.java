package com.virtual_hex.jimgui.handlers;

import com.virtual_hex.data.OpenableFlags;
import com.virtual_hex.data.UIComponent;
import com.virtual_hex.data.UIComponentArray;
import com.virtual_hex.handling.ComponentHandler;
import com.virtual_hex.handling.UIDeserializer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.apache.commons.lang.ArrayUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class OpenableFlagHandler implements ComponentHandler<JImGui> {

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, NativeBool>>> cachedBools = new HashMap<>();

    public static transient DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @return
     */
    public NativeBool createBool(){
        NativeBool nativeValue = new NativeBool();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    @Override
    public void draw(JImGui ui, UIComponent uiComponent, UIDeserializer parentDeserializer) {
        OpenableFlags openable = (OpenableFlags) uiComponent;
        switch (openable.type) {
            case WINDOW: {
                if(openable.open){
                    // Not clipped or collapsed
                    boolean visible = ui.begin(openable.label);
                    if(visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                    }
                    ui.end();
                }
                break;
            }
            case WINDOW_EXITABLE: {
                if (openable.open) {
                    NativeBool value = getNativeBool("open", openable);
                    // Not clipped or collapsed
                    value.modifyValue(openable.open);
                    boolean visible = ui.begin(openable.label, value, openable.flags);
                    openable.open = value.accessValue();
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                    }
                    ui.end();
                }
                break;
            }
            case TAB_BAR: {
                if (openable.open) {
                    // Not clipped or collapsed
                    boolean visible = ui.begin(openable.label);
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                        // We want to let the type specific know its time to pop out
                        ui.endTabBar();
                    }
                }
                break;
            }
            case TAB_ITEM: {
                if (openable.open) {
                    // Not clipped or collapsed
                    boolean visible = ui.begin(openable.label);
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                        // We want to let the type specific know its time to pop out
                        ui.endTabItem();
                    }
                }
                break;
            }
            case TAB_ITEM_EXITABLE: {
                if(openable.open){
                    NativeBool value = getNativeBool("open", openable);
                    // Not clipped or collapsed
                    value.modifyValue(openable.open);
                    boolean visible = ui.begin(openable.label, value, openable.flags);
                    openable.open = value.accessValue();
                    if(visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                        // We want to let the type specific know its time to pop out
                        ui.endTabItem();
                    }
                }
                break;

            }
            case COLLAPSABLE_HEADER: {
                if (openable.open) {
                    // Not clipped or collapsed
                    boolean visible = ui.begin(openable.label);
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                        // We want to let the type specific know its time to pop out
                    }
                }
                break;
            }
            case COLLAPSABLE_HEADER_EXITABLE: {
                if(openable.open){
                    NativeBool value = getNativeBool("open", openable);
                    // Not clipped or collapsed
                    value.modifyValue(openable.open);
                    boolean visible = ui.begin(openable.label, value, openable.flags);
                    openable.open = value.accessValue();
                    if(visible) { // TODO Maybe remove this open check
                        processUiDataList(ui, openable.uiDataArray, parentDeserializer);
                        // We want to let the type specific know its time to pop out
                    }
                }
                break;
            }
        }
    }

    public NativeBool getNativeBool(String fieldName, Object object){
        return cachedBools
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, name -> createBool());
    }

    private static void processUiDataList(JImGui imGui, UIComponentArray drawable, UIDeserializer parentDrawer) {
        processUiDataList(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    private static void processUiDataList(JImGui imGui, UIComponent[] drawables, Queue<UIComponent> addWindowQueue, Queue<UIComponent> removeWindowQueue, UIDeserializer parentDrawer) {
        for (UIComponent element; (element = addWindowQueue.poll()) != null;){
            ArrayUtils.add(drawables, element);
        }

        for (UIComponent element; (element = removeWindowQueue.poll()) != null;){
            ArrayUtils.removeElement(drawables, element);
        }
        for (int i = 0; i < drawables.length; i++) {
            UIComponent drawable = drawables[i];
            if(drawable != null){
                parentDrawer.draw(imGui, drawable);
            }
        }
    }
}

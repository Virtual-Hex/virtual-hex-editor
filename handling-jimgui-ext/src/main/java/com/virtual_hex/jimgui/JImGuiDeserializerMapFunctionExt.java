package com.virtual_hex.jimgui;

import com.virtual_hex.data.UIComponent;
import com.virtual_hex.data.ext.*;
import org.ice1000.jimgui.JImGui;

import java.util.Map;
import java.util.function.Consumer;

public class JImGuiDeserializerMapFunctionExt implements Consumer<Map<Class<?>, ComponentHandler<JImGui>>> {

    @Override
    public void accept(Map<Class<?>, ComponentHandler<JImGui>> typeDrawers) {

        typeDrawers.put(ColumnSet.class, JImGuiDeserializerMapFunctionExt::columnSet);
        typeDrawers.put(ColumnSetHeader.class, JImGuiDeserializerMapFunctionExt::columnSetHeader);
        typeDrawers.put(ColumnSetBody.class, JImGuiDeserializerMapFunctionExt::columnSetBody);
        typeDrawers.put(ColumnSetRow.class, JImGuiDeserializerMapFunctionExt::columnSetBodyRow);

        typeDrawers.put(ShowHelpMarker.class, JImGuiDeserializerMapFunctionExt::showHelpMarker);

        // TODO Remove This is for pulling up to world level through a plugin for Artemis, but here for a reminder
//        classComponentHandlers.classComponentHandlers.put(WorldSelectable.class, JImGuiUIDataDeserializer::selectable);
    }

    /**
     *
     * This can be used to make anything hoverable. Needs to have two drawbles, one that is hoovered, and one that
     * is displayed on it, such as a Text and a Text. new HelpMarker("?", - "This is a help marker");
     *
     * @param imGui
     * @param uiComponent
     * @param parentDrawer
     */
    public static void showHelpMarker(JImGui imGui, UIComponent uiComponent, UIDataDeserializer parentDrawer) {
        ShowHelpMarker drawable = (ShowHelpMarker) uiComponent;
        parentDrawer.draw(imGui, drawable.helpMarker, parentDrawer);
        if (imGui.isItemHovered()) {
            imGui.beginTooltip();
            imGui.pushTextWrapPos(imGui.getFontSize() * 35.0f);
            parentDrawer.draw(imGui, drawable.data, parentDrawer);
            imGui.popTextWrapPos();
            imGui.endTooltip();
        }
    }

    private static void columnSet(JImGui imGui, UIComponent uiComponent, UIDataDeserializer parentDrawer) {
        ColumnSet drawable = (ColumnSet) uiComponent;
        columnSetHeader(imGui, drawable.header, parentDrawer);
        columnSetBody(imGui, drawable.body, parentDrawer);
    }

    public static void columnSetHeader(JImGui imGui, UIComponent uiComponent, UIDataDeserializer parentDrawer){
        ColumnSetHeader drawable = (ColumnSetHeader) uiComponent;
        int length = drawable.columns.length;
        imGui.columns(length, drawable.stringId, drawable.border);
        if(drawable.headerSeparatorTop) imGui.separator();
        for (int i = 0; i < length; i++) {
            UIComponent column = drawable.columns[i];
            parentDrawer.draw(imGui, column, parentDrawer);
            imGui.nextColumn();
        }
        if(drawable.headerSeparatorBottom) imGui.separator();
    }

    private static void columnSetBody(JImGui imGui, UIComponent uiComponent, UIDataDeserializer parentDrawer) {
        ColumnSetBody drawable = (ColumnSetBody) uiComponent;
        int length = drawable.rows.length;
        for (int i = 0; i < length; i++) {
            UIComponent column = drawable.rows[i];
            parentDrawer.draw(imGui, column, parentDrawer);
        }
    }

    private static void columnSetBodyRow(JImGui imGui, UIComponent uiComponent, UIDataDeserializer parentDrawer) {
        ColumnSetRow drawable = (ColumnSetRow) uiComponent;
        int length = drawable.columns.length;
        for (int i = 0; i < length; i++) {
            UIComponent column = drawable.columns[i];
            parentDrawer.draw(imGui, column, parentDrawer);
            imGui.nextColumn();
        }
    }

}

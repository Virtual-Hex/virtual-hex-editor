package com.virtual_hex.jimgui;

import com.virtual_hex.data.UIData;
import com.virtual_hex.data.UIDataDeserializer;
import com.virtual_hex.data.ext.*;
import org.ice1000.jimgui.JImGui;

public class JImGuiUIDataDeserializerExt<T extends JImGuiUIDataDeserializerExt> extends JImGuiUIDataDeserializer<T> {

    public JImGuiUIDataDeserializerExt() {
    }

    public JImGuiUIDataDeserializerExt(boolean useDefaultDrawer, String name) {
        super(useDefaultDrawer, name);
    }

    @Override
    public void init() {
        super.init();
        typeDrawers.put(ShowHelpMarker.class, JImGuiUIDataDeserializerExt::showHelpMarker);
        typeDrawers.put(ColumnSet.class, JImGuiUIDataDeserializerExt::columnSet);
        typeDrawers.put(ColumnSetHeader.class, JImGuiUIDataDeserializerExt::columnSetHeader);
        typeDrawers.put(ColumnSetBody.class, JImGuiUIDataDeserializerExt::columnSetBody);
        typeDrawers.put(ColumnSetRow.class, JImGuiUIDataDeserializerExt::columnSetBodyRow);

        // This is for pulling up to world level
//        typeDrawers.typeDrawers.put(WorldSelectable.class, JImGuiUIDataDeserializer::selectable);
    }

    /**
     *
     * This can be used to make anything hoverable. Needs to have two drawbles, one that is hoovered, and one that
     * is displayed on it, such as a Text and a Text. new HelpMarker("?", - "This is a help marker");
     *
     * @param imGui
     * @param uiData
     * @param parentDrawer
     */
    public static void showHelpMarker(JImGui imGui, UIData uiData, UIDataDeserializer parentDrawer) {
        ShowHelpMarker drawable = (ShowHelpMarker) uiData;
        parentDrawer.draw(imGui, drawable.helpMarker, parentDrawer);
        if (imGui.isItemHovered()) {
            imGui.beginTooltip();
            imGui.pushTextWrapPos(imGui.getFontSize() * 35.0f);
            parentDrawer.draw(imGui, drawable.data, parentDrawer);
            imGui.popTextWrapPos();
            imGui.endTooltip();
        }
    }

    private static void columnSet(JImGui imGui, UIData uiData, UIDataDeserializer parentDrawer) {
        ColumnSet drawable = (ColumnSet) uiData;
        columnSetHeader(imGui, drawable.header, parentDrawer);
        columnSetBody(imGui, drawable.body, parentDrawer);
    }

    public static void columnSetHeader(JImGui imGui, UIData uiData, UIDataDeserializer parentDrawer){
        ColumnSetHeader drawable = (ColumnSetHeader) uiData;
        int length = drawable.columns.length;
        imGui.columns(length, drawable.stringId, drawable.border);
        if(drawable.headerSeparatorTop) imGui.separator();
        for (int i = 0; i < length; i++) {
            UIData column = drawable.columns[i];
            parentDrawer.draw(imGui, column, parentDrawer);
            imGui.nextColumn();
        }
        if(drawable.headerSeparatorBottom) imGui.separator();
    }

    private static void columnSetBody(JImGui imGui, UIData uiData, UIDataDeserializer parentDrawer) {
        ColumnSetBody drawable = (ColumnSetBody) uiData;
        int length = drawable.rows.length;
        for (int i = 0; i < length; i++) {
            UIData column = drawable.rows[i];
            parentDrawer.draw(imGui, column, parentDrawer);
        }
    }

    private static void columnSetBodyRow(JImGui imGui, UIData uiData, UIDataDeserializer parentDrawer) {
        ColumnSetRow drawable = (ColumnSetRow) uiData;
        int length = drawable.columns.length;
        for (int i = 0; i < length; i++) {
            UIData column = drawable.columns[i];
            parentDrawer.draw(imGui, column, parentDrawer);
            imGui.nextColumn();
        }
    }
}



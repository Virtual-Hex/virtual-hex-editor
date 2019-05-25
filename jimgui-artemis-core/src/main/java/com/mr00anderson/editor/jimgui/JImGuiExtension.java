package com.mr00anderson.editor.jimgui;

import org.ice1000.jimgui.JImGui;

public class JImGuiExtension extends JImGui {

    public static void showHelpMarker(JImGui imGui, String description) {
        imGui.textDisabled("(?)");
        if (imGui.isItemHovered()) {
            imGui.beginTooltip();
            imGui.pushTextWrapPos(imGui.getFontSize() * 35.0f);
            imGui.text(description);
            imGui.popTextWrapPos();
            imGui.endTooltip();
        }
    }

    public static String f(String format, Object... objects) {
        return String.format(format, objects);
    }
}

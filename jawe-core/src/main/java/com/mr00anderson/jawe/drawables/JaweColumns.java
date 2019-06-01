package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 *
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L563
 * // Columns
 * // - You can also use SameLine(pos_x) to mimic simplified columns.
 * // - The columns API is work-in-progress and rather lacking (columns are arguably the worst part of dear imgui at the moment!)
 */
public class JaweColumns {

    public String stringId;
    public int count;
    public boolean border;

    public JaweColumns() {
    }



    public JaweColumns(String stringId, int count, boolean border) {
        this.stringId = stringId;
        this.count = count;
        this.border = border;
    }


    public void draw(JImGui imGui) {
        imGui.columns(count, stringId, border);
    }

}

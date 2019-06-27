package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L563
 * Columns
 * - You can also use SameLine(pos_x) to mimic simplified rows.
 * - The rows API is work-in-progress and rather lacking (rows are arguably the worst part of dear imgui at the moment!)
 */
public class Columns extends AbstractUIComponent {

    public String stringId;
    public int count;
    public boolean border;

    public Columns() {
    }

    public Columns(String stringId, int count, boolean border) {
        this.stringId = stringId;
        this.count = count;
        this.border = border;
    }
}

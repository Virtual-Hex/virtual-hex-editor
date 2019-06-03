package com.mr00anderson.jawe.drawables;

/**
 *
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L563
 * // Columns
 * // - You can also use SameLine(pos_x) to mimic simplified rows.
 * // - The rows API is work-in-progress and rather lacking (rows are arguably the worst part of dear imgui at the moment!)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweColumns that = (JaweColumns) o;

        if (count != that.count) return false;
        if (border != that.border) return false;
        return stringId != null ? stringId.equals(that.stringId) : that.stringId == null;
    }

    @Override
    public int hashCode() {
        int result = stringId != null ? stringId.hashCode() : 0;
        result = 31 * result + count;
        result = 31 * result + (border ? 1 : 0);
        return result;
    }
}

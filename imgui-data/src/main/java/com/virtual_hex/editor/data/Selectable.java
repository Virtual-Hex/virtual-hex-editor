package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
 */
public class Selectable<LABEL> extends BoxInt<LABEL> {

    public boolean selected;
    public int flags;

    public Selectable() {
    }

    public Selectable(LABEL label) {
        super(label);
    }

    public Selectable(LABEL label, boolean selected) {
        super(label);
        this.selected = selected;
    }

    public Selectable(LABEL label, boolean selected, int flags) {
        super(label);
        this.selected = selected;
        this.flags = flags;
    }

    public Selectable(LABEL label, int width, int height, boolean selected, int flags) {
        super(label, width, height);
        this.selected = selected;
        this.flags = flags;
    }

    public static Selectable[] fromStrings(String... strings){
        Selectable[] selectables = new Selectable[strings.length];
        for (int i = 0; i < strings.length; i++) {
            selectables[i] = new Selectable(strings[i]);
        }
        return selectables;
    }
}

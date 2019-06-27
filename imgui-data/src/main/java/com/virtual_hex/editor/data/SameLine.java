package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L338
 * <p>
 * call between widgets or groups to layout them horizontally. X position given in window coordinates.
 */
public class SameLine extends AbstractUIComponent {

    public float positionX;
    public float spacingWidth = -1;

    public SameLine() {
    }

    public SameLine(float positionX) {
        this.positionX = positionX;
    }

    public SameLine(float positionX, float spacingWidth) {
        this.positionX = positionX;
        this.spacingWidth = spacingWidth;
    }
}

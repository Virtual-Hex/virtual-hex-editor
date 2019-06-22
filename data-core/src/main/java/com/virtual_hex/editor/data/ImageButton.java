package com.virtual_hex.editor.data;

public class ImageButton<T> extends Image {

    public int framePadding;

    public ImageButton() {
    }

    public ImageButton(String label, Object from) {
        super(label, from);
    }

    public ImageButton(String label, int width, int height, Object from) {
        super(label, width, height, from);
    }

    public ImageButton(String label, Object from, float uv0x, float uv0y, float uv1x, float uv1y) {
        super(label, from, uv0x, uv0y, uv1x, uv1y);
    }

    public ImageButton(String label, int width, int height, Object from, float uv0x, float uv0y, float uv1x, float uv1y) {
        super(label, width, height, from, uv0x, uv0y, uv1x, uv1y);
    }

    public ImageButton(String label, Object from, int framePadding) {
        super(label, from);
        this.framePadding = framePadding;
    }

    public ImageButton(String label, int width, int height, Object from, int framePadding) {
        super(label, width, height, from);
        this.framePadding = framePadding;
    }

    public ImageButton(String label, Object from, float uv0x, float uv0y, float uv1x, float uv1y, int framePadding) {
        super(label, from, uv0x, uv0y, uv1x, uv1y);
        this.framePadding = framePadding;
    }

    public ImageButton(String label, int width, int height, Object from, float uv0x, float uv0y, float uv1x, float uv1y, int framePadding) {
        super(label, width, height, from, uv0x, uv0y, uv1x, uv1y);
        this.framePadding = framePadding;
    }
}

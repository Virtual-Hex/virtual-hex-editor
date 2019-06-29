package com.virtual_hex.editor.data;

public class ImageButton<LABEL, IMAGE_PATH> extends Image<LABEL, IMAGE_PATH> {

    public int framePadding;

    public ImageButton() {
    }

    public ImageButton(LABEL label, IMAGE_PATH from) {
        super(label, from);
    }

    public ImageButton(LABEL label, int width, int height, IMAGE_PATH from) {
        super(label, width, height, from);
    }

    public ImageButton(LABEL label, IMAGE_PATH from, float uv0x, float uv0y, float uv1x, float uv1y) {
        super(label, from, uv0x, uv0y, uv1x, uv1y);
    }

    public ImageButton(LABEL label, int width, int height, IMAGE_PATH from, float uv0x, float uv0y, float uv1x, float uv1y) {
        super(label, width, height, from, uv0x, uv0y, uv1x, uv1y);
    }

    public ImageButton(LABEL label, IMAGE_PATH from, int framePadding) {
        super(label, from);
        this.framePadding = framePadding;
    }

    public ImageButton(LABEL label, int width, int height, IMAGE_PATH from, int framePadding) {
        super(label, width, height, from);
        this.framePadding = framePadding;
    }

    public ImageButton(LABEL label, IMAGE_PATH from, float uv0x, float uv0y, float uv1x, float uv1y, int framePadding) {
        super(label, from, uv0x, uv0y, uv1x, uv1y);
        this.framePadding = framePadding;
    }

    public ImageButton(LABEL label, int width, int height, IMAGE_PATH from, float uv0x, float uv0y, float uv1x, float uv1y, int framePadding) {
        super(label, width, height, from, uv0x, uv0y, uv1x, uv1y);
        this.framePadding = framePadding;
    }
}

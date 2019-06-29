package com.virtual_hex.editor.data;

public class Image<LABEL, IMAGE_PATH> extends BoxInt<LABEL> {

    public IMAGE_PATH from;
    public float uv0x;
    public float uv0y;
    public float uv1x;
    public float uv1y;

    public Image() {
        setup();
    }

    public Image(LABEL label, IMAGE_PATH from) {
        super(label);
        this.from = from;
        setup();
    }

    public Image(LABEL label, int width, int height, IMAGE_PATH from) {
        super(label, width, height);
        this.from = from;
        setup();
    }

    public Image(LABEL label, IMAGE_PATH from, float uv0x, float uv0y, float uv1x, float uv1y) {
        super(label);
        this.from = from;
        this.uv0x = uv0x;
        this.uv0y = uv0y;
        this.uv1x = uv1x;
        this.uv1y = uv1y;
    }

    public Image(LABEL label, int width, int height, IMAGE_PATH from, float uv0x, float uv0y, float uv1x, float uv1y) {
        super(label, width, height);
        this.from = from;
        this.uv0x = uv0x;
        this.uv0y = uv0y;
        this.uv1x = uv1x;
        this.uv1y = uv1y;
    }

    private void setup(){
        this.uv1x = 1;
        this.uv1y = 1;
    }
}

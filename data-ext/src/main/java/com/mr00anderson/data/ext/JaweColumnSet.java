package com.mr00anderson.data.ext;

public class JaweColumnSet {

    public JaweColumnSetHeader header;
    public JaweColumnSetBody body;

    public JaweColumnSet() {
    }

    public JaweColumnSet(JaweColumnSetHeader header, JaweColumnSetBody body) {
        this.header = header;
        this.body = body;
    }
}

package com.mr00anderson.jawe;

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

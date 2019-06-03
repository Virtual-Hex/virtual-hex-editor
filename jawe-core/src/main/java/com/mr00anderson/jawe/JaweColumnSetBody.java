package com.mr00anderson.jawe;

public class JaweColumnSetBody {

    // this must be divisible by the JaweColumnSetHeader
    public JaweColumnSetRow[] rows;

    // TODO horizontal seps
    public boolean seperators;

    public JaweColumnSetBody() {
    }

    public JaweColumnSetBody(JaweColumnSetRow... rows) {
        this.rows = rows;
    }
}

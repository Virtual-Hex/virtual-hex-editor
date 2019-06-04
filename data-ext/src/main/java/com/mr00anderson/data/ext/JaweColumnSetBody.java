package com.mr00anderson.data.ext;

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

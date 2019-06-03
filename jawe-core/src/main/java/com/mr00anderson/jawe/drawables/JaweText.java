package com.mr00anderson.jawe.drawables;

/**
 * simple formatted text
 */
public class JaweText  {

    public String text;

    public JaweText() {
    }

    public JaweText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweText jaweText = (JaweText) o;

        return text != null ? text.equals(jaweText.text) : jaweText.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}

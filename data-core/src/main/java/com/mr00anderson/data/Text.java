package com.mr00anderson.data;

/**
 * simple formatted text
 */
public class Text {

    public String text;

    public Text() {
    }

    public Text(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        return this.text != null ? this.text.equals(text.text) : text.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}

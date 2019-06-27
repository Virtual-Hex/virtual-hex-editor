package com.virtual_hex.editor.data;

public class ShowHelpMarker extends AbstractUIComponent {

    public UIComponent helpMarker;
    public UIComponent data;

    public ShowHelpMarker() {
    }

    public ShowHelpMarker(String helpMarker, String data) {
        this.helpMarker = new Text(helpMarker);
        this.data = new Text(data);
    }

    public ShowHelpMarker(UIComponent helpMarker, UIComponent data) {
        this.helpMarker = helpMarker;
        this.data = data;
    }
}

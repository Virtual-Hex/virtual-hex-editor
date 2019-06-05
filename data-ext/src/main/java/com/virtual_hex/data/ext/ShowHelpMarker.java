package com.virtual_hex.data.ext;

import com.virtual_hex.data.Text;
import com.virtual_hex.data.UIComponent;

public class ShowHelpMarker extends UIComponent {

    public UIComponent helpMarker;
    public UIComponent data;

    public ShowHelpMarker() {
    }

    public ShowHelpMarker(String helpMarker, String data) {
        this.helpMarker = new Text( helpMarker);
        this.data = new Text(data);
    }

    public ShowHelpMarker(UIComponent helpMarker, UIComponent data) {
        this.helpMarker = helpMarker;
        this.data = data;
    }
}

package com.virtual_hex.data.ext;

import com.virtual_hex.data.Text;
import com.virtual_hex.data.UIData;

public class ShowHelpMarker extends UIData {

    public UIData helpMarker;
    public UIData data;

    public ShowHelpMarker() {
    }

    public ShowHelpMarker(String helpMarker, String data) {
        this.helpMarker = new Text( helpMarker);
        this.data = new Text(data);
    }

    public ShowHelpMarker(UIData helpMarker, UIData data) {
        this.helpMarker = helpMarker;
        this.data = data;
    }
}

package com.virtual_hex.editor;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to run the editor
 */
public class EditorConfiguration {

    // Used to initially or upon restart configure the editor to a preferred setup
    // Also the Widgets

    public List<UIWriter> writers;

//    public List<UIWriterHolder>


    public EditorConfiguration() {
        writers = new ArrayList<>();

    }

    // Merge configurations
}

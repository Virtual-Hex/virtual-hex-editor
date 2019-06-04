package com.virtual_hex;

import com.virtual_hex.data.*;
import com.virtual_hex.jimgui.JImGuiApp;
import com.virtual_hex.jimgui.JImGuiUIDataDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Will need some reflective nativeData entities to be able to render IDs to names, ect since
 * TODO: Pluggable Serialization
 *
 * Note: As of now the intent is that Entities translate to a widget.
 *          -> Entities have a main render component
 *          -> Entities should be added in the order of rendering
 *          -> Entities will have one component for rendering and in that
 *              their may be a organization of how nativeData is added, Example:
 *              menu bar process can be a linked list of Drawable's
 *
 *
 *
 * When editor is loaded...
 * project = worlds // May be more then one project
 * worlds = entities
 * entities = component
 *
 *  TODO- Replace linked list with fixed but resizable array to reduced linked list garbage
 */
public final class VirtualHexDesktopEditor {

    /**
     * Simply a Logger Reference
     */
    private static final Logger L = LoggerFactory.getLogger(VirtualHexDesktopEditor.class);

    public static VirtualHexDesktopEditor INSTANCE;

    public JImGuiApp imGuiApp;

    public static void main(String[] args) {
        // TODO jimgui.ini loading so users can import layouts
        for (int i = 0; i < args.length; i++) {
        }
        INSTANCE = new VirtualHexDesktopEditor();
        INSTANCE.run();
    }

    public void run() {
        // Deserialize a UIApp here
        // PROJECT FORMAT which will have a new child first loader
        // Project will have a a child first loader as well
        // Load Editor
        // Load Projects


        Object editorLoader = null;
        if(editorLoader != null){
            imGuiApp = new JImGuiApp(
                // TODO
            );
        } else {
            imGuiApp = new JImGuiApp(
                    new UIApp("Virtual Hex Editor", 1280, 720,
                            new UIDeserializerWrapper(
                                    JImGuiUIDataDeserializer.DEFAULT_UI_DATA_DESERIALIZER,
                                    new Window("Debug", true, 0, new UIDataList(new Text("Todo")))
                            )
                    )
            );
        }

        // Initialize JImGui Instance
        imGuiApp.init();

        imGuiApp.loop();

        // Save Projects
        // Save Editor

        // Release editor resources
        if(imGuiApp != null) {
            imGuiApp.close();
        }
    }
}

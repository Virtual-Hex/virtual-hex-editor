package com.virtual_hex.editor;

import com.virtual_hex.editor.data.*;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;

import static com.virtual_hex.editor.VirtualHexDesktopEditor.*;

public class EditorMainMenu extends MainMenuBar {

    public EditorMainMenu(VirtualHexDesktopEditor editor, DefaultUIWriter writer) {
        super(
                new Menu("File", writer.createAction(new MenuItem("Exit"), new RunnableActivationHandler<>(() -> editor.shouldClose  = true))),
                new Menu("Tools",
                        writer.cToggleGroup(FieldNames.SELECTED, W_UI_PLUGINS, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable("UI Plugins")),
                        writer.cToggleGroup(FieldNames.SELECTED, W_PROJECTS, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable("Projects"))
                    ),
                new Menu("Quick Task", writer.createAction(new MenuItem("Clear all windows"), new RunnableActivationHandler<>(() -> writer.toggleGroup(EDITOR_ALL_WINDOWS, false)))),
                new Menu("Help",
                        new Text("Coming soon..."),
                        new Text("A Game editor by Virtual Hex Games, development@virtual-hex.com"),
                        new Separator(),
                        new Text("ImGui # Built-in Windows"),
                        new Separator(),
                        // This may seem complicated at first, here we are creating a window and adding it to the root app, when the method
                        // returns it returns the window, which is used in the write.createOneWayBoolFieldLink to link the value mechanism item to the toggleable
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_ABOUT, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable("About")),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_DEMO, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable("Demo")),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable("Metrics"))
                )
        );
    }

    class FileMenU extends Menu {
        public FileMenU() {
            super("File");
        }
    }
}

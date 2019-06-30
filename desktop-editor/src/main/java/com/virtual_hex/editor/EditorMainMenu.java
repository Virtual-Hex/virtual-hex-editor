package com.virtual_hex.editor;

import com.virtual_hex.editor.data.*;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import org.ice1000.jimgui.JImStr;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.virtual_hex.editor.VirtualHexDesktopEditor.*;

public class EditorMainMenu extends MainMenuBar {

    public EditorMainMenu(DefaultUIWriter writer) {
        super(
                new Menu<>(new JImStr("File"), writer.createAction(new MenuItem("Exit"), new RunnableActivationHandler<>(() -> {
                    AtomicBoolean atomicBoolean = writer.getProperty("editor-should-close");
                    atomicBoolean.set(true);
                }))),
                new Menu<>(new JImStr("Tools"),
                        writer.cToggleGroup(FieldNames.SELECTED, W_EDITOR_CONFIGURATION, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Editor Configuration"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_UI_PLUGINS, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("UI Plugins"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_PROJECTS, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Projects"), new JImStr("")))
                    ),
                new Menu<>(new JImStr("Quick Task"), writer.createAction(new MenuItem("Clear all windows"), new RunnableActivationHandler<>(() -> writer.toggleGroup(EDITOR_ALL_WINDOWS, false)))),
                new Menu<>(new JImStr("Help"),
                        new Text<>(new JImStr("Coming soon...")),
                        new Text<>(new JImStr("A Game editor by Virtual Hex Games, development@virtual-hex.com"))
                ),
                new Menu<>(new JImStr("ImGui"),
                        // This may seem complicated at first, here we are creating a window and adding it to the root app, when the method
                        // returns it returns the window, which is used in the write.createOneWayBoolFieldLink to link the value mechanism item to the toggleable
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_ABOUT, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("About"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_USER_GUIDE, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("User Guide"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_DEMO, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Demo"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Metrics"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_FONT_SELECTOR, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Font Selector"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_STYLE_SELECTOR, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Style Selector"), new JImStr(""))),
                        writer.cToggleGroup(FieldNames.SELECTED, W_IMGUI_STYLE_EDITOR, new String[]{EDITOR_ALL_WINDOWS}, new MenuItemSelectable<>(new JImStr("Style Editor"), new JImStr("")))
                )
        );
    }

    private JImStr s(String s) {
        return new JImStr(s);
    }

    class FileMenU extends Menu<JImStr> {
        public FileMenU() {
            super(new JImStr("File"));
        }
    }
}

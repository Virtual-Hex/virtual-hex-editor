package com.virtual_hex.editor;

import com.virtual_hex.editor.data.MenuItem;
import com.virtual_hex.editor.data.Selectable;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import com.virtual_hex.editor.jimgui.JImGuiComponentWriter;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = EditorConfigurationHolder.class)
public class EditorConfigurationHolderWriter extends JImGuiComponentWriter {

    public EditorConfigCache editorConfigCache;

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        EditorConfigurationHolder editorConfigHolder = (EditorConfigurationHolder) uiComponent;
        EditorConfiguration editorConfig = editorConfigHolder.editorConfiguration;
        if(editorConfigCache == null){
            Selectable<JImStr>[] selRefreshables = new Selectable[editorConfig.refreshables.size()];
            for (int i = 0; i < selRefreshables.length; i++) {
                Refreshable refreshable = editorConfig.refreshables.get(i);
                selRefreshables[i] = new EditorSelectable(refreshable.getClass().toString());
            }

            Selectable<JImStr>[] uiComponentSelectables = new Selectable[editorConfig.uiComponents.size()];
            for (int i = 0; i < uiComponentSelectables.length; i++) {
                ClassHolder uiComponentClassHolder = editorConfig.uiComponents.get(i);
                String uiClassCompString = uiComponentClassHolder.aClass.toString();
                uiComponentSelectables[i] = writer.setStateChangeListener(
                        new EditorSelectable(uiClassCompString),
                        new StateChangeHandler<JImGui>() {
                            @Override
                            public void handle(JImGui out, UIComponent objectChanged, UIWriter<JImGui> parentDrawer) {
                                boolean b = out.beginPopup(uiClassCompString, 0);
                                if(b) {
                                    out.text(uiComponentClassHolder.urlToLoad.toString());
                                    out.endPopup();
                                }
                            }
                        });
            }

            Selectable<JImStr>[] uiComponentWriterSelectables = new Selectable[editorConfig.uiComponentWriters.size()];
            for (int i = 0; i < uiComponentWriterSelectables.length; i++) {
                ClassHolder uiComponentWriterHolder = editorConfig.uiComponentWriters.get(i);
                String uiClassCompString = uiComponentWriterHolder.aClass.toString();
                uiComponentWriterSelectables[i] = writer.setStateChangeListener(
                        new EditorSelectable(uiClassCompString),
                        new StateChangeHandler<JImGui>() {
                            @Override
                            public void handle(JImGui out, UIComponent objectChanged, UIWriter<JImGui> parentDrawer) {
                                boolean b = out.beginPopup(uiClassCompString, 0);
                                if(b) {
                                    out.text(uiComponentWriterHolder.urlToLoad.toString());
                                    out.endPopup();
                                }
                            }
                        });
            }

            editorConfigCache = new EditorConfigCache(
                    editorConfigHolder.name,
                    new EditorMenuBar("editor-configuration-menu",
                            true,
                            new EditorMenu("File",
                                    new MenuItem("Load New"),
                                    new MenuItem("Load and Merge"),
                                    new MenuItem("Save")
                            )
                    ),


                    new EditorListBox("Refreshables", 1, writer.cSingleToggleGroup("selected", "refreshables", selRefreshables)),

                    // These two could be collated into a tree format of writers for components, by id, the class loading, ext
                    // those are also available in the writer without the reflection data, This could drop new windows or child pop ups for supplying
                    // greater details on components with reflection
                    new EditorListBox("UI Components", 10, writer.cSingleToggleGroup("selected", "ui-components", uiComponentSelectables)),
                    new EditorListBox("UI Component Writers", 10, writer.cSingleToggleGroup("selected", "ui-component-writers", uiComponentWriterSelectables))
            );
        }

        UIComponentsUtils.processUiDataList(out, editorConfigCache, writer);
    }
}

package com.virtual_hex.editor;

import com.virtual_hex.editor.data.*;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import com.virtual_hex.editor.jimgui.JImGuiComponentWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.flag.JImSelectableFlags;
import org.ice1000.jimgui.flag.JImWindowFlags;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.virtual_hex.editor.VirtualHexDesktopEditor.*;


// TODO NEXT IS Packaging still, also reflection and widget listing interfaces and abstract classes
// add always handle after widget interface and add

@ComponentRegister(typeKey = EditorConfigurationHolder.class)
public class EditorConfigurationHolderWriter extends JImGuiComponentWriter {

    public EditorConfigCache editorConfigCache;

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        EditorConfigurationHolder editorConfigHolder = (EditorConfigurationHolder) uiComponent;
        EditorConfiguration editorConfig = editorConfigHolder.editorConfiguration;
        if(editorConfigCache == null){
            // Goal is to build out loaded/unloaded display of classes with control

            Selectable<JImStr>[] selRefreshables = new Selectable[editorConfig.refreshables.size()];
            for (int i = 0; i < selRefreshables.length; i++) {
                Refreshable refreshable = editorConfig.refreshables.get(i);
                selRefreshables[i] = new EditorSelectable(refreshable.getClass().toString());
            }

            List uiComponentSelectables = getSelectables(out, editorConfig.uiComponents);
            List uiComponentWriterSelectables = getSelectables(out, editorConfig.uiComponentWriters);

            UIComponents uiComponentsUiComponents = new UIComponents(uiComponentSelectables);
            UIComponents uiComponentsUiComponentWriters = new UIComponents(uiComponentWriterSelectables);


            editorConfigCache = new EditorConfigCache(
                    editorConfigHolder.name,
                    writer.cToggleGroup(VirtualHexDesktopEditor.OPEN, W_EDITOR_CONFIGURATION, new String[]{EDITOR_ALL_WINDOWS},
                        new JImWindowDecorated("Editor Configuration", false, JImWindowFlags.MenuBar,
                                new JImMenuBar("editor-configuration-menu",
                                        true,
                                        new JImMenu("File",
                                                new MenuItem("Load New"),
                                                new MenuItem("Load and Merge"),
                                                new MenuItem("Save")
                                        )
                                )
                        )
                    )
            );
        }


//                    new JImListBox("Refreshables", 1,selRefreshables),
//
//                    // These two could be collated into a tree format of writers for components, by id, the class loading, ext
//                    // those are also available in the writer without the reflection data, This could drop new windows or child pop ups for supplying
//                    // greater details on components with reflection
//                    new JImListBox("UI Components", 10,  uiComponentSelectables),
//                    new JImListBox("UI Component Writers", 10, uiComponentWriterSelectables)



        UIComponentsUtils.processUiDataList(out, editorConfigCache, writer);
    }


    protected static List<Selectable<JImStr>> getSelectables(JImGui out, Map<URL, List<Class<?>>> classList){
        List<Selectable<JImStr>> selectables = new ArrayList<>();
        Set<Map.Entry<URL, List<Class<?>>>> entries = classList.entrySet();
        for (Map.Entry<URL, List<Class<?>>> entry : entries) {
            URL key = entry.getKey();
            List<Class<?>> value = entry.getValue();
            int size = value.size();
            boolean open = out.treeNode(new JImStr(key.toString()));
            if(open){
                for (int i = 0; i < size; i++) {
                    Class<?> aClass = value.get(i);
                    selectables.add(new EditorSelectable(aClass.toString(), false, JImSelectableFlags.Nothing));
                }
            }
        }

        return selectables;
    }
}

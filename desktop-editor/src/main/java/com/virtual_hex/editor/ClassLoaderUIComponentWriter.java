package com.virtual_hex.editor;

import com.virtual_hex.editor.data.InputText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import com.virtual_hex.editor.jimgui.JImGuiComponentWriter;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

import java.util.WeakHashMap;

// todo once we start the plugin manager
@UIComponentRegister(typeKey = ClassLoaderUIComponent.class, name = "Style 1")
public class ClassLoaderUIComponentWriter extends JImGuiComponentWriter {

    ScanResult scan;
    JImStr str = new JImStr("Classes");
    boolean refresh = true;
    String[] list;

    private transient InputText inputText = InputText.of("Filter", new byte[255]);
    private WeakHashMap<byte[], String> strings = new WeakHashMap<>();

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ClassLoaderUIComponent component = (ClassLoaderUIComponent) uiComponent;



        out.text("Test Class Loader - API");
        out.separator();
        writer.write(out, inputText);
        out.separator();

        ChildFirstClazzLoader classLoader = (ChildFirstClazzLoader) component.classLoader;

        if(scan == null && !refresh) {
            ClassInfoList classes = scan.getAllClasses();
            int size = classes.size();
            list = new String[size];
            scan = new ClassGraph().overrideClassLoaders(classLoader.getChildClassLoader()).enableAllInfo().whitelistPackages().scan();
            for (int i = 0; i < size; i++) {
                String s = classes.get(i).toString();
                list[i] = s;
            }
        }
//
//        Vector<Class> classes = null;
//        try {
//            classes = ClassUtils.getClasses(classLoader);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

        boolean begin = out.listBoxHeader(str, list.length, 10);
        if(begin) {
            for (int i = 0; i < list.length; i++) {
                String s = list[i];
                int endIndex = bufferEndIndex(inputText.buffer);
                if (endIndex > 0) {
                    String string = strings.get(inputText.buffer);
                    if (string == null) {
                        // Very garbage creating, TODO use raw bytes
                        string = new String(inputText.buffer, 0, endIndex);
                        strings.put(inputText.buffer, string);
                    }
                    if (s.contains(string)) {
                        out.text(s);
                    }
                } else {
                    out.text(s);
                }
            }
            out.listBoxFooter();
        }
    }

    public static int bufferEndIndex(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

}

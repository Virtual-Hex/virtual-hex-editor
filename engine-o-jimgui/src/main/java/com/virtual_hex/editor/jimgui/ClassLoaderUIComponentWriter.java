package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ClassLoaderUIComponent;
import com.virtual_hex.editor.data.InputText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;
import java.util.Vector;
import java.util.WeakHashMap;

// todo once we start the plugin manager
@ComponentRegister(typeKey = ClassLoaderUIComponent.class)
public class ClassLoaderUIComponentWriter extends JImGuiComponentWriter {

    private transient InputText inputText = new InputText("Filter", 255);
    private WeakHashMap<byte[], String> strings = new WeakHashMap<>();

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ClassLoaderUIComponent component = (ClassLoaderUIComponent) uiComponent;



        out.text("Test Class Loader - API");
        out.separator();
        writer.write(out, inputText);
        out.separator();

        ClassLoader classLoader = component.classLoader;

        Field f = null;
        try {
            f = ClassLoader.class.getDeclaredField("classes");
            f.setAccessible(true);
            Vector<Class> classes =  (Vector<Class>) f.get(classLoader);
            boolean begin = out.listBoxHeader("Classes", classes.size(), 10);
            if(begin) {
                for (int i = 0; i < classes.size(); i++) {
                    String s = classes.get(i).toString();

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
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static int bufferEndIndex(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

}

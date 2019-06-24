package com.virtual_hex.editor.io;

import com.virtual_hex.editor.data.UIComponent;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.util.HashMap;
import java.util.Map;

/**
 * This is used to read in from some other source a UI, such as json or binary
 *
 * @param <T>
 */
public class UIReader<T> {

    public static final UIComponentReader EMPTY_READER = new EmptyComponentReader();
    public final int version;
    public Map<Class<?>, UIComponentReader<T>> classComponentHandlers;

    public UIReader(boolean scanForHandlers, ScanResult... scanResults) {
        this(0, scanForHandlers, scanResults);
    }

    // TODO Scans being provided to provide more app level configuration
    public UIReader(int version, boolean scanForHandlers, ScanResult... sca) {
        this.version = version;
        setMaps();
        if (scanForHandlers) {
            scanForHandlers(sca);
        }
    }

    private void scanForHandlers(ScanResult... scanResults) {
        for (int i = 0; i < scanResults.length; i++) {
            ScanResult scanResult = scanResults[i];
            ClassInfoList componentHandlerRegister = scanResult.getClassesWithAnnotation("com.virtual_hex.editor.io.ComponentRegister");
            for (ClassInfo compClassInfo : componentHandlerRegister) {
                boolean extendsSuperclass = compClassInfo.implementsInterface("com.virtual_hex.editor.io.UIComponentReader");
                if (extendsSuperclass) {
                    // New handler, should be registered
                    Class<UIComponentReader> aClass = (Class<UIComponentReader>) compClassInfo.loadClass();
                    try {
                        UIComponentReader componentReader = aClass.newInstance();
                        ComponentRegister annotation = componentReader.getClass().getAnnotation(ComponentRegister.class);
                        if (annotation.operation() == ComponentOperation.READ || annotation.operation() == ComponentOperation.READ_WRITE) {
                            classComponentHandlers.put(annotation.typeKey(), componentReader);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Todo Logging for failed class loading
                    }
                } else {
                    // TODO Logging that annotation exist but not a subclass of CompHandler
                }
            }
        }
    }


    private void setMaps() {
        this.classComponentHandlers = new HashMap<>();
    }

    public <UI extends UIComponent> UI read(Class<UI> clazzType, T in) {
        return (UI) classComponentHandlers.getOrDefault(clazzType, EMPTY_READER).read(in, this);
    }

    /**
     * Dispose of resources here, WE may have a cache clear method for memory management if needed
     */
    public void disopse() {
        classComponentHandlers.forEach((k, v) -> v.dispose());
//        activationHandlers.forEach((k, v) -> v.dispose());
    }

    private static class EmptyComponentReader implements UIComponentReader {

        @Override
        public UIComponent read(Object in, UIReader parentSerializer) {
            // Nothing Intended
            return null;
        }

        @Override
        public void dispose() {
            // Nothing Intended
        }
    }
}

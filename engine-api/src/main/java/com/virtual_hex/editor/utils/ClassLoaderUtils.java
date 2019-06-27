package com.virtual_hex.editor.utils;

import com.virtual_hex.editor.ChildFirstClazzLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassLoaderUtils {

    // This will only work if all references to the classloader, or its classes are gone
    // then this may allow for GC, but at least the Classloader in use will be refreshed without the classes
    public ChildFirstClazzLoader removeClasses(ChildFirstClazzLoader clazzLoader, URL... removeUrls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URL[] currentLoadedUrls = clazzLoader.getChildClassLoader().getURLs();
        List<URL> urlsToReload = new ArrayList<>(currentLoadedUrls.length);
        Collections.addAll(urlsToReload, currentLoadedUrls);
        for (int i = 0; i < removeUrls.length; i++) {
            urlsToReload.remove(removeUrls[i]);
        }
        Constructor<? extends ChildFirstClazzLoader> constructor = clazzLoader.getClass().getConstructor(List.class);
        return constructor.newInstance(urlsToReload);
    }


    public ChildFirstClazzLoader addClasses(ChildFirstClazzLoader clazzLoader, URL... addUrls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URL[] currentLoadedUrls = clazzLoader.getChildClassLoader().getURLs();
        List<URL> urlsToReload = new ArrayList<>(currentLoadedUrls.length + addUrls.length);
        Collections.addAll(urlsToReload, currentLoadedUrls);
        Collections.addAll(urlsToReload, addUrls);
        Constructor<? extends ChildFirstClazzLoader> constructor = clazzLoader.getClass().getConstructor(List.class);
        return constructor.newInstance(urlsToReload);
    }
}

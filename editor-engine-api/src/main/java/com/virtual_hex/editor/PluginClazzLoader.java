package com.virtual_hex.editor;

import java.net.URL;
import java.util.List;

public class PluginClazzLoader extends ChildFirstClazzLoader {

    public PluginClazzLoader(List<URL> classpath) {
        super(classpath);
    }

    public PluginClazzLoader(URL[] classpath) {
        super(classpath);
    }

    // TODO Previous URL clazz loader. Plugin / module systems should always self contain all the needs and look internally first before extending outwords
//
//    public PluginClazzLoader(Class<?> clazz) {
//        super(new URL[]{}, clazz.getClassLoader());//TODO Need to create a way to release load URLs/plugins after unloaded
//    }
//
//    public void addNewUrl(URL url) {
//        addURL(url);
//    }
//
//    @Override
//    protected void addURL(URL url) {
//        super.addURL(url);
//    }
//
//    public URL getSpecificResource(URL fileUrl, String resourcesPath) throws IOException {
//        return getSpecificResource(fileUrl.toString(), resourcesPath);
//    }
//
//    public URL getSpecificResource(String fileName, String resourcesPath) throws IOException {
//        Enumeration<URL> resources = getResources(resourcesPath);
//        while (resources.hasMoreElements()) {
//            URL url = resources.nextElement();
//            if (url.toString().contains(fileName)) {
//                return url;
//            }
//        }
//        return null;
//    }
//
//    public InputStream getSpecificResourceAsStream(URL fileURL, String resourcesPath) throws IOException {
//        return getSpecificResourceAsStream(fileURL.toString(), resourcesPath);
//    }
//
//    public InputStream getSpecificResourceAsStream(String fileName, String resourcesPath) throws IOException {
//        Enumeration<URL> resources = getResources(resourcesPath);
//        while (resources.hasMoreElements()) {
//            URL url = resources.nextElement();
//            if (url.toString().contains(fileName)) {
//                return url.openStream();
//            }
//        }
//        return null;
//    }


}
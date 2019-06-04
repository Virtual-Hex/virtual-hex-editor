package com.virtual_hex.jimgui;

import com.virtual_hex.data.UIApp;
import com.virtual_hex.data.UIDataDeserializer;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JImGuiUtil;
import org.ice1000.jimgui.util.JniLoader;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JImGuiApp {

    public transient UIApp uiApp;
    public transient JImGui imGui;
    public transient Map<String, byte[]> cachedStrings = new HashMap<>();
    public transient boolean running = true;

    public JImGuiApp() {
    }

    public JImGuiApp(UIApp uiApp) {
        this.uiApp = uiApp;
    }

    public JImGuiApp init(){
        JniLoader.load();
        this.imGui = new JImGui(uiApp.width, uiApp.height, uiApp.title);
        imGui.initBeforeMainLoop();
        JImGuiUtil.setStringToBytes(s -> cachedStrings.computeIfAbsent(s, s1 -> s1.getBytes(StandardCharsets.UTF_8)));
        return this;
    }

    public JImGuiApp close(){
        imGui.close();
        uiApp.deserializerWrapper.deserializer.close();
        return this;
    }

    public JImGuiApp deallocateAll(){
        imGui.deallocateNativeObject();
        uiApp.deserializerWrapper.deserializer.deallocateAll();
        return this;
    }

    public void setRunning() {
        this.running = true;
    }

    public void setNotRunning() {
        this.running = false;
    }

    public void loop() {
        while(running){
            imGui.initNewFrame();
            UIDataDeserializer parentDeserializer = uiApp.deserializerWrapper.deserializer;
            parentDeserializer.draw(imGui, uiApp, parentDeserializer);
            imGui.render();
        }

    }
}

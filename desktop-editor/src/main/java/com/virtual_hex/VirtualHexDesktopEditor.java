package com.virtual_hex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.virtual_hex.data.*;
import com.virtual_hex.jimgui.JImGuiUIDataDeserializer;
import com.virtual_hex.jimgui.handlers.OpenableFlagHandler;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * TODO: Will need some reflective nativeData entities to be able to render IDs to names, ect since
 * TODO: Pluggable Serialization
 *
 * Note: As of now the intent is that Entities translate to a widget.
 *          -> Entities have a main render component
 *          -> Entities should be added in the order of rendering
 *          -> Entities will have one component for rendering and in that
 *              their may be a organization of how nativeData is added, Example:
 *              menu bar process can be a linked list of Drawable's
 *
 *
 *
 * When editor is loaded...
 * project = worlds // May be more then one project
 * worlds = entities
 * entities = component
 *
 *  TODO- Replace linked list with fixed but resizable array to reduced linked list garbage
 */
public final class VirtualHexDesktopEditor {

    /**
     * Simply a Logger Reference
     */
    private static final Logger L = LoggerFactory.getLogger(VirtualHexDesktopEditor.class);

    public static VirtualHexDesktopEditor INSTANCE;

    public UIApp uiApp;

    public transient Map<String, byte[]> cachedStrings = new HashMap<>();

    public static void main(String[] args) {
        // TODO jimgui.ini loading so users can import layouts
        for (int i = 0; i < args.length; i++) {
        }
        INSTANCE = new VirtualHexDesktopEditor();
        INSTANCE.run();
    }

    public void run() {
        // Deserialize a UIApp here
        // PROJECT FORMAT which will have a new child first loader
        // Project will have a a child first loader as well
        // Load Editor
        // Load Projects



        // TODO Setup Menu bar next 06/05/2019
        Object editorLoader = null;
        if(editorLoader != null){
            uiApp = new UIApp(
                // TODO
            );
        } else {
            uiApp = new UIApp("Virtual Hex Editor", 1280, 720,
                            new UIDeserializerWrapper(
                                    JImGuiUIDataDeserializer.DEFAULT_UI_DATA_DESERIALIZER,
                                    new OpenableFlags("Debug", true,
                                            new UIComponentArray(new Text("Todo")), Openable.Type.WINDOW_EXITABLE, 0))
                            );
        }

        JniLoader.load();

        // TODO Ensure that data types all be rechecked for proper hashcoding
        // TODO CHECK THIS, CAUSING STUTTERING
//        JImGuiUtil.setStringToBytes(s -> cachedStrings.computeIfAbsent(s, s1 -> s1.getBytes(StandardCharsets.UTF_8)));
        runPer(0,
                uiApp.width,
                uiApp.height,
                uiApp.title,
                new Consumer<JImGui>() {
                    @Override
                    public void accept(JImGui imGui) {
                        JImGuiUIDataDeserializer.DEFAULT_UI_DATA_DESERIALIZER.draw(
                                        imGui,
                                        uiApp,
                                        null
                        );
                    }
                }
        );

        // Save Projects
        // Save Editor

        // Release editor resources
        OpenableFlagHandler.INSTANCE.deallocatableObjectManager.deallocateAll();

        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).setPrettyPrinting().create();
        String s = gson.toJson(uiApp);
        System.out.println(s);

        UIApp uiApp2 = gson.fromJson(s, UIApp.class);
        runPer(0,
                uiApp.width,
                uiApp.height,
                uiApp.title,
                new Consumer<JImGui>() {
                    @Override
                    public void accept(JImGui imGui) {
                        JImGuiUIDataDeserializer.DEFAULT_UI_DATA_DESERIALIZER.draw(
                                imGui,
                                uiApp2,
                                null
                        );
                    }
                }
        );
    }


    // TODO Pull request for JIMGUI to have title only in constructor
    public static void runPer(long millis, int width, int height, String title,  @NotNull Consumer<@NotNull JImGui> runnable) {
        try (JImGui imGui = new JImGui(width, height, title)) {
            long latestRefresh = System.currentTimeMillis();
            imGui.initBeforeMainLoop();
            while (!imGui.windowShouldClose()) {
                long currentTimeMillis = System.currentTimeMillis();
                long deltaTime = currentTimeMillis - latestRefresh;
                Thread.sleep(deltaTime / 2);
                if (deltaTime > millis) {
                    imGui.initNewFrame();
                    runnable.accept(imGui);
                    imGui.render();
                    latestRefresh = currentTimeMillis;
                }
            }
        } catch (@NotNull InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

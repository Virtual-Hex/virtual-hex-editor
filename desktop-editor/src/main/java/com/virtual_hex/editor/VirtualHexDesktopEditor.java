package com.virtual_hex.editor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.virtual_hex.editor.data.*;
import com.virtual_hex.editor.io.UIWriter;
import com.virtual_hex.editor.jimgui.utils.JImGuiFailedWriteBiConsumer;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.virtual_hex.editor.data.FieldNames.OPEN;

/**
 * TODO: Will need some reflective nativeData entities to be able to render IDs to names, ect since
 * TODO: Pluggable Serialization
 * <p>
 * Note: As of now the intent is that Entities translate to a widget.
 * -> Entities have a main render component
 * -> Entities should be added in the order of rendering
 * -> Entities will have one component for rendering and in that
 * their may be a organization of how nativeData is added, Example:
 * menu bar process can be a linked list of Drawable's
 * <p>
 * <p>
 * <p>
 * When editor is loaded...
 * project = worlds // May be more then one project
 * worlds = entities
 * entities = component
 * <p>
 * TODO- Replace linked list with fixed but resizable array to reduced linked list garbage
 */
public final class VirtualHexDesktopEditor extends UIComponent {

    public static final String EDITOR_ALL_WINDOWS = "emm-0";
    public static final String W_PROJECTS = "w-projects";
    public static final String W_IMGUI_ABOUT = "w-imgui-about";
    public static final String W_IMGUI_DEMO = "w-imgui-demo";
    public static final String W_IMGUI_METRICS = "w-imgui-metrics";

    public static final String TITLE = "Virtual Hex Editor";
    /**
     * Simply a Logger Reference
     */
    private static final Logger L = LoggerFactory.getLogger(VirtualHexDesktopEditor.class);

    public static VirtualHexDesktopEditor INSTANCE;

    public UIComponents uiComponents;
    public boolean shouldClose;

    public transient Map<String, byte[]> cachedStrings = new HashMap<>();

    public static void main(String[] args) throws URISyntaxException {

        int width = 1024;
        int height = 720;

        // TODO jimgui.ini loading so users can import layouts
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg){
                case "--w": width = Integer.parseInt(args[i + 1]);
                case "--h": height = Integer.parseInt(args[i + 1]);
            }

        }
        INSTANCE = new VirtualHexDesktopEditor();
        INSTANCE.run(width, height);
    }

    public void run(int width, int height) throws URISyntaxException {
        // Deserialize a UIApp here
        // PROJECT FORMAT which will have a new child first loader
        // Project will have a a child first loader as well
        // Load Editor
        // Load Projects

        // Load a writer, here we want to load all of the component writers for this writer from the provided package
        UIWriter<JImGui> uiWriter = new UIWriter<>(true, "com.virtual_hex.editor.jimgui");


        Object editorLoader = null;
        if (editorLoader != null) {
            uiComponents = null; // TODO
        } else {
            // TODO Window Settings
            uiComponents = new UIComponents();
            // Anything we draw here will go to the debug window if its a widget type that cannot interact
            // directly with JImGui, like a new Text();  would open a debug window and draw text on it
            // Using a menu, window, some settings and options will draw it correctly in its own area
            uiComponents.uiComponents.add(new EditorMainMenu(this, uiWriter));

            Collections.addAll(uiComponents.uiComponents,
                    uiWriter.cToggleGroup(OPEN, W_PROJECTS, new String[]{EDITOR_ALL_WINDOWS}, new ProjectsWindow(
                        new Image<String>("Cupboard", 500, 250, "D:\\dropbox\\cupboard-portal-obama-is-missing.png"),
                        new Popup("PopTest", "Hey a pop up bros"),
                        uiWriter.createAction(new ImageButton<String>("Enter", 64,64, "D:\\Business\\OneDrive - Free Universe Games\\Free Universe Games Shared\\Company Asset Pool\\virtual-hex\\splash\\64-no-alpha.png"),
                                (out, objectActivated, parentDrawer) -> out.openPopup("PopTest")
                        )
                    )),

                    new ListBox("Only one selectable", 2, uiWriter.cSingleToggleGroup("selected", "listbox", new Selectable("Test1"),  new Selectable("test2"),  new Selectable("Test 3"))),
                    new ListBox0("List 2", "Test11", "test22", "Test 33"),
                    new ListBox0("List 3", 50, 50, "selected", "lb", "Test111", "test222", "Test 333"),
                    new Child("Test Child", true, 0,50, 50, "Test"),
                    uiWriter.cToggleGroup(OPEN, W_IMGUI_ABOUT, new String[]{EDITOR_ALL_WINDOWS}, new ShowAboutWindow()),
                    uiWriter.cToggleGroup(OPEN, W_IMGUI_DEMO, new String[]{EDITOR_ALL_WINDOWS}, new ShowDemoWindow()),
                    uiWriter.cToggleGroup(OPEN, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new ShowMetricsWindow())
            );
        }

        JniLoader.load();

        // TODO Ensure that data types all be rechecked for proper hashcoding
        // TODO CHECK THIS, CAUSING STUTTERING
//        JImGuiUtil.setStringToBytes(s -> cachedStrings.computeIfAbsent(s, s1 -> s1.getBytes(StandardCharsets.UTF_8)));

        DeallocatableObjectManager manager = new DeallocatableObjectManager();
        NativeBool bool = new NativeBool();
        manager.add(bool);
        bool.modifyValue(true);


        // TODO Not properly exiting in some cases
        long millis = 0;
        try (JImGui imGui = new JImGui(width, height, TITLE)) {
            long latestRefresh = System.currentTimeMillis();
            imGui.initBeforeMainLoop();
            while (!shouldClose) {
                long currentTimeMillis = System.currentTimeMillis();
                long deltaTime = currentTimeMillis - latestRefresh;
                Thread.sleep(deltaTime / 2);
                if (deltaTime > millis) {
                    imGui.initNewFrame();

                    uiWriter.write(imGui, uiComponents, new JImGuiFailedWriteBiConsumer());

                    imGui.render();
                    latestRefresh = currentTimeMillis;
                }
            }

        } catch (@NotNull InterruptedException e) {
            throw new RuntimeException(e);
        }


        // ANYTHING PAST THIS POINT WILL BE EXITING, MEANS THE shouldClose = true;

        // Save Projects
        // Save Editor


        Gson gson = new GsonBuilder()

                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                .setPrettyPrinting().create();
        String s = gson.toJson(uiComponents);
        System.out.println(s);

//        UIApp uiApp2 = gson.fromJson(s, UIApp.class);

        // Netty can be moved out at some point when other methods are added and then this loaded modular style
//        UIWriter<ByteBuf>  byteBufWriter = new UIWriter<>(true, "com.virtual_hex.netty");
//
//        ByteBuf out = PooledByteBufAllocator.DEFAULT.ioBuffer();
//        byteBufWriter.write(out, uiComponents);
//        System.out.println(out);
//
//        UIReader<ByteBuf> byteBufReader = new UIReader<>(true, "com.virtual_hex.netty");
//        UIApp uiApp2 = (UIApp) byteBufReader.classComponentHandlers.get(UIApp.class).read(out, byteBufReader);
//        System.out.println(out);
//        out.release();

//        runPer(0,
//                uiComponents.width,
//                uiComponents.height,
//                uiComponents.title,
//                new Consumer<JImGui>() {
//                    @Override
//                    public void accept(JImGui imGui) {
//                        uiWriter.write(imGui, uiApp2);
//                    }
//                }
//        );

        // Release editor resources
        uiWriter.disopse();
        manager.deallocateAll();
    }

}

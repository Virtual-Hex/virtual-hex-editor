package com.virtual_hex.editor;

import ch.qos.logback.classic.Level;
import com.virtual_hex.editor.data.*;
import com.virtual_hex.editor.io.UIWriter;
import com.virtual_hex.editor.jimgui.utils.JImGuiFailedWriteBiConsumer;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
public final class VirtualHexDesktopEditor extends AbstractUIComponent {

    public static final String EDITOR_ALL_WINDOWS = "emm-0";
    public static final String W_PROJECTS = "w-projects";
    public static final String W_IMGUI_ABOUT = "w-imgui-about";
    public static final String W_IMGUI_DEMO = "w-imgui-demo";
    public static final String W_IMGUI_METRICS = "w-imgui-metrics";

    public static final String TITLE = "Virtual Hex Editor";
    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualHexDesktopEditor.class);

    public static VirtualHexDesktopEditor INSTANCE;

    public PluginManager pluginManager;
    public UIComponents uiComponents;
    public boolean shouldClose;

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
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.ALL);

        // Deserialize a UIApp here
        // PROJECT FORMAT which will have a new child first loader
        // EditorProject will have a a child first loader as well
        // Load Editor
        // Load Projects

        // Load a writer, here we want to load all of the component writers for this writer from the provided package

        // Basic writer using the defaults
        ScanResult scanResult1 = new ClassGraph().enableAllInfo().whitelistPackages("com.virtual_hex.editor.jimgui").scan();
        UIWriter<JImGui> uiWriter = new UIWriter<>(true, scanResult1);

        ChildFirstClassLoader editorClassLoader = new ChildFirstClassLoader(new URL[]{}, this.getClass().getClassLoader());
        pluginManager = new EnhancedPluginManager(Paths.get("plugins"), "virtual-hex-editor");

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
                            new ClassLoaderUIComponent("Test API", editorClassLoader)
                    )),

                    uiWriter.cToggleGroup(OPEN, W_IMGUI_ABOUT, new String[]{EDITOR_ALL_WINDOWS}, new ShowAboutWindow()),
                    uiWriter.cToggleGroup(OPEN, W_IMGUI_DEMO, new String[]{EDITOR_ALL_WINDOWS}, new ShowDemoWindow()),
                    uiWriter.cToggleGroup(OPEN, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new ShowMetricsWindow())
            );
        }

        JniLoader.load();

        // TODO Ensure that data types all be rechecked for proper hashcoding
        // TODO CHECK THIS, CAUSING STUTTERING



//        JImGuiUtil.setStringToBytes(new StringCaching());

        DeallocatableObjectManager manager = new DeallocatableObjectManager();
        NativeBool bool = new NativeBool();
        manager.add(bool);
        bool.modifyValue(true);

        JImGuiFailedWriteBiConsumer failedWriteBiConsumer = new JImGuiFailedWriteBiConsumer();

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

                    uiWriter.write(imGui, uiComponents, failedWriteBiConsumer);

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


//        Gson gson = new GsonBuilder()
//
//                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
//                .setPrettyPrinting().create();
//        String s = gson.toJson(uiComponents);
//        System.out.println(s);

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


    public class StringCaching implements Function<String, byte[]> {

        private final Map<String, byte[]> cachedStrings = new HashMap<>();
        private final byte[] EMPTY_ARRAY = {};

        @Override
        public byte[] apply(String s) {
            if(s == null) return EMPTY_ARRAY;
            byte[] buffer = cachedStrings.get(s);
            if(buffer == null) {
                buffer = s.getBytes(StandardCharsets.UTF_8);
                cachedStrings.put(s, buffer);
                System.arraycopy(s.getBytes(), 0, buffer, 0, s.length());
                LOGGER.error("String caching check. Not Cached - String: \"{}\", Bytes: \"{}\", Reconstructed \"{}\".", s, Arrays.toString(buffer), new String(buffer));
            }
            LOGGER.debug("String caching check. String: \"{}\", Bytes: \"{}\", Reconstructed \"{}\".", s, Arrays.toString(buffer), new String(buffer));
            return buffer;
        }
    }
}

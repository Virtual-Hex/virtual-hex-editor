package com.virtual_hex.editor;

import ch.qos.logback.classic.Level;
import com.virtual_hex.editor.data.AbstractUIComponent;
import com.virtual_hex.editor.data.UIComponents;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

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



    public static final String TITLE = "Virtual Hex Editor";
    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualHexDesktopEditor.class);

    public static VirtualHexDesktopEditor INSTANCE;

    public PluginManager pluginManager;
    public UIComponents uiComponents;
    public AtomicBoolean shouldClose = new AtomicBoolean(false);
    public EditorConfiguration editorConfiguration = null;// This will be just a file essentially as settings, loaded into a full type

    public static void main(String[] args) throws URISyntaxException {

        int width = 1024;
        int height = 720;
        boolean renderDefault = true;

        // TODO jimgui.ini loading so users can import layouts
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg){
                case "--w": width = Integer.parseInt(args[i + 1]);
                case "--h": height = Integer.parseInt(args[i + 1]);
                case "--d": renderDefault = false; // Turn off default rendering (widgets / reader)
            }

        }
        INSTANCE = new VirtualHexDesktopEditor();
        INSTANCE.run(width, height, renderDefault);
    }

    public void run(int width, int height, boolean renderDefault) throws URISyntaxException {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.ALL);

        // Deserialize a UIApp here
        // PROJECT FORMAT which will have a new child first loader
        // EditorProject will have a a child first loader as well
        // Load Editor
        // Load Projects

        // Load a writer, here we want to load all of the component writers for this writer from the provided package

        // Look for settings, to load previous writers and data
        // Load basic UIWriter, show window if no previous stuff, to allow extending of the launcher

        if(editorConfiguration == null){
            editorConfiguration = getDefaultImpl();
        } else {
            LOGGER.warn("Editor configuration loading is not currently supported yet (Soon) TM :D.");
            // This allows users to completely override the UI and editor default widgets or just add additional ones
            if(renderDefault){
                editorConfiguration = getDefaultImpl();
            }
            // Need to create a merge
        }


        JniLoader.load();

        // TODO CHECK THIS, CAUSING STUTTERING
//        JImGuiUtil.setStringToBytes(new StringCaching());

        // TODO Not properly exiting in some cases
        long millis = 0;
        try (JImGui imGui = new JImGui(width, height, TITLE)) {
            long latestRefresh = System.currentTimeMillis();
            imGui.initBeforeMainLoop();
            while (!shouldClose.get()) {
                long currentTimeMillis = System.currentTimeMillis();
                long deltaTime = currentTimeMillis - latestRefresh;
                Thread.sleep(deltaTime / 2);
                if (deltaTime > millis) {
                    imGui.initNewFrame();

                    // Allow the writers to write
                    for (UIWriter uiWriter : editorConfiguration.writers) {
                        uiWriter.write(imGui);
                    }

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
//        uiWriter.disopse(); // TODO

    }

    private EditorConfiguration getDefaultImpl() {
        EditorConfiguration editorConfiguration = new EditorConfiguration();

        // Scan for internal data types
        ScanResult editorPackageScan = new ClassGraph().enableAllInfo().whitelistPackages("com.virtual_hex.editor").scan();

        // Lets grab the ui components
        ClassInfoList uiComponents = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.data.UIComponent");

        // Lets grab the io component writers
        ClassInfoList uiComponentWriters = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIComponentWriter").filter(classInfo -> classInfo.hasAnnotation("com.virtual_hex.editor.ComponentRegister"));

        // Lets grab all the writers
        ClassInfoList uiWriters = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIWriter");

        // Lets grab all the readers // TODO
        ClassInfoList uiReaders = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIReader");

        // Combine them into a child first class loader
        List<URL> classesToLoad = new ArrayList<>();
        uiComponents.forEach(classInfo -> classesToLoad.add(classInfo.getClasspathElementURL()));
        uiComponentWriters.forEach(classInfo -> classesToLoad.add(classInfo.getClasspathElementURL()));
        uiWriters.forEach(classInfo -> classesToLoad.add(classInfo.getClasspathElementURL()));
        ChildFirstClazzLoader childURLClassLoader = new ChildFirstClazzLoader(classesToLoad);

        // Find and load the writers
        ScanResult childClassLoaderScan = new ClassGraph().overrideClassLoaders(childURLClassLoader).enableAllInfo().whitelistPackages("com.virtual_hex.editor").scan();
        ClassInfoList uiWritersToLoad = childClassLoaderScan.getClassesImplementing("com.virtual_hex.editor.UIWriter");

        uiWritersToLoad.forEach(ci -> {
            Class<?> aClass = ci.loadClass();

            try {
                UIWriter newInstance = (UIWriter) aClass.newInstance();

                newInstance.setProperty("editor-should-close", shouldClose);
                // List needs to be converted to a Editor Configuration with ability to change
                editorConfiguration.writers.add(newInstance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        // Fall out of block and process JImGui UI.
        return editorConfiguration;
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

package com.virtual_hex.editor;

import ch.qos.logback.classic.Level;
import com.virtual_hex.editor.data.*;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
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


    public static final String EDITOR_ALL_WINDOWS = "emm-0";
    public static final String W_PROJECTS = "w-projects";
    public static final String W_UI_PLUGINS = "w-ui-plugins";
    public static final String W_IMGUI_ABOUT = "w-imgui-about";
    public static final String W_IMGUI_USER_GUIDE = "w-imgui-userguide";
    public static final String W_IMGUI_DEMO = "w-imgui-demo";
    public static final String W_IMGUI_METRICS = "w-imgui-metrics";
    public static final String W_IMGUI_FONT_SELECTOR = "w-font-selector";
    public static final String W_IMGUI_STYLE_SELECTOR = "w-style-selector";
    public static final String W_IMGUI_STYLE_EDITOR = "w-style-editor";
    public static final String OPEN = "open";


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

        // Load a writer, here we want to load all of the component uiComponentWriters for this writer from the provided package

        // Look for settings, to load previous uiComponentWriters and data
        // Load basic UIWriter, show window if no previous stuff, to allow extending of the launcher

        if(editorConfiguration == null){
            editorConfiguration = getDefaultImpl();
        } else {
            LOGGER.warn("Editor configuration loading is not currently supported yet (Soon) TM :D.");
            // This allows users to completely override the UI and editor default widgets or just add additional ones
            if(renderDefault){
                editorConfiguration = editorConfiguration.merge(getDefaultImpl());
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

                    // Allow the uiComponentWriters to write
                    for (UIWriter uiWriter : editorConfiguration.uiWriters) {
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

        editorConfiguration.dispose();
    }

    private EditorConfiguration getDefaultImpl() {
        Refreshable<EditorConfiguration> refreshable = (editorConfiguration) -> {
            // Scan for internal data types
            ScanResult editorPackageScan = new ClassGraph().enableAllInfo().whitelistPackages("com.virtual_hex.editor").scan();

            // Lets grab the ui components
            ClassInfoList uiComponents = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.data.UIComponent");

            // Lets grab the io component uiComponentWriters
            ClassInfoList uiComponentWriters = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIComponentWriter").filter(classInfo -> classInfo.hasAnnotation("com.virtual_hex.editor.ComponentRegister"));

            // Lets grab all the uiComponentWriters
            ClassInfoList uiWriters = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIWriter");

            // Lets grab all the readers // TODO
            ClassInfoList uiReaders = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIReader");

            // Combine them into a child first class loader
            List<URL> classesToLoad = new ArrayList<>();
            uiComponents.forEach(classInfo -> classesToLoad.add(classInfo.getClasspathElementURL()));
            uiComponentWriters.forEach(classInfo -> classesToLoad.add(classInfo.getClasspathElementURL()));
            uiWriters.forEach(classInfo -> classesToLoad.add(classInfo.getClasspathElementURL()));
            ChildFirstClazzLoader childURLClassLoader = new ChildFirstClazzLoader(classesToLoad);

            // Find and load the uiComponentWriters
            ScanResult childClassLoaderScan = new ClassGraph().overrideClassLoaders(childURLClassLoader).enableAllInfo().whitelistPackages("com.virtual_hex.editor").scan();
            ClassInfoList uiWritersToLoad = childClassLoaderScan.getClassesImplementing("com.virtual_hex.editor.UIWriter");

            uiWritersToLoad.forEach(ci -> {
                Class<?> aClass = ci.loadClass();

                try {
                    UIWriter uiWriter = (UIWriter) aClass.newInstance();

                    if(uiWriter instanceof DefaultUIWriter){
                        DefaultUIWriter dUIWriter = (DefaultUIWriter) uiWriter;
                        dUIWriter.root = new UIComponents();
                        // Anything we show here will go to the debug window if its a widget type that cannot interact
                        // directly with JImGui, like a new Text();  would open a debug window and show text on it
                        // Using a menu, window, some settings and options will show it correctly in its own area

                        // TODO Move to a plugin, it should be the?? data i dunno but not a writer.
                        // TODO needs to be updated as well, this is from the editor but didnt belong there so much in the final app
                        Collections.addAll(dUIWriter.root.uiComponents,
                                        // New User Window

                                        // The editor menu will turn into a slightly dif component, well have helper methods to extend
                                        // add to editor
                                        new EditorMainMenu(dUIWriter),


                                        // UI Plugin Window, UI needs to be remember in case the user completely replaces it

                                        dUIWriter.cToggleGroup(OPEN, W_PROJECTS, new String[]{EDITOR_ALL_WINDOWS}, new ProjectsWindow(
                                                // Test widgets here

                                        )),

                                        dUIWriter.cToggleGroup(OPEN, W_UI_PLUGINS, new String[]{EDITOR_ALL_WINDOWS}, new ProjectsWindow(
                                                // Here we need to show what is loaded by default , We need to create a child first classloader
                                                // where these will be loaded, this way same class can be overridden due to isolation
                                                new MainMenuBar("")




                                        )),


                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_ABOUT, new String[]{EDITOR_ALL_WINDOWS}, new ShowAboutWindow()),
                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_USER_GUIDE, new String[]{EDITOR_ALL_WINDOWS}, new WindowDecorated("User Guide", false, 0, new ShowUserGuide())),
                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_DEMO, new String[]{EDITOR_ALL_WINDOWS}, new ShowDemoWindow()),
                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new ShowMetricsWindow()),

                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_FONT_SELECTOR, new String[]{EDITOR_ALL_WINDOWS}, new WindowDecorated("Font Selector", false, 0, new ShowFontSelector<>(new JImStr("Font Selector")))),
                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_STYLE_SELECTOR, new String[]{EDITOR_ALL_WINDOWS}, new WindowDecorated("Style Selector", false, 0, new ShowStyleSelector<>(new JImStr("Style Selector")))),
                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_STYLE_EDITOR, new String[]{EDITOR_ALL_WINDOWS}, new WindowDecorated("Style Editor", false, 0, new ShowStyleEditor<>(null)))


                                // Style Selector
//                                        dUIWriter.cToggleGroup(OPEN, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new ShowMetricsWindow())

                                );
                    }


                    uiWriter.setProperty("editor-should-close", shouldClose);
                    // List needs to be converted to a Editor Configuration with ability to change
                    editorConfiguration.uiWriters.add(uiWriter);

                    // Todo set the rest of the Editor Confuration and create a widget for it

                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        };
        return new EditorConfiguration(refreshable);
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

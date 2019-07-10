package com.virtual_hex.editor;

import com.virtual_hex.editor.data.Child;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Window;
import com.virtual_hex.editor.data.WindowDecorated;
import com.virtual_hex.editor.flags.Flag;
import com.virtual_hex.editor.flags.JImWindowFlagsType;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import io.github.classgraph.ClassInfo;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeBool;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.ice1000.jimgui.flag.JImInputTextFlags;
import org.ice1000.jimgui.flag.JImSelectableFlags;
import org.ice1000.jimgui.flag.JImWindowFlags;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

@UIComponentRegister(typeKey = UIComponentsDataStructure.class)
public class UIComponentsDataStructureWriter implements UIComponentWriter<JImGui, DefaultUIWriter> {

    public static final int STRING_SIZE_CAP = 128;

    private static final JImStr ROOT = new JImStr("root");
    private static final JImStr WIDGET_DESIGNER = new JImStr("widget-designer");
    private static final JImStr BYTE = new JImStr("byte");
    private static final JImStr SHORT = new JImStr("short");
    private static final JImStr INT = new JImStr("int");
    private static final JImStr LONG = new JImStr("long");
    private static final JImStr FLOAT = new JImStr("float");
    private static final JImStr DOUBLE = new JImStr("double");
    private static final JImStr BOOLEAN = new JImStr("boolean");
    private static final JImStr STRING = new JImStr("String");
    private static final JImStr NULL = new JImStr("null");
    private static final JImStr EMPTY_ARRAY = new JImStr("empty array");
    private static final JImStr FLOAT_FORMAT = new JImStr("%.3f");
    private static final JImStr DOUBLE_FORMAT = new JImStr("%.6f");
    private static final JImStr FIELDS = new JImStr("Fields");
    private static final JImStr CLASS_FIELD_COLUMN_1 = new JImStr("Name & Value");
    private static final JImStr CLASS_FIELD_COLUMN_2 = new JImStr("Value");
    private static final JImStr CLASS_FIELD_COLUMN_3 = new JImStr("Type");
    private static final JImStr NO_LABEL = new JImStr("");
    private static final JImStr FLAGS = new JImStr("Flags");
    private static final JImStr OK = new JImStr("Ok");
    private static final JImStr ADD_WIDGET = new JImStr("Add Widget");
    private static final JImStr REMOVE_WIDGET = new JImStr("Remove Widget(s)");
    private static final JImStr CLEAR_FLAGS = new JImStr("Clear Flags");
    private static final JImStr FLAG_SELECTION = new JImStr("Hold CTRL and click to select multiple items.");

    public final DeallocatableObjectManager manager = new DeallocatableObjectManager();

    private final Map<Class<?>, JImStr> stringCache = new WeakHashMap<>();
    private final Map<Class<?>, WriteHandler<JImGui, DefaultUIWriter>> writeHandling = new WeakHashMap<>();
    private final Map<Object, JImStr> jimStrCache = new WeakHashMap<>();

    private final Map<UIComponent, Map<Field, byte[]>> stringFieldCache = new WeakHashMap<>();
    private final Map<UIComponent, Map<Field, String>> jimStrFieldCache = new WeakHashMap<>();

    UIComponentsDataStructure widgetHolder = new UIComponentsDataStructure(new UIComponent[0], null);
    private WeakReference<Class<?>> selectedWidgetClass = new WeakReference<>(Object.class);


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        UIComponentsDataStructure component = (UIComponentsDataStructure) uiComponent;

        // TODO Save Menu

        boolean open = out.treeNode(ROOT);
        if(open){
            try {
                write(out, component.root, writer);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO
            }
            out.treePop();
        }
//
        boolean open2 = out.treeNode(WIDGET_DESIGNER);
        if(open2){
            try {
                boolean buttonAddWidget = out.button(ADD_WIDGET, 150f, 25f);
                if(buttonAddWidget){
                    // Pop up widget selector
                    // Stacked Model
                    if(widgetHolder == null) {
                        widgetHolder = new UIComponentsDataStructure(new UIComponent[0], component.uiComponents);
                    }
                    out.openPopup(ADD_WIDGET);

                    // Filter box, widget list, single select, ok

                    // Reflection to get constructors or static methods

//                    widgetHolder = UIUtils.merge(widgetHolder.root, );
                }
                NativeBool openPtr = writer.getAlwaysTrueNativeBool();
                boolean openPopup = out.beginPopupModal(ADD_WIDGET, openPtr, JImWindowFlags.Nothing);
                if(openPopup){

                    for (ClassInfo classInfo : component.uiComponents) {
                        // label
                        Class<?> aClass = classInfo.loadClass();
                        JImStr label = jimStrCache.computeIfAbsent(aClass, a -> new JImStr(a.toString()));
                        boolean selected = out.selectable0(label, selectedWidgetClass.get() == aClass, 0, 0,0);
                        if(selected){
                            // Reflect and get constructores and static metheods returning class



                        }
                    }


//                    if (ImGui::BeginMenuBar())
//                    {
//                        if (ImGui::BeginMenu("File"))
//                        {
//                            if (ImGui::MenuItem("Dummy menu item")) {}
//                            ImGui::EndMenu();
//                        }
//                        ImGui::EndMenuBar();
//                    }
//                    ImGui::Text("Hello from Stacked The First\nUsing style.Colors[ImGuiCol_ModalWindowDimBg] behind it.");
//
//                    // Testing behavior of widgets stacking their own regular popups over the modal.
//                    static int item = 1;
//                    static float color[4] = { 0.4f,0.7f,0.0f,0.5f };
//                    ImGui::Combo("Combo", &item, "aaaa\0bbbb\0cccc\0dddd\0eeee\0\0");
//                    ImGui::ColorEdit4("color", color);
//
//                    if (ImGui::Button("Add another modal.."))ImGui::OpenPopup("Stacked 2");
//
//                    // Also demonstrate passing a bool* to BeginPopupModal(), this will create a regular close button which will close the popup.
//                    // Note that the visibility state of popups is owned by imgui, so the input value of the bool actually doesn't matter here.
//                    bool dummy_open = true;
//                    if (ImGui::BeginPopupModal("Stacked 2", &dummy_open))
//                    {
//                        ImGui::Text("Hello from Stacked The Second!");
//                        if (ImGui::Button("Close"))
//                        ImGui::CloseCurrentPopup();
//                        ImGui::EndPopup();
//                    }
//
//                    if (ImGui::Button("Close"))ImGui::CloseCurrentPopup();

                    out.endPopup();
                }





                out.sameLine();
                boolean buttonRemoveWidget = out.button(REMOVE_WIDGET, 150f, 25f);
                if(buttonRemoveWidget){
                    List<UIComponent> list = new ArrayList<>(Arrays.asList(widgetHolder.root));
//                    list.removeAll(); // Selected
                    UIComponent[] newRoot = new UIComponent[list.size()];
                    widgetHolder.root = list.toArray(newRoot);
                }
                write(out, widgetHolder.root, writer);

                // Draw widgets to the screen
                for (int i = 0; i < widgetHolder.root.length; i++) {
                    writer.write(out, widgetHolder.root[i]);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO
            }
            out.treePop();
        }
    }

    // Write out the widgets for widget making
    private void write(JImGui out, List<UIComponent[]> widgets, DefaultUIWriter writer) {
        // Selectable list
    }

    public void write(JImGui out, UIComponent[] uiComponents, DefaultUIWriter writer) throws IllegalAccessException {
        for (int i = 0; i < uiComponents.length; i++) {
            UIComponent uiComponent = uiComponents[i];
            write0(out, uiComponent, writer);
        }
    }

    protected void write0(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) throws IllegalAccessException  {
        Class<? extends UIComponent> aClass = uiComponent.getClass();
//            JImStr jImStr = stringCache.computeIfAbsent(aClass, aClass1 -> );
        // Put a tree together for this widget
        JImStr imStr = new JImStr(aClass.toString() + " " + uiComponent.getId());
        boolean openWidget = out.treeNode(imStr);
        if(openWidget) {
            boolean beginChild = out.beginChild(uiComponent.hashCode(), 0, 0, true,
                    JImWindowFlags.AlwaysVerticalScrollbar | JImWindowFlags.AlwaysHorizontalScrollbar | JImWindowFlags.AlwaysAutoResize);
            if(beginChild) {
                Field[] declaredFields = aClass.getDeclaredFields();
                out.columns(2, null, true);

                out.textWrapped(CLASS_FIELD_COLUMN_1);
                out.nextColumn();
//                out.textWrapped(CLASS_FIELD_COLUMN_2);
//                out.nextColumn();
                out.textWrapped(CLASS_FIELD_COLUMN_3);
                out.nextColumn();
                out.separator();

                int lastElement = declaredFields.length - 1;
                for (int j = 0; j < declaredFields.length; j++) {
                    boolean isLastElement = j == lastElement;

                    Field field = declaredFields[j];
                    String fieldName = field.getName();


                    Class<?> type = field.getType();
                    if(type.isPrimitive()){
                        JImStr jimStr = jimStrCache.computeIfAbsent(field, s -> new JImStr(fieldName));

                        if (type.isAssignableFrom(byte.class)) {
                            writer.getCacheModifySetInt(field, fieldName, uiComponent, value -> out.inputInt(NO_LABEL, value, 1, 1, 0));
                            out.nextColumn();
                            out.textWrapped(BYTE);
                            out.nextColumn();
                            if(!isLastElement) out.separator();
                        } else if (type.isAssignableFrom(short.class)) {
                            writer.getCacheModifySetInt(field, fieldName, uiComponent, value -> out.inputInt(NO_LABEL, value, 1, 1, 0));
                            out.nextColumn();
                            out.textWrapped(SHORT);
                            out.nextColumn();
                            if(!isLastElement) out.separator();
                        } else if (type.isAssignableFrom(int.class)) {
                            // TODO convert into a pop up selectable list?
                            if (fieldName.equals("flags")) {
                                int flags = field.getInt(uiComponent);
                                flags = drawFlagsList(out, aClass, flags);
                                field.setInt(uiComponent, flags);
                            } else {
                                writer.getCacheModifySetInt(field, fieldName, uiComponent,
                                        value -> out.inputInt(jimStr, value, 1, 1, 0));
                                out.nextColumn();
                                out.textWrapped(INT);
                                out.nextColumn();
                            }
                            if(!isLastElement) out.separator();
                        } else if (type.isAssignableFrom(long.class)) {

                            // TODO is a buffer
                        } else if (type.isAssignableFrom(float.class)) {
                            writer.getCacheModifySetFloat(field, fieldName, uiComponent,
                                    value -> out.inputFloat(jimStr, value, 1, 1, FLOAT_FORMAT, 0));
                            out.nextColumn();
                            out.textWrapped(FLOAT);
                            out.nextColumn();
                            if(!isLastElement) out.separator();
                        } else if (type.isAssignableFrom(double.class)) {
                            writer.getCacheModifySetDouble(field, fieldName, uiComponent,
                                    value -> out.inputDouble(jimStr, value, 1, 1, DOUBLE_FORMAT, 0));
                            out.nextColumn();
                            out.textWrapped(DOUBLE);
                            out.nextColumn();
                            if(!isLastElement) out.separator();
                        } else if (type.isAssignableFrom(boolean.class)) {
                            writer.getCacheModifySetBool(field, fieldName, uiComponent,
                                    value -> out.checkbox(jimStr, value));
                            out.nextColumn();
                            out.textWrapped(BOOLEAN);
                            out.nextColumn();
                            if(!isLastElement) out.separator();
                        }
                    } else {
                        Object object = field.get(uiComponent);
                        if (object == null) {
                            out.textWrapped(NULL);
                            return;
                        }


                        // Strings and JimStr horribly slow, using JImInputTextFlags.EnterReturnsTrue prevents useless
                        if (object instanceof String) {
                            String string = (String) object;
                            byte[] buffer = stringFieldCache.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> new byte[STRING_SIZE_CAP]);

                            Arrays.fill(buffer, (byte) 0);

                            System.arraycopy(string.getBytes(), 0, buffer, 0, string.length());

                            boolean valueChanged = out.inputText("label", buffer, JImInputTextFlags.EnterReturnsTrue);
                            out.sameLine();
                            showHelpMarker(out,"When entering text, you must press \"Enter\" to make changes.");
                            if (valueChanged) {
                                String s = new String(buffer, 0, bufferEndIndex(buffer), StandardCharsets.UTF_8);
                                field.set(uiComponent, s);
                            }
                            out.nextColumn();
                            out.textWrapped(STRING);
                            out.nextColumn();
                            if (!isLastElement) out.separator();
                        } else if (object instanceof JImStr) {
                            JImStr jImStr = (JImStr) object;
                            try {
                                // TODO This is TEMPORARY waiting for PR #39
                                Field bytesField = jImStr.getClass().getDeclaredField("bytes");
                                bytesField.setAccessible(true);
                                // Get the JIMSTR Bytes
                                byte[] bytes = (byte[]) bytesField.get(jImStr);

                                // Lets get the cached buffer object and then fill and copy the data
                                byte[] buffer = stringFieldCache.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> new byte[STRING_SIZE_CAP]);
                                Arrays.fill(buffer, (byte) 0);
                                System.arraycopy(bytes, 0, buffer, 0, bytes.length);

                                boolean valueChanged = out.inputText("label", buffer,
                                        JImInputTextFlags.EnterReturnsTrue);
                                out.sameLine();
                                showHelpMarker(out,"When entering text, you must press \"Enter\" to make changes.");
                                if (valueChanged) {
                                    // Lets make a new JImStr
                                    JImStr s = new JImStr(new String(buffer, 0, bufferEndIndex(buffer), StandardCharsets.UTF_8));
                                    field.set(uiComponent, s);
                                }
                                out.nextColumn();
                                out.textWrapped(STRING);
                                out.nextColumn();
                                if (!isLastElement) out.separator();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        } else if (object instanceof UIComponent) {
                            write0(out, (UIComponent) object, writer);
                        } else if (object instanceof UIComponent[]) {
                            UIComponent[] objectField = (UIComponent[]) object;
                            if (objectField == null || objectField.length == 0 || allElementsNull(objectField)) {
                                out.textWrapped(EMPTY_ARRAY);
                                return;
                            }
                            write(out, objectField, writer);
                        }
                    }
                }
                out.columns(1);
                out.separator();
                out.endChild();
            }
            out.treePop();
        }
    }

    protected static Map<Class<?>, Class<?>> widgetFlagCache = new WeakHashMap<>();

    static {
        widgetFlagCache.put(Child.class, JImWindowFlagsType.class);
        widgetFlagCache.put(Window.class, JImWindowFlagsType.class);
        widgetFlagCache.put(WindowDecorated.class, JImWindowFlagsType.class);
    }

    private int drawFlagsList(JImGui out, Class<? extends UIComponent> clazz, int flags){

        Class<Flag> flagClass = (Class<Flag>) widgetFlagCache.get(clazz);

        // Look up flags
        Flag[] selectedFlagsArray = Flag.getAsFlags(flagClass, flags);
        List<Flag> selectedFlags = new ArrayList<>();
        Collections.addAll(selectedFlags, selectedFlagsArray);

        Flag[] allFlags = flagClass.getEnumConstants();

        boolean drawn = out.listBoxHeader(NO_LABEL, allFlags.length, 5);
        if(drawn){
            // Skip NoSuchFlag
            for (int i = 1; i < allFlags.length; i++) {
                Flag flag = allFlags[i];
                boolean wasSelected = Flag.hasFlag(flag, selectedFlagsArray);
                boolean selected = out.selectable0(flag.toString(), wasSelected, JImSelectableFlags.Nothing);
                if(selected){
                    if(wasSelected){
                        selectedFlags.remove(flag);
                    } else {
                        selectedFlags.add(flag);
                    }
                }
            }
            out.listBoxFooter();
        }
        boolean button = out.button(CLEAR_FLAGS, 100, 25);
        if(button){
            selectedFlags.clear();
        }
        out.nextColumn();
        out.textWrapped(flagClass.getSimpleName()); out.sameLine(); showHelpMarker(out, flagClass.toString());
        out.nextColumn();
        return selectedFlags.stream().mapToInt(Flag::get).sum();
    }

    private static void showHelpMarker(@NotNull JImGui imGui, @NotNull String description) {
        imGui.textDisabled("(?)");
        if (imGui.isItemHovered()) {
            imGui.beginTooltip();
            imGui.pushTextWrapPos(imGui.getFontSize() * 35.0f);
            imGui.text(description);
            imGui.popTextWrapPos();
            imGui.endTooltip();
        }
    }



    private boolean allElementsNull(UIComponent[] objects) {
        for (int i = 0; i < objects.length; i++) {
            if(objects[i] != null){
                return false;
            }
        }
        return true;
    }

    public static int bufferEndIndex(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

    public void copyStringIntoBuffer(String textInput, byte[] buffer) {
        Arrays.fill(buffer, (byte) 0);
        System.arraycopy(textInput.getBytes(), 0, buffer, 0, textInput.length());
    }

    public int getBufferEndIndex(byte[] buffer) {
        return bufferEndIndex(buffer);
    }
    private void dispose() {
        manager.deallocateAll();
    }

}

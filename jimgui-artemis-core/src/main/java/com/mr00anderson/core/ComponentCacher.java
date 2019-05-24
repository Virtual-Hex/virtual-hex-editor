package com.mr00anderson.core;

import com.artemis.Component;
import org.ice1000.jimgui.JImGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ComponentCacher {
    INSTANCE;


    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentCacher.class);

    public Map<Class<? extends Component>, ComponentData> componentCacheMap = new HashMap<>();

    ComponentCacher() {
        // Map the fields so we can get the type and reflectively draw
    }


    // Annotations for tooltips, help ect, can also
    // TODO Validation
    // Edit the fields, or lock them
    public void cacheComponent(Component component){
        Class<? extends Component> aClass = component.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();


        System.out.println(Arrays.toString(declaredFields));
        for (int i = 0; i < declaredFields.length; i++) {
            Field f = declaredFields[i];


            // use equals to compare the data type.
            if (isType(f, byte.class, Byte.class)) {
                LOGGER.debug("Field is of type byte or Byte {}", f);
            } else if (isType(f, short.class, Short.class)) {
                LOGGER.debug("Field is of type short or Short {}", f);
            } else if (isType(f, int.class, Integer.class)) {
                LOGGER.debug("Field is of type int or integer {}", f);
            } else if (isType(f, long.class, Long.class)) {
                LOGGER.debug("Field is of type long or Long {}", f);
            } else if (isType(f, float.class, Float.class)) {
                LOGGER.debug("Field is of type float or Float {}", f);
            } else if (isType(f, double.class, Double.class)) {
                LOGGER.debug("Field is of type double or DOuble {}", f);
            } else if (isType(f, String.class)) {
                LOGGER.debug("Field is of type string {}", f);
            } else if (isType(f, Component.class)){
                LOGGER.debug("Field is of type Component {}", f);
//            } else if (isType(f, Component[].class)){
//                // Todo Remove or move to aray part
//                LOGGER.debug("Field is of type Component[] {}", f);
//            } else {
                // TODO Error not assignable
            }

            //for other data type

            //Map
            //if(f.getType().equals(Map.class))

            //Set
            //if(f.getType().equals(Set.class))

            //primitive int
            //if(f.getType().equals(int.class))
            //if(f.getType().equals(Integer.TYPE))

            //primitive long
            //if(f.getType().equals(long.class))
            //if(f.getType().equals(Long.TYPE))

        }
    }

    // When drawing we need to have validation types to attach


    public void drawSlow(JImGui imGui, Component component){
        Class<? extends Component> aClass = component.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            Field f = declaredFields[i];

//            static char buf2[64] = ""; ImGui::InputText("decimal", buf2, 64, ImGuiInputTextFlags_CharsDecimal);

            // use equals to compare the data type.
            if (isType(f, byte.class, Byte.class)) {

                LOGGER.debug("Field is of type byte or Byte {}", f);
            } else if (isType(f, short.class, Short.class)) {
                LOGGER.debug("Field is of type short or Short {}", f);
            } else if (isType(f, int.class, Integer.class)) {
                LOGGER.debug("Field is of type int or integer {}", f);
            } else if (isType(f, long.class, Long.class)) {
                LOGGER.debug("Field is of type long or Long {}", f);
            } else if (isType(f, float.class, Float.class)) {
                LOGGER.debug("Field is of type float or Float {}", f);
            } else if (isType(f, double.class, Double.class)) {
                LOGGER.debug("Field is of type double or DOuble {}", f);
            } else if (isType(f, String.class)) {
                LOGGER.debug("Field is of type string {}", f);
            } else if (isType(f, Component.class)){
                // TODO put a text clazzField name or header for new components, or array of

                // Find editable tree ecample
                LOGGER.debug("Field is of type Component {}", f);
//            } else if (isType(f, Component[].class)){
//                // Todo Remove or move to aray part
//                LOGGER.debug("Field is of type Component[] {}", f);
//            } else {
                // TODO Error not assignable
            }

            //for other data type

            //Map
            //if(f.getType().equals(Map.class))

            //Set
            //if(f.getType().equals(Set.class))

            //primitive int
            //if(f.getType().equals(int.class))
            //if(f.getType().equals(Integer.TYPE))

            //primitive long
            //if(f.getType().equals(long.class))
            //if(f.getType().equals(Long.TYPE))

        }
    }


    public boolean isType(Field field, Class<?> aClazz){
        return field.getType().isAssignableFrom(aClazz);
    }

    public boolean isType(Field field, Class<?> aClazz1, Class<?> aClazz2){
        Class<?> fieldType = field.getType();
        return fieldType.isAssignableFrom(aClazz1) || fieldType.isAssignableFrom(aClazz2);
    }

    public boolean isType(Field field, Class<?>... aClazz){
        Class<?> fieldType = field.getType();
        for (Class<?> clazz : aClazz) {
            if (!fieldType.isAssignableFrom(clazz)) return false;
        }
        return true;
    }
}

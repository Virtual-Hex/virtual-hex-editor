package com.virtual_hex.editor;

import java.lang.annotation.*;

/**
 * This is used to annotate a class or a method as a method for handling UI Drawing
 *
 * Class level well instantiate the class as the handler
 *
 * If {@see UIComponentsRegister}
 *  - The class will be scanned
 *  - Fields can either be a UIComponentRegister
 *  - Methods can be annotated to return UIComponentWriter
 *
 *
 */
@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UIComponentRegister {
    int version() default 0;
    String name() default "Default";
    Class<?> typeKey();
}

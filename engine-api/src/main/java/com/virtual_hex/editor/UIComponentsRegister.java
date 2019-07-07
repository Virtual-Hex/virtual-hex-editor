package com.virtual_hex.editor;

import java.lang.annotation.*;

/**
 * This just marks the class to be scanned for static classes and methods.
 *
 * This will instantiate the class to access the field values
 *
 * {@see UIComponentRegister}
 */
@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UIComponentsRegister {
}

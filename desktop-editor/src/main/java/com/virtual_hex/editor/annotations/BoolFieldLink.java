package com.virtual_hex.editor.annotations;

import java.lang.annotation.*;

/**
 * This is used to annotate which field has a boolean and is considered for toggle linking via reflection
 */
@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BoolFieldLink {
    /**
     *
     * @return String the id of the field to link with another
     */
    String id() default ""; // May not be needed
}

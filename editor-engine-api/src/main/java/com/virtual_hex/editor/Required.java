package com.virtual_hex.editor;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This is used to mark a required field
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}

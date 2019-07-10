package com.virtual_hex.editor;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HasFlags {
    Class<?> typeKey();
}

package com.virtual_hex.editor;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})// Use of OpenableType ???
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ComponentRegister {
    int version() default 0;
    String name() default "Default";
    Class<?> typeKey();
}

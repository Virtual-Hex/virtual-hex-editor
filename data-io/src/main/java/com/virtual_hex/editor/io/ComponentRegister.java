package com.virtual_hex.editor.io;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})// Use of OpenableType ???
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ComponentRegister {
    int version() default 0;

    Class<?> typeKey();

    ComponentOperation operation() default ComponentOperation.READ_WRITE;
}

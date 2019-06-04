package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.types.Disposable;
import com.mr00anderson.jawe.types.InstancePurge;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;

public interface JImGuiTypeHandler extends Disposable, InstancePurge {
    void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw);
}

package com.mr00anderson.editor.jimgui.handlers;

import com.mr00anderson.editor.Disposable;
import com.mr00anderson.editor.InstancePurge;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;

public interface JImGuiTypeHandler extends Disposable, InstancePurge {
    void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw);
}

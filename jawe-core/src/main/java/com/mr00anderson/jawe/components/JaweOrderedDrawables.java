package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import org.ice1000.jimgui.JImGui;

import java.util.Collections;
import java.util.LinkedList;

public class JaweOrderedDrawables extends PooledComponent implements JaweDrawable {

    public LinkedList<JaweDrawable> drawables = new LinkedList<>();

    public JaweOrderedDrawables() {
    }

    public JaweOrderedDrawables(JaweDrawable... drawableElements) {
        Collections.addAll(drawables, drawableElements);
    }

    @Override
    protected void reset() {
        drawables.clear();
    }

    @Override
    public void draw(JImGui imGui) {
        drawables.forEach((d) -> d.draw(imGui));
    }
}

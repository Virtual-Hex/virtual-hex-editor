package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JaweOrderedDrawables extends PooledComponent {

    public LinkedList<Object> drawables = new LinkedList<>();
    public ConcurrentLinkedQueue<Object> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<Object> removeWindowQueue = new ConcurrentLinkedQueue<>();

    public JaweOrderedDrawables() {
    }

    public JaweOrderedDrawables(Object... drawableElements) {
        Collections.addAll(drawables, drawableElements);
    }

    @Override
    protected void reset() {
        drawables.clear();
    }

}

package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweWindow;
import org.ice1000.jimgui.JImGui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JaweOrderedDrawables extends PooledComponent implements JaweDrawable {

    public LinkedList<JaweDrawable> drawables = new LinkedList<>();
    public ConcurrentLinkedQueue<JaweWindow> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<JaweWindow> removeWindowQueue = new ConcurrentLinkedQueue<>();

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
        JaweWindow addPoll = addWindowQueue.poll();
        if(addPoll != null){
            drawables.add(addPoll);
        }

        drawables.forEach((d) -> d.draw(imGui));

        JaweWindow removePoll = removeWindowQueue.poll();
        if(removePoll != null){
            drawables.remove(removePoll);
        }
    }
}

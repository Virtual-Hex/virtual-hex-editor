package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import org.ice1000.jimgui.JImGui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JaweOrderedDrawables<T extends JaweDrawable> extends PooledComponent implements JaweDrawable {

    public LinkedList<T> drawables = new LinkedList<>();
    public ConcurrentLinkedQueue<T> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<T> removeWindowQueue = new ConcurrentLinkedQueue<>();

    public JaweOrderedDrawables() {
    }

    public JaweOrderedDrawables(T... drawableElements) {
        Collections.addAll(drawables, drawableElements);
    }

    @Override
    protected void reset() {
        drawables.clear();
    }

    @Override
    public void draw(JImGui imGui) {
        T addPoll = addWindowQueue.poll();
        if(addPoll != null){
            drawables.add(addPoll);
        }

        drawables.forEach((d) -> d.draw(imGui));

        T removePoll = removeWindowQueue.poll();
        if(removePoll != null){
            drawables.remove(removePoll);
        }
    }
}

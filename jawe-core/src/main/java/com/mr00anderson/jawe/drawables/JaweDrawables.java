package com.mr00anderson.jawe.drawables;

import com.artemis.PooledComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * If no list is provided linked list will be used
 */
public class JaweDrawables extends PooledComponent {

    public List<Object> drawables;
    public ConcurrentLinkedQueue<Object> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<Object> removeWindowQueue = new ConcurrentLinkedQueue<>();

    /**
     * Uses LinkedList
     */
    public JaweDrawables() {
        this.drawables = new LinkedList<>();
    }

    /**
     *
     * @param ordered true uses LinkedList, false uses ArrayList
     */
    public JaweDrawables(boolean ordered){
        if(ordered){
            drawables = new LinkedList<>();
        } else {
            drawables = new ArrayList<>();
        }
    }

    /**
     * Uses LinkedList
     *
     * @param drawableElements the items to be drawn
     */
    public JaweDrawables(Object... drawableElements) {
        this.drawables = new LinkedList<>();
        Collections.addAll(drawables, drawableElements);
    }

    /**
     * Uses LinkedList
     *
     * @param drawableElements the items to be drawn
     */
    public JaweDrawables(boolean ordered, Object... drawableElements) {
        if(ordered) {
            this.drawables = new LinkedList<>();
        } else {
            this.drawables = new ArrayList<>();
        }
        Collections.addAll(drawables, drawableElements);
    }

    /**
     *
     * @param drawables List of items to draw
     */
    public JaweDrawables(List<Object> drawables) {
        this.drawables = drawables;
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public JaweDrawables(Class<List> listClazz) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @param drawableElements the items to be drawn
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public JaweDrawables(Class<List> listClazz, Object... drawableElements) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
        Collections.addAll(drawables, drawableElements);
    }

    // TODO Convert from one type of JaweDrawables to another,
    //  or allow list changing for performance and ordering changing

    @Override
    protected void reset() {
        drawables.clear();
    }

}

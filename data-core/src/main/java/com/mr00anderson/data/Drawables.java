package com.mr00anderson.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * If no list is provided linked list will be used
 */
public class Drawables {

    public List<Object> drawables;
    public ConcurrentLinkedQueue<Object> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<Object> removeWindowQueue = new ConcurrentLinkedQueue<>();

    /**
     * Uses LinkedList
     */
    public Drawables() {
        this.drawables = new LinkedList<>();
    }

    /**
     *
     * @param ordered true uses LinkedList, false uses ArrayList
     */
    public Drawables(boolean ordered){
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
    public Drawables(Object... drawableElements) {
        this.drawables = new LinkedList<>();
        Collections.addAll(drawables, drawableElements);
    }

    /**
     * Uses LinkedList
     *
     * @param drawableElements the items to be drawn
     */
    public Drawables(boolean ordered, Object... drawableElements) {
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
    public Drawables(List<Object> drawables) {
        this.drawables = drawables;
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Drawables(Class<List> listClazz) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @param drawableElements the items to be drawn
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Drawables(Class<List> listClazz, Object... drawableElements) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
        Collections.addAll(drawables, drawableElements);
    }

    // TODO Convert from one type of Drawables to another,
    //  or allow list changing for performance and ordering changing


    protected void reset() {
        drawables.clear();
        addWindowQueue.clear();
        removeWindowQueue.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drawables that = (Drawables) o;

        return drawables != null ? drawables.equals(that.drawables) : that.drawables == null;
    }

    @Override
    public int hashCode() {
        return drawables != null ? drawables.hashCode() : 0;
    }
}

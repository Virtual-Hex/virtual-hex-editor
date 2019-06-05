package com.virtual_hex.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * If no list is provided linked list will be used
 *
 * This represents a collection of drawables. You must use the queues to add or remove from this list.
 * - This is due to concurrent modification of any drawables wanting to make changes to its parents
 */
public class UIDataList implements UIData {

    public List<UIData> drawables;
    public ConcurrentLinkedQueue<UIData> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<UIData> removeWindowQueue = new ConcurrentLinkedQueue<>();

    /**
     * Uses LinkedList
     */
    public UIDataList() {
        this.drawables = new LinkedList<>();
    }

    /**
     *
     * @param ordered true uses LinkedList, false uses ArrayList
     */
    public UIDataList(boolean ordered){
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
    public UIDataList(UIData... drawableElements) {
        this.drawables = new LinkedList<>();
        Collections.addAll(drawables, drawableElements);
    }

    /**
     * Uses LinkedList
     *
     * @param drawableElements the items to be drawn
     */
    public UIDataList(boolean ordered, UIData... drawableElements) {
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
    public UIDataList(List<UIData> drawables) {
        this.drawables = drawables;
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public UIDataList(Class<List> listClazz) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @param drawableElements the items to be drawn
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public UIDataList(Class<List> listClazz, UIData... drawableElements) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
        Collections.addAll(drawables, drawableElements);
    }

    // TODO Convert from one type of uiDataList to another,
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

        UIDataList that = (UIDataList) o;

        return drawables != null ? drawables.equals(that.drawables) : that.drawables == null;
    }

    @Override
    public int hashCode() {
        return drawables != null ? drawables.hashCode() : 0;
    }
}

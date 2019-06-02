package com.mr00anderson.jawe.drawables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JaweDrawablesOpenFlagLabel extends JaweOpenFlagLabel {

    public List<Object> drawables;
    public ConcurrentLinkedQueue<Object> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<Object> removeWindowQueue = new ConcurrentLinkedQueue<>();



    /**
     * Uses LinkedList
     */
    public JaweDrawablesOpenFlagLabel() {
        this.drawables = new LinkedList<>();
    }

    public JaweDrawablesOpenFlagLabel(String label) {
        super(label);
    }

    public JaweDrawablesOpenFlagLabel(int flags) {
        super(flags);
    }

    public JaweDrawablesOpenFlagLabel(String label, int flags) {
        super(label, flags);
    }

    /**
     *
     * @param ordered true uses LinkedList, false uses ArrayList
     */
    public JaweDrawablesOpenFlagLabel(boolean ordered){
        if(ordered){
            drawables = new LinkedList<>();
        } else {
            drawables = new ArrayList<>();
        }
    }

    public JaweDrawablesOpenFlagLabel(String label, boolean opened) {
        super(label, opened);
    }

    public JaweDrawablesOpenFlagLabel(int flags, boolean opened) {
        super(flags, opened);
    }

    public JaweDrawablesOpenFlagLabel(String label, int flags, boolean opened) {
        super(label, flags, opened);
    }

    /**
     * Uses LinkedList
     *
     * @param drawableElements the items to be drawn
     */
    public JaweDrawablesOpenFlagLabel(Object... drawableElements) {
        this.drawables = new LinkedList<>();
        Collections.addAll(drawables, drawableElements);
    }

    /**
     * Uses LinkedList
     *
     * @param drawableElements the items to be drawn
     */
    public JaweDrawablesOpenFlagLabel(boolean ordered, Object... drawableElements) {
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
    public JaweDrawablesOpenFlagLabel(List<Object> drawables) {
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(String label, List<Object> drawables) {
        super(label);
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(int flags, List<Object> drawables) {
        super(flags);
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(String label, int flags, List<Object> drawables) {
        super(label, flags);
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(boolean opened, List<Object> drawables) {
        super(opened);
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(String label, boolean opened, List<Object> drawables) {
        super(label, opened);
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(int flags, boolean opened, List<Object> drawables) {
        super(flags, opened);
        this.drawables = drawables;
    }

    public JaweDrawablesOpenFlagLabel(String label, int flags, boolean opened, List<Object> drawables) {
        super(label, flags, opened);
        this.drawables = drawables;
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public JaweDrawablesOpenFlagLabel(Class<List> listClazz) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
    }

    /**
     *
     * @param listClazz list clazz type to use for list
     * @param drawableElements the items to be drawn
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public JaweDrawablesOpenFlagLabel(Class<List> listClazz, Object... drawableElements) throws IllegalAccessException, InstantiationException {
        this.drawables = listClazz.newInstance();
        Collections.addAll(drawables, drawableElements);
    }

    // TODO Convert from one type of JaweDrawables to another,
    //  or allow list changing for performance and ordering changing

}

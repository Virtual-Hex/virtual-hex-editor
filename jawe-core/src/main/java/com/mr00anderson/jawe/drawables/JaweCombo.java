package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 *  Widgets: Combo Box
 *  - The new BeginCombo()/EndCombo() api allows you to manage your contents and selection state however you want it, by creating e.g. Selectable() items.
 */
public class JaweCombo{

    public String label;
    public int flags = 0;
    public JaweDrawables jaweSelectables;
    public JaweSelectable currentSelectable = new JaweSelectable("");

    public JaweCombo() {
    }

    public JaweCombo(String label, JaweDrawables jaweSelectables) {
        this.label = label;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, int flags, JaweDrawables jaweSelectables) {
        this.label = label;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, JaweSelectable currentSelectable, JaweDrawables jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, JaweSelectable currentSelectable, int flags, JaweDrawables jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }


    public void draw(JImGui imGui) {
//
//        boolean beginCombo = imGui.beginCombo(label, currentSelectable.label, flags);
//        if(beginCombo){
//            // Replace with JawOrderDrawables to allow inserting
//            LinkedList<JaweSelectable> worldsHeader = jaweSelectables.worldsHeader;
//
//            for (int i = 0; i < worldsHeader.size(); i++) {
//                JaweSelectable jaweSelectable = worldsHeader.get(i);
//                jaweSelectable.draw(imGui);
//                boolean accessValue = jaweSelectable.open.accessValue();
//                if(accessValue){
//                    currentSelectable.open.modifyValue(false);
//                    currentSelectable = jaweSelectable;
//                    imGui.setItemDefaultFocus();
//                }
//            }
//            imGui.endCombo();
//        }

    }
}

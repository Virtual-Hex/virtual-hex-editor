package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.components.JaweOrderedDrawables;
import org.ice1000.jimgui.JImGui;

import java.util.LinkedList;

/**
 *  Widgets: Combo Box
 *  - The new BeginCombo()/EndCombo() api allows you to manage your contents and selection state however you want it, by creating e.g. Selectable() items.
 */
public class JaweCombo implements JaweDrawable{

    public String label;
    public int flags = 0;
    public JaweOrderedDrawables<JaweSelectable> jaweSelectables;
    public JaweSelectable currentSelectable = new JaweSelectable("");

    public JaweCombo() {
    }

    public JaweCombo(String label, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, int flags, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, JaweSelectable currentSelectable, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, JaweSelectable currentSelectable, int flags, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    @Override
    public void draw(JImGui imGui) {

        boolean beginCombo = imGui.beginCombo(label, currentSelectable.label, flags);
        if(beginCombo){
            // Replace with JawOrderDrawables to allow inserting
            LinkedList<JaweSelectable> drawables = jaweSelectables.drawables;

            for (int i = 0; i < drawables.size(); i++) {
                JaweSelectable jaweSelectable = drawables.get(i);
                jaweSelectable.draw(imGui);
                boolean accessValue = jaweSelectable.selected.accessValue();
                if(accessValue){
                    currentSelectable.selected.modifyValue(false);
                    currentSelectable = jaweSelectable;
                    imGui.setItemDefaultFocus();
                }
            }
            imGui.endCombo();
        }

    }
}

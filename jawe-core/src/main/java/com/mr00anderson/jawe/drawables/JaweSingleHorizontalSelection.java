package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.components.JaweOrderedDrawables;
import com.mr00anderson.jawe.handlers.ActivationHandler;

import java.util.Collections;

public class JaweSingleHorizontalSelection extends JaweOrderedDrawables {

    public String selected = "";

    public JaweSingleHorizontalSelection() {
    }

    public JaweSingleHorizontalSelection(JaweSelectable... drawableElements) {

        ActivationHandler<JaweSelectable> jaweSelectableAction = new ActivationHandler<JaweSelectable>() {
            @Override
            public void handle(JaweSelectable imGuiDrawable) {
                selected = imGuiDrawable.label;
                for (int i = 0; i < drawableElements.length; i++) {
                    JaweSelectable drawableElement = drawableElements[i];
                    if(drawableElement.label.equals(selected)){
                        drawableElement.selected.modifyValue(true);
                    } else {
                        drawableElement.selected.modifyValue(false);
                    }
                }
            }
        };

        for (int i = 0; i < drawableElements.length; i++) {
            JaweSelectable drawableElement = drawableElements[i];
            drawableElement.onActivation = jaweSelectableAction;
            drawableElement.flags = 0;
            Collections.addAll(
                    drawables,
                    drawableElement,
                    JaweJImGui.SAME_LINE,
                    JaweJImGui.SEPARATOR,
                    JaweJImGui.SAME_LINE
            );
        }
    }

    // List of buttons horixontally, so build this layout from provided buttons
}


//        if (ImGui::TreeNode("Selection State: Single Selection"))
//                {
//static int selected = -1;
//        for (int n = 0; n < 5; n++)
//        {
//        char buf[32];
//        sprintf(buf, "Object %d", n);
//        if (ImGui::Selectable(buf, selected == n))
//        selected = n;
//        }
//        ImGui::TreePop();
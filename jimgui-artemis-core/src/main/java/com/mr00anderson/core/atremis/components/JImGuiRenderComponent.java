package com.mr00anderson.core.atremis.components;

import com.artemis.Component;
import com.artemis.World;
import com.mr00anderson.core.Disposable;
import com.mr00anderson.core.jimgui.JImGuiDrawable;
import org.ice1000.jimgui.JImGui;

public class JImGuiRenderComponent extends Component implements Disposable {

    // TODO Change to native bool and add native serializers
    public boolean active;
    public JImGuiDrawable jImGuiDrawable;

    public JImGuiRenderComponent() {
    }

    public void drawSafe(JImGui imGui, World world){
        if(active) jImGuiDrawable.draw(imGui, world);
    }

    @Override
    public void dispose() {
        jImGuiDrawable.dispose();
    }
}

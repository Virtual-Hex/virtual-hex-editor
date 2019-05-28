package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.mr00anderson.jawe.types.Disposable;
import org.ice1000.jimgui.JImGui;

/**
 * This should represent the smallest unit translated to JImGui
 * This should map to JImGui#methodNameHere when created
 */
public interface JaweDrawable extends Disposable {
//    void init(World world);
    void draw(JImGui imGui, World world);

}

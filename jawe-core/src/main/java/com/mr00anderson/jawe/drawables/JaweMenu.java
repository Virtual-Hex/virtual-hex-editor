package com.mr00anderson.jawe.drawables;

import com.artemis.annotations.PooledWeaver;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweMenu {

    // This class probably should be a any menu builder, essentially a linked list od worldsHeader


    public void draw(JImGui imGui) {

    }

    public static final class JaweMenuBuilder {


        private JaweMenuBuilder() {
        }

        public static JaweMenu.JaweMenuBuilder aJaweWindow() {
            return new JaweMenu.JaweMenuBuilder();
        }
    }

}

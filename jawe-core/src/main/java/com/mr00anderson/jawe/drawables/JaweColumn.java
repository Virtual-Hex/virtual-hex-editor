package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweColumn implements JaweDrawable {

    public int count;
    public String stringId;
    public boolean border;

    @Override
    public void draw(JImGui imGui, World world) {
        imGui.columns(count, stringId, border);
    }

    @Override
    public void dispose() {
        // Nothing to dispose of here
    }

    public static final class JaweColumnBuilder {
        public int count;
        public String stringId;
        public boolean border;

        private JaweColumnBuilder() {
        }

        public static JaweColumnBuilder aJaweColumn() {
            return new JaweColumnBuilder();
        }

        public JaweColumnBuilder count(int count) {
            this.count = count;
            return this;
        }

        public JaweColumnBuilder stringId(String stringId) {
            this.stringId = stringId;
            return this;
        }

        public JaweColumnBuilder border(boolean border) {
            this.border = border;
            return this;
        }

        public JaweColumn build() {
            JaweColumn jaweColumn = new JaweColumn();
            jaweColumn.stringId = this.stringId;
            jaweColumn.count = this.count;
            jaweColumn.border = this.border;
            return jaweColumn;
        }
    }
}

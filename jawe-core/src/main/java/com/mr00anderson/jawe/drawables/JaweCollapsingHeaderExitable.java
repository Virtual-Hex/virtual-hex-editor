package com.mr00anderson.jawe.drawables;

public class JaweCollapsingHeaderExitable extends JaweTreeNodeEx {

    /**
     * Can be null, if not null an X will appear
     */
    public boolean open;

    public JaweCollapsingHeaderExitable(String label) {
        super(label);
    }

    public JaweCollapsingHeaderExitable(String label, Object... drawableElements) {
        super(label, drawableElements);
    }

    public JaweCollapsingHeaderExitable(String label, int flags) {
        super(label, flags);
    }

    public JaweCollapsingHeaderExitable(String label, int flags, Object... drawableElements) {
        super(label, flags, drawableElements);
    }

    public JaweCollapsingHeaderExitable() {
    }

    public JaweCollapsingHeaderExitable(Object[] drawableElements) {
        super(drawableElements);
    }

}

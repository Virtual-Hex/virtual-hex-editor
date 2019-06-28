package com.virtual_hex.editor.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Note: Thought about extending label here, because it would allow more inheritance.
 *
 * @param > generally UIComponents can be mixed, but sometimes the types should be restricted
 */
public class UIComponents extends AbstractUIComponent {

    // Change to array and add helper utils to insert, remove, move since this will modify less
    public List<UIComponent> uiComponents;
    public ConcurrentLinkedQueue<UIComponent> addComponentQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<UIComponent> removeComponentQueue = new ConcurrentLinkedQueue<>();

    public UIComponents() {
        this.uiComponents = new ArrayList<>();
    }

    /**
     * @param uiComponents String of items to show which are converted to new Text(string)
     */
    public UIComponents(String... uiComponents) {
        this.uiComponents = new ArrayList<>();
        for (int i = 0; i < uiComponents.length; i++) {
            this.uiComponents.add(new Text(uiComponents[i]));
        }
    }

    /**
     * @param uiComponents List of items to show
     */
    public UIComponents(UIComponents... uiComponents) {
        this.uiComponents = new ArrayList<>();
        Collections.addAll(this.uiComponents, uiComponents);
    }

    /**
     * @param uiComponents List of items to show
     */
    public UIComponents(UIComponent... uiComponents) {
        this.uiComponents = new ArrayList<>();
        Collections.addAll(this.uiComponents, uiComponents);
    }

    protected void reset() {
        uiComponents.clear();
        addComponentQueue.clear();
        removeComponentQueue.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UIComponents that = (UIComponents) o;

        if (!uiComponents.equals(that.uiComponents)) return false;
        if (!addComponentQueue.equals(that.addComponentQueue)) return false;
        return removeComponentQueue.equals(that.removeComponentQueue);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uiComponents.hashCode();
        result = 31 * result + addComponentQueue.hashCode();
        result = 31 * result + removeComponentQueue.hashCode();
        return result;
    }
}

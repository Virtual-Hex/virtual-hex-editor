package com.virtual_hex.editor.data;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: Thought about extending label here, because it would allow more inheritance.
 *
 * @param > generally UIComponents can be mixed, but sometimes the types should be restricted
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class UIComponents extends AbstractUIComponent {

    public static final UIComponents EMPTY = new UIComponents();

    // Change to array and add helper utils to insert, remove, move since this will modify less
    @Singular
    @NonNull
    public List<UIComponent> uiComponents = new ArrayList<>();
    @NonNull
    public List<UIComponent> addComponentQueue = new ArrayList<>();
    @NonNull
    public List<UIComponent> removeComponentQueue = new ArrayList<>();

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

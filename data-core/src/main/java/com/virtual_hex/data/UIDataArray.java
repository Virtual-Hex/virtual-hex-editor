package com.virtual_hex.data;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UIDataArray extends UIData {

    // Change to array and add helper utils to insert, remove, move since this will modify less
    public UIData[] drawables;
    public ConcurrentLinkedQueue<UIData> addWindowQueue = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<UIData> removeWindowQueue = new ConcurrentLinkedQueue<>();

    public UIDataArray() {
        this.drawables = new UIData[0];
    }

    /**
     *
     * @param drawables List of items to draw
     */
    public UIDataArray(UIData... drawables) {
        this.drawables = drawables;
    }

    protected void reset() {
        Arrays.fill(drawables, null);
        addWindowQueue.clear();
        removeWindowQueue.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UIDataArray that = (UIDataArray) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(drawables, that.drawables)) return false;
        if (addWindowQueue != null ? !addWindowQueue.equals(that.addWindowQueue) : that.addWindowQueue != null)
            return false;
        return removeWindowQueue != null ? removeWindowQueue.equals(that.removeWindowQueue) : that.removeWindowQueue == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(drawables);
        result = 31 * result + (addWindowQueue != null ? addWindowQueue.hashCode() : 0);
        result = 31 * result + (removeWindowQueue != null ? removeWindowQueue.hashCode() : 0);
        return result;
    }
}

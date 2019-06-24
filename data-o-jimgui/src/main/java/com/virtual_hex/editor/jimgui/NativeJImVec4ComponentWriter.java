package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Vec4;
import org.ice1000.jimgui.JImVec4;

import java.util.WeakHashMap;

@NativeExchange
public abstract class NativeJImVec4ComponentWriter extends NativeAllocComponentWriter {

    /**
     * Will release when out of scope the mapping, but the JImVec4, still exist in deallocate manager
     */
    private WeakHashMap<Vec4, JImVec4> cachedjimVecs = new WeakHashMap<>();

    @Override
    public void dispose() {
        super.dispose();
        cachedjimVecs.clear();
    }

    protected JImVec4 getCachedOrCreate(Vec4 color) {
        return cachedjimVecs.computeIfAbsent(color, value -> {
            JImVec4 jImVec4 = new JImVec4(color.x, color.y, color.z, color.w);
            deallocatableObjectManager.add(jImVec4);
            return jImVec4;
        });
    }

    protected void setFromTo(JImVec4 from, Vec4 to){
        to.x = from.getX();
        to.y = from.getY();
        to.z = from.getZ();
        to.w = from.getW();
    }
}

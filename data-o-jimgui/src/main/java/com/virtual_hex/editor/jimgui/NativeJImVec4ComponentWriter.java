package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Vec4;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImVec4;

@NativeExchange
public abstract class NativeJImVec4ComponentWriter extends NativeAllocComponentWriter {

    protected Int2ObjectMap<JImVec4> cachedjimVec = new Int2ObjectOpenHashMap<>();

    @Override
    public void dispose() {
        super.dispose();
        cachedjimVec.clear();
    }

    /**
     * TODO a way to clear cache after a while
     *
     * @param x
     * @param y
     * @param z
     * @param w
     * @return
     */
    protected JImVec4 create(float x, float y, float z, float w) {
        JImVec4 jImVec4 = new JImVec4(x, y, z, w);
        deallocatableObjectManager.add(jImVec4);
        return jImVec4;
    }

    protected void setFromTo(JImVec4 from, Vec4 to){
        to.x = from.getX();
        to.y = from.getY();
        to.z = from.getZ();
        to.w = from.getW();
    }
}

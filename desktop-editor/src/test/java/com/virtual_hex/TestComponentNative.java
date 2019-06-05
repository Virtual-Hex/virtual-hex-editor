package com.virtual_hex;

import com.virtual_hex.components.JaweRenderComponent;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObject;

public class TestComponentNative extends JaweRenderComponent {

    public int hi = 10;
    public NativeBool nativeBool;
    public NativeShort nativeShort;
    public NativeInt nativeInt;
    public NativeLong nativeLong;
    public NativeFloat nativeFloat;
    public NativeDouble nativeDouble;



    public TestComponentNative() {

    }

    public void dispose() {
    DeallocatableObject[] deallocateObjects = {
            nativeBool,
            nativeShort,
            nativeInt,
            nativeLong,
            nativeFloat,
            nativeDouble
    };

      for (DeallocatableObject d : deallocateObjects)
          d.deallocateNativeObject();
    }
}
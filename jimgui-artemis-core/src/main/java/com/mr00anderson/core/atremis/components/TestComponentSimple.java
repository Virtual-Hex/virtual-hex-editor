package com.mr00anderson.core.atremis.components;

import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObject;

public class TestComponentSimple extends JImGuiRenderComponent {

    public int hi = 10;
    public NativeBool nativeBool;
    public NativeShort nativeShort;
    public NativeInt nativeInt;
    public NativeLong nativeLong;
    public NativeFloat nativeFloat;
    public NativeDouble nativeDouble;



    public TestComponentSimple() {

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

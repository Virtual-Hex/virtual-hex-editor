package com.virtual_hex.components;

import org.ice1000.jimgui.NativeFloat;
import org.ice1000.jimgui.util.JImGuiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@TestOnly
public class PlatformWindowTest {
	@BeforeAll
	public static void setup() {
		JImGuiTest.useAlternativeJniLibAndCheckHeadless();
	}

	@Test
	public void testPlatformWindow() {
		main();
	}

	public static void main(String @NotNull ... args) {
		NativeFloat nativeFloat = new NativeFloat();
		JImGuiUtil.runWithinPer(10000, 16, gui -> {
			gui.sliderFloat("Value", nativeFloat, 0, 1000);
			if (gui.button("Set Window X"))
				gui.setPlatformWindowSizeX(nativeFloat.accessValue());
			if (gui.button("Set Window Y"))
				gui.setPlatformWindowSizeY(nativeFloat.accessValue());
		});
		nativeFloat.deallocateNativeObject();
	}
}

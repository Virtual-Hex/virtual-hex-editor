package com.virtual_hex.components;

import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiIO;
import org.ice1000.jimgui.flag.JImMouseIndexes;
import org.ice1000.jimgui.util.JImGuiUtil;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class JImGuiTest {
	@BeforeAll
	@TestOnly
	public static void useAlternativeJniLibAndCheckHeadless() {
		assumeFalse("true".equalsIgnoreCase(System.getenv("CI")));
		assumeFalse("true".equalsIgnoreCase(System.getenv("APPVEYOR")));
		assumeFalse("true".equalsIgnoreCase(System.getProperty("java.awt.headless")));
		JniLoader.load();
	}

	@Test
	public void jniInitialization() throws @NotNull NoSuchFieldException, @NotNull IllegalAccessException {
		try (JImGui imGui = new JImGui()) {
			Field nativeObjectPtr = JImGui.class.getDeclaredField("nativeObjectPtr");
			nativeObjectPtr.setAccessible(true);
			assertTrue((long) nativeObjectPtr.get(imGui) != 0);
			nativeObjectPtr.setAccessible(false);
		}
	}


	@Test()
	public void jniDisposal() {
		JImGui imGui = new JImGui();
		imGui.close();
		assertTrue(imGui.isDisposed());
		JImGuiIO io = imGui.getIO();
		System.out.println(io);
	}

	public static void main(String @NotNull ... args) {
		JniLoader.load();
		JImGuiUtil.runPer(15, imGui -> {
			if (imGui.isMouseClicked(JImMouseIndexes.Left)) imGui.text("Mouse clicked!");
			imGui.text(imGui.getClipboardText());
		});
	}
}

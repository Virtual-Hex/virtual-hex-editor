package com.mr00anderson.jawe.components;

import org.ice1000.jimgui.NativeInt;
import org.ice1000.jimgui.util.JImGuiUtil;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ComboTest {
	@BeforeAll
	public static void setup() {
		JImGuiTest.useAlternativeJniLibAndCheckHeadless();
	}

	@Disabled
	@Test
	public void testSandbox() {
		main();
	}

	public static void main(String @NotNull ... args) {
		JniLoader.load();
		final int totalMillis = 6000;
		NativeInt currentItem = new NativeInt();
		JImGuiUtil.runWithinPer(totalMillis, 15, imGui -> {
			imGui.combo("Wtf", currentItem, "Java\0Kotlin\0Clojure\0Ceylon\0Scala\0");
			imGui.text("Selected: " + currentItem);
		});
		currentItem.deallocateNativeObject();
	}
}
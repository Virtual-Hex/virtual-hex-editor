package com.mr00anderson.core.atremis.components;

import org.ice1000.jimgui.util.JImGuiUtil;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.mr00anderson.core.atremis.components.JImGuiTest.useAlternativeJniLibAndCheckHeadless;

public class ProgressBarTest {
	@BeforeAll
	public static void setup() {
		useAlternativeJniLibAndCheckHeadless();
	}

	@Test
	public void testSandbox() {
		main();
	}

	public static void main(String @NotNull ... args) {
		JniLoader.load();
		final long start = System.currentTimeMillis();
		final int totalMillis = 3000;
		JImGuiUtil.runWithinPer(totalMillis, 15, imGui -> {
			imGui.text("IntelliJ IDEA is booting...");
			imGui.progressBar((System.currentTimeMillis() - start) / (float) totalMillis);
			imGui.text("ICEditor is booting...");
			imGui.progressBar(2 * (System.currentTimeMillis() - start) / (float) totalMillis);
		});
	}
}
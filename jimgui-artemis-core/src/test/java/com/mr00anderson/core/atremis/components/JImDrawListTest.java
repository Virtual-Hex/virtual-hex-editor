package com.mr00anderson.core.atremis.components;

import org.ice1000.jimgui.JImDrawList;
import org.ice1000.jimgui.util.JImGuiUtil;
import org.ice1000.jimgui.util.JniLoader;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import java.awt.*;

import static com.mr00anderson.core.atremis.components.JImGuiTest.useAlternativeJniLibAndCheckHeadless;
import static org.junit.jupiter.api.Assertions.*;

public class JImDrawListTest {
	@BeforeAll
	public static void setup() {
		useAlternativeJniLibAndCheckHeadless();
	}

	@Test
	public void testSandbox() {
		main();
	}

	@SuppressWarnings("AccessStaticViaInstance")
	public static void main(@NotNull String @NotNull ... args) {
		JniLoader.load();
		JImGuiUtil.runWithinPer(5000, 15, imGui -> {
			JImDrawList windowDrawList = imGui.findWindowDrawList();
			assertNotNull(windowDrawList);
			JImDrawList overlayDrawList = imGui.findOverlayDrawList();
			assertNotNull(overlayDrawList);
			float cursorPosX = imGui.getWindowPosX();
			float cursorPosY = imGui.getWindowPosY();
			windowDrawList.addText(30,
					cursorPosX + 122,
					cursorPosY + 22,
					Color.BLUE.getRGB(), "I XiHuan Reiuji Utsuho forever\nQAQ");
			int rgb = Color.GREEN.getRGB();
			windowDrawList.addCircle(
					cursorPosX + 250,
					cursorPosY + 150,
					50, rgb, 100);
			windowDrawList.addText(72,
					cursorPosX + 230,
					cursorPosY + 120,
					rgb, "6");
		});
	}
}

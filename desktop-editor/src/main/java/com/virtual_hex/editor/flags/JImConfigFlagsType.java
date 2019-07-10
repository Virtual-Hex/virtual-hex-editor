package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImConfigFlags;

public enum JImConfigFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImConfigFlags#NavEnableKeyboard */
		NavEnableKeyboard(JImConfigFlags.NavEnableKeyboard),
		/** @see JImConfigFlags#NavEnableGamepad */
		NavEnableGamepad(JImConfigFlags.NavEnableGamepad),
		/** @see JImConfigFlags#NavEnableSetMousePos */
		NavEnableSetMousePos(JImConfigFlags.NavEnableSetMousePos),
		/** @see JImConfigFlags#NavNoCaptureKeyboard */
		NavNoCaptureKeyboard(JImConfigFlags.NavNoCaptureKeyboard),
		/** @see JImConfigFlags#NoMouse */
		NoMouse(JImConfigFlags.NoMouse),
		/** @see JImConfigFlags#NoMouseCursorChange */
		NoMouseCursorChange(JImConfigFlags.NoMouseCursorChange),
		/** @see JImConfigFlags#IsSRGB */
		IsSRGB(JImConfigFlags.IsSRGB),
		/** @see JImConfigFlags#IsTouchScreen */
		IsTouchScreen(JImConfigFlags.IsTouchScreen);

		public final int flag;

		JImConfigFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

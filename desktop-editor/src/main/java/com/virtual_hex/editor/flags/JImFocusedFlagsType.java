package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImFocusedFlags;

public enum JImFocusedFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImFocusedFlags#Default */
		Default(JImFocusedFlags.Default),
		/** @see JImFocusedFlags#ChildWindows */
		ChildWindows(JImFocusedFlags.ChildWindows),
		/** @see JImFocusedFlags#RootWindow */
		RootWindow(JImFocusedFlags.RootWindow),
		/** @see JImFocusedFlags#AnyWindow */
		AnyWindow(JImFocusedFlags.AnyWindow),
		/** @see JImFocusedFlags#RootAndChildWindows */
		RootAndChildWindows(JImFocusedFlags.RootAndChildWindows);
		
		public final int flag;

		JImFocusedFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

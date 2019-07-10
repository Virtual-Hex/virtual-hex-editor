package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImHoveredFlags;
public enum JImHoveredFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImHoveredFlags#Default */
		Default(JImHoveredFlags.Default),
		/** @see JImHoveredFlags#ChildWindows */
		ChildWindows(JImHoveredFlags.ChildWindows),
		/** @see JImHoveredFlags#RootWindow */
		RootWindow(JImHoveredFlags.RootWindow),
		/** @see JImHoveredFlags#AnyWindow */
		AnyWindow(JImHoveredFlags.AnyWindow),
		/** @see JImHoveredFlags#AllowWhenBlockedByPopup */
		AllowWhenBlockedByPopup(JImHoveredFlags.AllowWhenBlockedByPopup),
		/** @see JImHoveredFlags#AllowWhenBlockedByActiveItem */
		AllowWhenBlockedByActiveItem(JImHoveredFlags.AllowWhenBlockedByActiveItem),
		/** @see JImHoveredFlags#AllowWhenOverlapped */
		AllowWhenOverlapped(JImHoveredFlags.AllowWhenOverlapped),
		/** @see JImHoveredFlags#AllowWhenDisabled */
		AllowWhenDisabled(JImHoveredFlags.AllowWhenDisabled),
		/** @see JImHoveredFlags#RectOnly */
		RectOnly(JImHoveredFlags.RectOnly),
		/** @see JImHoveredFlags#RootAndChildWindows */
		RootAndChildWindows(JImHoveredFlags.RootAndChildWindows);

		public final int flag;

		JImHoveredFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImWindowFlags;

public enum JImWindowFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImWindowFlags.Nothing),
		Nothing(JImWindowFlags.Nothing),
		/** @see JImWindowFlags#NoTitleBar */
		NoTitleBar(JImWindowFlags.NoTitleBar),
		/** @see JImWindowFlags#NoResize */
		NoResize(JImWindowFlags.NoResize),
		/** @see JImWindowFlags#NoMove */
		NoMove(JImWindowFlags.NoMove),
		/** @see JImWindowFlags#NoScrollbar */
		NoScrollbar(JImWindowFlags.NoScrollbar),
		/** @see JImWindowFlags#NoScrollWithMouse */
		NoScrollWithMouse(JImWindowFlags.NoScrollWithMouse),
		/** @see JImWindowFlags#NoCollapse */
		NoCollapse(JImWindowFlags.NoCollapse),
		/** @see JImWindowFlags#AlwaysAutoResize */
		AlwaysAutoResize(JImWindowFlags.AlwaysAutoResize),
		/** @see JImWindowFlags#NoSavedSettings */
		NoSavedSettings(JImWindowFlags.NoSavedSettings),
		/** @see JImWindowFlags#NoInputs */
		NoInputs(JImWindowFlags.NoInputs),
		/** @see JImWindowFlags#MenuBar */
		MenuBar(JImWindowFlags.MenuBar),
		/** @see JImWindowFlags#HorizontalScrollbar */
		HorizontalScrollbar(JImWindowFlags.HorizontalScrollbar),
		/** @see JImWindowFlags#NoFocusOnAppearing */
		NoFocusOnAppearing(JImWindowFlags.NoFocusOnAppearing),
		/** @see JImWindowFlags#NoBringToFrontOnFocus */
		NoBringToFrontOnFocus(JImWindowFlags.NoBringToFrontOnFocus),
		/** @see JImWindowFlags#AlwaysVerticalScrollbar */
		AlwaysVerticalScrollbar(JImWindowFlags.AlwaysVerticalScrollbar),
		/** @see JImWindowFlags#AlwaysHorizontalScrollbar */
		AlwaysHorizontalScrollbar(JImWindowFlags.AlwaysHorizontalScrollbar),
		/** @see JImWindowFlags#AlwaysUseWindowPadding */
		AlwaysUseWindowPadding(JImWindowFlags.AlwaysUseWindowPadding),
		/** @see JImWindowFlags#ResizeFromAnySide */
		ResizeFromAnySide(JImWindowFlags.ResizeFromAnySide),
		/** @see JImWindowFlags#NoNavInputs */
		NoNavInputs(JImWindowFlags.NoNavInputs),
		/** @see JImWindowFlags#NoNavFocus */
		NoNavFocus(JImWindowFlags.NoNavFocus),
		/** @see JImWindowFlags#NoNav */
		NoNav(JImWindowFlags.NoNav),
		/** @see JImWindowFlags#NavFlattened */
		NavFlattened(JImWindowFlags.NavFlattened);

		public final int flag;

		JImWindowFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

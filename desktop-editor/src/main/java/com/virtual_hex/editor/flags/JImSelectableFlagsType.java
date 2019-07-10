package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImSelectableFlags;

public enum JImSelectableFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImSelectableFlags.Nothing),
		Nothing(JImSelectableFlags.Nothing),
		/** @see JImSelectableFlags#DontClosePopups */
		DontClosePopups(JImSelectableFlags.DontClosePopups),
		/** @see JImSelectableFlags#SpanAllColumns */
		SpanAllColumns(JImSelectableFlags.SpanAllColumns),
		/** @see JImSelectableFlags#AllowDoubleClick */
		AllowDoubleClick(JImSelectableFlags.AllowDoubleClick);

		public final int flag;

		JImSelectableFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

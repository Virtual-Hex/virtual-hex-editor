package com.virtual_hex.editor.flags;


import org.ice1000.jimgui.flag.JImDrawListFlags;

public enum JImDrawListFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImDrawListFlags.Nothing),
		Nothing(JImDrawListFlags.Nothing),
		/** @see JImDrawListFlags#AntiAliasedLines */
		AntiAliasedLines(JImDrawListFlags.AntiAliasedLines),
		/** @see JImDrawListFlags#AntiAliasedFill */
		AntiAliasedFill(JImDrawListFlags.AntiAliasedFill);

		public final int flag;

		JImDrawListFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

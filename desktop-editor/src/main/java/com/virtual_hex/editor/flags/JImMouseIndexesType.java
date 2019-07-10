package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImMouseIndexes;

public enum JImMouseIndexesType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(-1),
		/** @see JImMouseIndexes#Left */
		Left(JImMouseIndexes.Left),
		/** @see JImMouseIndexes#Right */
		Right(JImMouseIndexes.Right),
		/** @see JImMouseIndexes#Middle */
		Middle(JImMouseIndexes.Middle),
		/** @see JImMouseIndexes#ExtraA */
		ExtraA(JImMouseIndexes.ExtraA),
		/** @see JImMouseIndexes#ExtraB */
		ExtraB(JImMouseIndexes.ExtraB);

		public final int flag;

		JImMouseIndexesType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}
}

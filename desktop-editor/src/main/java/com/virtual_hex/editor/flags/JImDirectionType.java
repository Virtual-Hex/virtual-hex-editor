package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImDirection;

public enum JImDirectionType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImDirection.None),
		/** @see JImDirection#None */
		None(JImDirection.None),
		/** @see JImDirection#Left */
		Left(JImDirection.Left),
		/** @see JImDirection#Right */
		Right(JImDirection.Right),
		/** @see JImDirection#Up */
		Up(JImDirection.Up),
		/** @see JImDirection#Down */
		Down(JImDirection.Down);

		public final int flag;

		JImDirectionType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

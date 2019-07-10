package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImDrawCornerFlags;

public enum JImDrawCornerFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImDrawCornerFlags#TopLeft */
		TopLeft(JImDrawCornerFlags.TopLeft),
		/** @see JImDrawCornerFlags#TopRight */
		TopRight(JImDrawCornerFlags.TopRight),
		/** @see JImDrawCornerFlags#BotLeft */
		BotLeft(JImDrawCornerFlags.BotLeft),
		/** @see JImDrawCornerFlags#BotRight */
		BotRight(JImDrawCornerFlags.BotRight),
		/** @see JImDrawCornerFlags#Top */
		Top(JImDrawCornerFlags.Top),
		/** @see JImDrawCornerFlags#Bot */
		Bot(JImDrawCornerFlags.Bot),
		/** @see JImDrawCornerFlags#Left */
		Left(JImDrawCornerFlags.Left),
		/** @see JImDrawCornerFlags#Right */
		Right(JImDrawCornerFlags.Right),
		/** @see JImDrawCornerFlags#All */
		All(JImDrawCornerFlags.All);

		public final int flag;

		JImDrawCornerFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

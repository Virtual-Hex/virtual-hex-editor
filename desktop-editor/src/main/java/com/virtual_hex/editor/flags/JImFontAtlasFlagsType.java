package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImFontAtlasFlags;

public enum JImFontAtlasFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison. 0 Used to prevent ImGui errors from
		 * unexpected results
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImFontAtlasFlags#NoPowerOfTwoHeight */
		NoPowerOfTwoHeight(JImFontAtlasFlags.NoPowerOfTwoHeight),
		/** @see JImFontAtlasFlags#NoMouseCursors */
		NoMouseCursors(JImFontAtlasFlags.NoMouseCursors);

		public final int flag;

		JImFontAtlasFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

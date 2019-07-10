package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImBackendFlags;

public enum JImBackendFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),

		Nothing(0),
		/** @see JImBackendFlags#HasGamepad */
		HasGamepad(JImBackendFlags.HasGamepad),
		/** @see JImBackendFlags#HasMouseCursors */
		HasMouseCursors(JImBackendFlags.HasMouseCursors),
		/** @see JImBackendFlags#HasSetMousePos */
		HasSetMousePos(JImBackendFlags.HasSetMousePos);

		public final int flag;

	JImBackendFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

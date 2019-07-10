package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImComboFlags;

public enum JImComboFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImComboFlags.Nothing),
		Nothing(JImComboFlags.Nothing),
		/** @see JImComboFlags#PopupAlignLeft */
		PopupAlignLeft(JImComboFlags.PopupAlignLeft),
		/** @see JImComboFlags#HeightSmall */
		HeightSmall(JImComboFlags.HeightSmall),
		/** @see JImComboFlags#HeightRegular */
		HeightRegular(JImComboFlags.HeightRegular),
		/** @see JImComboFlags#HeightLarge */
		HeightLarge(JImComboFlags.HeightLarge),
		/** @see JImComboFlags#HeightLargest */
		HeightLargest(JImComboFlags.HeightLargest),
		/** @see JImComboFlags#NoArrowButton */
		NoArrowButton(JImComboFlags.NoArrowButton),
		/** @see JImComboFlags#NoPreview */
		NoPreview(JImComboFlags.NoPreview),

		HeightMask_(JImComboFlags.HeightSmall | JImComboFlags.HeightRegular | JImComboFlags.HeightLarge | JImComboFlags.HeightLargest);

		public final int flag;

		JImComboFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

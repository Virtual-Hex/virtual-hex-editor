package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImColorEditFlags;


public enum JImColorEditFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImColorEditFlags.Nothing),
		Nothing(JImColorEditFlags.Nothing),
		/** @see JImColorEditFlags#NoAlpha */
		NoAlpha(JImColorEditFlags.NoAlpha),
		/** @see JImColorEditFlags#NoPicker */
		NoPicker(JImColorEditFlags.NoPicker),
		/** @see JImColorEditFlags#NoOptions */
		NoOptions(JImColorEditFlags.NoOptions),
		/** @see JImColorEditFlags#NoSmallPreview */
		NoSmallPreview(JImColorEditFlags.NoSmallPreview),
		/** @see JImColorEditFlags#NoInputs */
		NoInputs(JImColorEditFlags.NoInputs),
		/** @see JImColorEditFlags#NoTooltip */
		NoTooltip(JImColorEditFlags.NoTooltip),
		/** @see JImColorEditFlags#NoLabel */
		NoLabel(JImColorEditFlags.NoLabel),
		/** @see JImColorEditFlags#NoSidePreview */
		NoSidePreview(JImColorEditFlags.NoSidePreview),
		/** @see JImColorEditFlags#NoDragDrop */
		NoDragDrop(JImColorEditFlags.NoDragDrop),

		// user options

		/** @see JImColorEditFlags#AlphaBar */
		AlphaBar(JImColorEditFlags.AlphaBar),
		/** @see JImColorEditFlags#AlphaPreview */
		AlphaPreview(JImColorEditFlags.AlphaPreview),
		/** @see JImColorEditFlags#AlphaPreviewHalf */
		AlphaPreviewHalf(JImColorEditFlags.AlphaPreviewHalf),
		/** @see JImColorEditFlags#HDR */
		HDR(JImColorEditFlags.HDR),
		/** @see JImColorEditFlags#RGB */
		RGB(JImColorEditFlags.RGB),
		/** @see JImColorEditFlags#HSV */
		HSV(JImColorEditFlags.HSV),
		/** @see JImColorEditFlags#HEX */
		HEX(JImColorEditFlags.HEX),
		/** @see JImColorEditFlags#Uint8 */
		Uint8(JImColorEditFlags.Uint8),
		/** @see JImColorEditFlags#Float */
		Float(JImColorEditFlags.Float),
		/** @see JImColorEditFlags#PickerHueBar */
		PickerHueBar(JImColorEditFlags.PickerHueBar),
		/** @see JImColorEditFlags#PickerHueWheel */
		PickerHueWheel(JImColorEditFlags.PickerHueWheel),


		// [Internal] Masks

		InputsMask(JImColorEditFlags.RGB | JImColorEditFlags.HSV | JImColorEditFlags.HEX ),
		DataTypeMask(JImColorEditFlags.Uint8 | JImColorEditFlags.Float),
		PickerMask (JImColorEditFlags.PickerHueWheel | JImColorEditFlags.PickerHueBar),
		OptionsDefault(JImColorEditFlags.Uint8 | JImColorEditFlags.RGB | JImColorEditFlags.PickerHueBar);

		public final int flag;

		JImColorEditFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

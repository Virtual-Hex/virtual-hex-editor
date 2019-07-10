package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImCondition;

public enum JImConditionType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImCondition#Always */
		Always(JImCondition.Always),
		/** @see JImCondition#Once */
		Once(JImCondition.Once),
		/** @see JImCondition#FirstUseEver */
		FirstUseEver(JImCondition.FirstUseEver),
		/** @see JImCondition#Appearing */
		Appearing(JImCondition.Appearing);

		public final int flag;

	JImConditionType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

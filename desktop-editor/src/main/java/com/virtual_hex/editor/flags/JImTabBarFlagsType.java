package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImTabBarFlags;


public enum JImTabBarFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImTabBarFlags.None),
		None(JImTabBarFlags.None),
		/** @see JImTabBarFlags#Reorderable */
		Reorderable(JImTabBarFlags.Reorderable),
		/** @see JImTabBarFlags#AutoSelectNewTabs */
		AutoSelectNewTabs(JImTabBarFlags.AutoSelectNewTabs),
		/** @see JImTabBarFlags#TabListPopupButton */
		TabListPopupButton(JImTabBarFlags.TabListPopupButton),
		/** @see JImTabBarFlags#NoCloseWithMiddleMouseButton */
		NoCloseWithMiddleMouseButton(JImTabBarFlags.NoCloseWithMiddleMouseButton),
		/** @see JImTabBarFlags#NoTabListScrollingButtons */
		NoTabListScrollingButtons(JImTabBarFlags.NoTabListScrollingButtons),
		/** @see JImTabBarFlags#NoTooltip */
		NoTooltip(JImTabBarFlags.NoTooltip),
		/** @see JImTabBarFlags#FittingPolicyResizeDown */
		FittingPolicyResizeDown(JImTabBarFlags.FittingPolicyResizeDown),
		/** @see JImTabBarFlags#FittingPolicyScroll */
		FittingPolicyScroll(JImTabBarFlags.FittingPolicyScroll),
		/** @see JImTabBarFlags#FittingPolicyMask */
		FittingPolicyMask(JImTabBarFlags.FittingPolicyMask),
		/** @see JImTabBarFlags#FittingPolicyDefault */
		FittingPolicyDefault(JImTabBarFlags.FittingPolicyDefault);

		public final int flag;

		JImTabBarFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

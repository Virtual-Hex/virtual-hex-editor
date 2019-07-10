package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImTreeNodeFlags;

public enum JImTreeNodeFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(0),
		Nothing(0),
		/** @see JImTreeNodeFlags#Selected */
		Selected(JImTreeNodeFlags.Selected),
		/** @see JImTreeNodeFlags#Framed */
		Framed(JImTreeNodeFlags.Framed),
		/** @see JImTreeNodeFlags#AllowItemOverlap */
		AllowItemOverlap(JImTreeNodeFlags.AllowItemOverlap),
		/** @see JImTreeNodeFlags#NoTreePushOnOpen */
		NoTreePushOnOpen(JImTreeNodeFlags.NoTreePushOnOpen),
		/** @see JImTreeNodeFlags#NoAutoOpenOnLog */
		NoAutoOpenOnLog(JImTreeNodeFlags.NoAutoOpenOnLog),
		/** @see JImTreeNodeFlags#DefaultOpen */
		DefaultOpen(JImTreeNodeFlags.DefaultOpen),
		/** @see JImTreeNodeFlags#OpenOnDoubleClick */
		OpenOnDoubleClick(JImTreeNodeFlags.OpenOnDoubleClick),
		/** @see JImTreeNodeFlags#OpenOnArrow */
		OpenOnArrow(JImTreeNodeFlags.OpenOnArrow),
		/** @see JImTreeNodeFlags#Leaf */
		Leaf(JImTreeNodeFlags.Leaf),
		/** @see JImTreeNodeFlags#Bullet */
		Bullet(JImTreeNodeFlags.Bullet),
		/** @see JImTreeNodeFlags#FramePadding */
		FramePadding(JImTreeNodeFlags.FramePadding),
		/** @see JImTreeNodeFlags#NavLeftJumpsBackHere */
		NavLeftJumpsBackHere(JImTreeNodeFlags.NavLeftJumpsBackHere),
		/** @see JImTreeNodeFlags#CollapsingHeader */
		CollapsingHeader(JImTreeNodeFlags.CollapsingHeader);

		public final int flag;

		JImTreeNodeFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

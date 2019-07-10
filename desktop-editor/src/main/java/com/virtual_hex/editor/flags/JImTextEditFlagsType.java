package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImTextEditFlags;

public enum JImTextEditFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImTextEditFlags.Nothing),
		Nothing(JImTextEditFlags.Nothing),
		/** @see JImTextEditFlags#CharsDecimal */
		CharsDecimal(JImTextEditFlags.CharsDecimal),
		/** @see JImTextEditFlags#CharsHexadecimal */
		CharsHexadecimal(JImTextEditFlags.CharsHexadecimal),
		/** @see JImTextEditFlags#CharsUppercase */
		CharsUppercase(JImTextEditFlags.CharsUppercase),
		/** @see JImTextEditFlags#CharsNoBlank */
		CharsNoBlank(JImTextEditFlags.CharsNoBlank),
		/** @see JImTextEditFlags#AutoSelectAll */
		AutoSelectAll(JImTextEditFlags.AutoSelectAll),
		/** @see JImTextEditFlags#EnterReturnsTrue */
		EnterReturnsTrue(JImTextEditFlags.EnterReturnsTrue),
		/** @see JImTextEditFlags#CallbackCompletion */
		CallbackCompletion(JImTextEditFlags.CallbackCompletion),
		/** @see JImTextEditFlags#CallbackHistory */
		CallbackHistory(JImTextEditFlags.CallbackHistory),
		/** @see JImTextEditFlags#CallbackAlways */
		CallbackAlways(JImTextEditFlags.CallbackAlways),
		/** @see JImTextEditFlags#CallbackCharFilter */
		CallbackCharFilter(JImTextEditFlags.CallbackCharFilter),
		/** @see JImTextEditFlags#AllowTabInput */
		AllowTabInput(JImTextEditFlags.AllowTabInput),
		/** @see JImTextEditFlags#CtrlEnterForNewLine */
		CtrlEnterForNewLine(JImTextEditFlags.CtrlEnterForNewLine),
		/** @see JImTextEditFlags#NoHorizontalScroll */
		NoHorizontalScroll(JImTextEditFlags.NoHorizontalScroll),
		/** @see JImTextEditFlags#AlwaysInsertMode */
		AlwaysInsertMode(JImTextEditFlags.AlwaysInsertMode),
		/** @see JImTextEditFlags#ReadOnly */
		ReadOnly(JImTextEditFlags.ReadOnly),
		/** @see JImTextEditFlags#Password */
		Password(JImTextEditFlags.Password),
		/** @see JImTextEditFlags#NoUndoRedo */
		NoUndoRedo(JImTextEditFlags.NoUndoRedo),
		/** @see JImTextEditFlags#CharsScientific */
		CharsScientific(JImTextEditFlags.CharsScientific);

		public final int flag;

		JImTextEditFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

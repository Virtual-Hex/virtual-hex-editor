package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImInputTextFlags;

public enum JImInputTextFlagsType implements Flag {
		/**
		 * Used for reverse lookup results and enum comparison.
		 * Return the Nothing or Default flag to prevent errors.
		 */
		NoSuchFlag(JImInputTextFlags.Nothing),
		Nothing(JImInputTextFlags.Nothing),
		/** @see JImInputTextFlags#CharsDecimal */
		CharsDecimal(JImInputTextFlags.CharsDecimal),
		/** @see JImInputTextFlags#CharsHexadecimal */
		CharsHexadecimal(JImInputTextFlags.CharsHexadecimal),
		/** @see JImInputTextFlags#CharsUppercase */
		CharsUppercase(JImInputTextFlags.CharsUppercase),
		/** @see JImInputTextFlags#CharsNoBlank */
		CharsNoBlank(JImInputTextFlags.CharsNoBlank),
		/** @see JImInputTextFlags#AutoSelectAll */
		AutoSelectAll(JImInputTextFlags.AutoSelectAll),
		/** @see JImInputTextFlags#EnterReturnsTrue */
		EnterReturnsTrue(JImInputTextFlags.EnterReturnsTrue),
		/** @see JImInputTextFlags#CallbackCompletion */
		CallbackCompletion(JImInputTextFlags.CallbackCompletion),
		/** @see JImInputTextFlags#CallbackHistory */
		CallbackHistory(JImInputTextFlags.CallbackHistory),
		/** @see JImInputTextFlags#CallbackAlways */
		CallbackAlways(JImInputTextFlags.CallbackAlways),
		/** @see JImInputTextFlags#CallbackCharFilter */
		CallbackCharFilter(JImInputTextFlags.CallbackCharFilter),
		/** @see JImInputTextFlags#AllowTabInput */
		AllowTabInput(JImInputTextFlags.AllowTabInput),
		/** @see JImInputTextFlags#CtrlEnterForNewLine */
		CtrlEnterForNewLine(JImInputTextFlags.CtrlEnterForNewLine),
		/** @see JImInputTextFlags#NoHorizontalScroll */
		NoHorizontalScroll(JImInputTextFlags.NoHorizontalScroll),
		/** @see JImInputTextFlags#AlwaysInsertMode */
		AlwaysInsertMode(JImInputTextFlags.AlwaysInsertMode),
		/** @see JImInputTextFlags#ReadOnly */
		ReadOnly(JImInputTextFlags.ReadOnly),
		/** @see JImInputTextFlags#Password */
		Password(JImInputTextFlags.Password),
		/** @see JImInputTextFlags#NoUndoRedo */
		NoUndoRedo(JImInputTextFlags.NoUndoRedo),
		/** @see JImInputTextFlags#CharsScientific */
		CharsScientific(JImInputTextFlags.CharsScientific);

		public final int flag;

		JImInputTextFlagsType(int flag) {
			this.flag = flag;
		}

		@Override
		public int get() {
			return flag;
		}

}

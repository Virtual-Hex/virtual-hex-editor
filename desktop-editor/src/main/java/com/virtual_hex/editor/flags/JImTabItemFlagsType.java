package com.virtual_hex.editor.flags;

import org.ice1000.jimgui.flag.JImTabItemFlags;

public enum JImTabItemFlagsType implements Flag {
        /**
         * Used for reverse lookup results and enum comparison.
         * Return the Nothing or Default flag to prevent errors.
         */
        NoSuchFlag(JImTabItemFlags.None),
        None(JImTabItemFlags.None),
        /** @see JImTabItemFlags#UnsavedDocument */
        UnsavedDocument(JImTabItemFlags.UnsavedDocument),
        /** @see JImTabItemFlags#SetSelected */
        SetSelected(JImTabItemFlags.SetSelected),
        /** @see JImTabItemFlags#NoCloseWithMiddleMouseButton */
        NoCloseWithMiddleMouseButton(JImTabItemFlags.NoCloseWithMiddleMouseButton),
        /** @see JImTabItemFlags#NoPushId */
        NoPushId(JImTabItemFlags.NoPushId);

        public final int flag;

        JImTabItemFlagsType(int flag) {
            this.flag = flag;
        }

        @Override
        public int get() {
            return flag;
        }

}

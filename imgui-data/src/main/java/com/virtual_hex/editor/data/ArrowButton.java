package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@NoArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ArrowButton<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public Direction direction;

    public enum Direction{
        NONE(-1),
        LEFT(0),
        RIGHT(1),
        UP(2),
        DOWN(3);

        public final int index;

        Direction(int index) {
            this.index = index;
        }
    }
}

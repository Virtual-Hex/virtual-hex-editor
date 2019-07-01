package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ShowDemoWindow extends AbstractUIComponent {

    @NonNull
    public boolean open = false;
}

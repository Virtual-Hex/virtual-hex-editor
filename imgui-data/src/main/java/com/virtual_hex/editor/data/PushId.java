package com.virtual_hex.editor.data;

import lombok.*;

@ToString

@NoArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class PushId extends AbstractUIComponent {

    @NonNull
    public String id;
}

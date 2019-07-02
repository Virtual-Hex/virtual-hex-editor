package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class LabeledComponents {

    @NonNull
    public String label;
    @NonNull
    public UIComponent[] uiComponents;
}

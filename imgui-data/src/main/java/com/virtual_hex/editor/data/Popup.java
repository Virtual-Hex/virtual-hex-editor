package com.virtual_hex.editor.data;

import lombok.*;

/**
 * // call to mark popup as open (don't call every frame!). popups are closed when user click outside, or if CloseCurrentPopup() is called within a BeginPopup()/EndPopup() block. By default, Selectable()/MenuItemSelectable() are calling CloseCurrentPopup(). Popup identifiers are relative to the current ID-stack (so Popup and BeginPopup needs to be at the same level).
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Popup<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public boolean enabled = true;
    @NonNull
    public int flags = 0;
    @NonNull
    public UIComponents uiComponents;
}

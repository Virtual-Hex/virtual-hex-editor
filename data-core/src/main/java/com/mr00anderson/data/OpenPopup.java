package com.mr00anderson.data;

/**
 *  // call to mark popup as open (don't call every frame!). popups are closed when user click outside, or if CloseCurrentPopup() is called within a BeginPopup()/EndPopup() block. By default, Selectable()/MenuItem() are calling CloseCurrentPopup(). Popup identifiers are relative to the current ID-stack (so OpenPopup and BeginPopup needs to be at the same level).
 */
public class OpenPopup {

    // THIS CLASS needs to manage a state so it does not recall open popup, maybe leeave this to the user

//    public String stringId;
//    public int flags;
//    public Drawables drawables;
//    public State state = State.REQUEST_CLOSE;
//
//    public OpenPopup() {
//    }
//
//    public OpenPopup(String stringId) {
//        this.stringId = stringId;
//    }
//
//    public OpenPopup(String stringId, int flags) {
//        this.stringId = stringId;
//        this.flags = flags;
//    }
//
//    @Override
//    public void draw(JImGui imGui) {
//        switch (state) {
//            case REQUEST_OPEN:{
//                imGui.beginPopup(stringId, flags);
//                break;
//            }
//            case OPEN:
//                // Do nothing here but draw contents
//                drawables.draw(imGui);
//                break;
//            case CLOSED:
//
//                break;
//            case REQUEST_CLOSE:
//                break;
//            default:
//        }
//
//    }
//
//    public enum State {
//        OPEN,
//        CLOSED,
//        REQUEST_OPEN
//        REQUEST_CLOSE
//    }
}

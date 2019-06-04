package com.mr00anderson.jawe;

import com.artemis.World;
import com.mr00anderson.data.ActivationHandler;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.jetbrains.annotations.NotNull;

public class JaweJImGui extends JImGui {

//    /**
//     *  separator, generally horizontal. inside a menu bar or in horizontal layout mode, this becomes a vertical separator.
//     */
//    public static final Drawable SEPARATOR = new Separator();
//
//    /**
//     * next column, defaults to current row or next row if the current row is finished
//     */
//    public static final Drawable NEXT_COLUMN = new NextColumn();
//
//    /**
//     * undo a SameLine() or force a new line when in an horizontal-layout context.
//     */
//    public static final Drawable NEW_LINE = new NewLine();
//
//
//    public static final Drawable SAME_LINE = new SameLine();
//
//    public static final Drawable TREE_POP = new TreePop();
//
//    /**
//     * add vertical spacing
//     */
//    public static final Drawable JAWE_SPACING = new Spacing();


    public static final ActivationHandler<?> EMPTY_ACTIVATION_HANDLER = (imGuiDrawable, parentDrawable) -> {};

    protected DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    public static final JaweStaticDeallocateManager STATIC_NATIVE_MANAGEMENT = new JaweStaticDeallocateManager();

    public World world;

    public JaweJImGui(World world) {
        this.world = world;
    }

    public JaweJImGui(int width, int height, @NotNull String title, World world) {
        super(width, height, title);
        this.world = world;
    }

    public JaweJImGui(int width, int height, World world) {
        super(width, height);
        this.world = world;
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title, World world) {
        super(width, height, fontAtlas, title);
        this.world = world;
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title, long anotherWindow, World world) {
        super(width, height, fontAtlas, title, anotherWindow);
        this.world = world;
    }

    public JaweJImGui() {
    }

    public JaweJImGui(int width, int height, @NotNull String title) {
        super(width, height, title);
    }

    public JaweJImGui(int width, int height) {
        super(width, height);
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title) {
        super(width, height, fontAtlas, title);
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title, long anotherWindow) {
        super(width, height, fontAtlas, title, anotherWindow);
    }

    public static void showHelpMarker(JImGui imGui, String description) {
        imGui.textDisabled("(?)");
        if (imGui.isItemHovered()) {
            imGui.beginTooltip();
            imGui.pushTextWrapPos(imGui.getFontSize() * 35.0f);
            imGui.text(description);
            imGui.popTextWrapPos();
            imGui.endTooltip();
        }
    }
}

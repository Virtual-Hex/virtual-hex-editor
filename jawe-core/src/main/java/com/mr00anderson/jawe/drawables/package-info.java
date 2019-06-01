/**
 * Drawables are JImGui drawing code and is serializable without Artemis OBD, though the methods take a World,
 * this may not be needed, TODO TBD
 *
 * Artemis OBD does serialize the layers found in worldsHeader.
 *
 * See https://github.com/ocornut/imgui/blob/master/imgui.h
 *
 *
 * TODO Will need to look at subclassing where we want to have interchangeable but restricted parts,
 * I.E ColorText and Text would just be a parent of Text which is a parent of Labeled... Or something like that
 *
 */
package com.mr00anderson.jawe.drawables;


// TODO below
// Buttons
//IMGUI_API bool          ArrowButton(const char* str_id, ImGuiDir dir);                  // square button with an arrow shape
//IMGUI_API void          Image(ImTextureID user_texture_id, const ImVec2& size, const ImVec2& uv0 = ImVec2(0,0), const ImVec2& uv1 = ImVec2(1,1), const ImVec4& tint_col = ImVec4(1,1,1,1), const ImVec4& border_col = ImVec4(0,0,0,0));
//IMGUI_API bool          ImageButton(ImTextureID user_texture_id, const ImVec2& size, const ImVec2& uv0 = ImVec2(0,0),  const ImVec2& uv1 = ImVec2(1,1), int frame_padding = -1, const ImVec4& bg_col = ImVec4(0,0,0,0), const ImVec4& tint_col = ImVec4(1,1,1,1));    // <0 frame_padding uses default frame padding settings. 0 for no padding
//IMGUI_API bool          Checkbox(const char* label, bool* v);
//IMGUI_API bool          CheckboxFlags(const char* label, unsigned int* flags, unsigned int flags_value);
//IMGUI_API bool          RadioButton(const char* label, bool active);                    // use with e.g. if (RadioButton("one", my_value==1)) { my_value = 1; }
//IMGUI_API bool          RadioButton(const char* label, int* v, int v_button);           // shortcut to handle the above pattern when value is an integer
//IMGUI_API void          ProgressBar(float fraction, const ImVec2& size_arg = ImVec2(-1,0), const char* overlay = NULL);
//IMGUI_API void          Bullet();




//            IMGUI_API int           GetColumnIndex();                                                   // get current column index
//            IMGUI_API float         GetColumnWidth(int column_index = -1);                              // get column width (in pixels). pass -1 to use current column
//            IMGUI_API void          SetColumnWidth(int column_index, float width);                      // set column width (in pixels). pass -1 to use current column
//            IMGUI_API float         GetColumnOffset(int column_index = -1);                             // get position of column line (in pixels, from the left side of the contents region). pass -1 to use current column, otherwise 0..GetColumnsCount() inclusive. column 0 is typically 0.0f
//            IMGUI_API void          SetColumnOffset(int column_index, float offset_x);                  // set position of column line (in pixels, from the left side of the contents region). pass -1 to use current column
//            IMGUI_API int           GetColumnsCount();





// Cursor / Layout
// - By "cursor" we mean the current output position.
// - The typical widget behavior is to output themselves at the current cursor position, then move the cursor one line down.

//IMGUI_API void          Indent(float indent_w = 0.0f);                                  // move content position toward the right, by style.IndentSpacing or indent_w if != 0
//IMGUI_API void          Unindent(float indent_w = 0.0f);                                // move content position back to the left, by style.IndentSpacing or indent_w if != 0
//IMGUI_API void          BeginGroup();                                                   // lock horizontal starting position
//IMGUI_API void          EndGroup();                                                     // unlock horizontal starting position + capture the whole group bounding box into one "item" (so you can use IsItemHovered() or layout primitives such as SameLine() on whole group, etc.)
//IMGUI_API ImVec2        GetCursorPos();                                                 // cursor position in window coordinates (relative to window position)
//IMGUI_API float         GetCursorPosX();                                                //   (some functions are using window-relative coordinates, such as: GetCursorPos, GetCursorStartPos, GetContentRegionMax, GetWindowContentRegion* etc.
//IMGUI_API float         GetCursorPosY();                                                //    other functions such as GetCursorScreenPos or everything in ImDrawList::
//IMGUI_API void          SetCursorPos(const ImVec2& local_pos);                          //    are using the main, absolute coordinate system.
//IMGUI_API void          SetCursorPosX(float local_x);                                   //    GetWindowPos() + GetCursorPos() == GetCursorScreenPos() etc.)
//IMGUI_API void          SetCursorPosY(float local_y);                                   //
//IMGUI_API ImVec2        GetCursorStartPos();                                            // initial cursor position in window coordinates
//IMGUI_API ImVec2        GetCursorScreenPos();                                           // cursor position in absolute screen coordinates [0..io.DisplaySize] (useful to work with ImDrawList API)
//IMGUI_API void          SetCursorScreenPos(const ImVec2& pos);                          // cursor position in absolute screen coordinates [0..io.DisplaySize]
//IMGUI_API void          AlignTextToFramePadding();                                      // vertically align upcoming text baseline to FramePadding.y so that it will align properly to regularly framed items (call if you have text on a line before a framed item)
//IMGUI_API float         GetTextLineHeight();                                            // ~ FontSize
//IMGUI_API float         GetTextLineHeightWithSpacing();                                 // ~ FontSize + style.ItemSpacing.y (distance in pixels between 2 consecutive lines of text)
//IMGUI_API float         GetFrameHeight();                                               // ~ FontSize + style.FramePadding.y * 2
//IMGUI_API float         GetFrameHeightWithSpacing();                                    // ~ FontSize + style.FramePadding.y * 2 + style.ItemSpacing.y (distance in pixels between 2 consecutive lines of framed widgets)



//// Widgets: Text
//    IMGUI_API void          TextUnformatted(const char* text, const char* text_end = NULL);                // raw text without formatting. Roughly equivalent to Text("%s", text) but: A) doesn't require null terminated path if 'text_end' is specified, B) it's faster, no memory copy is done, no buffer size limits, recommended for long chunks of text.
//            IMGUI_API void          Text(const char* fmt, ...)                                      IM_FMTARGS(1); // simple formatted text
//            IMGUI_API void          TextV(const char* fmt, va_list args)                            IM_FMTLIST(1);
//            IMGUI_API void          TextColoredV(const ImVec4& col, const char* fmt, va_list args)  IM_FMTLIST(2);
//            IMGUI_API void          TextDisabled(const char* fmt, ...)                              IM_FMTARGS(1); // shortcut for PushStyleColor(ImGuiCol_Text, style.Colors[ImGuiCol_TextDisabled]); Text(fmt, ...); PopStyleColor();
//            IMGUI_API void          TextDisabledV(const char* fmt, va_list args)                    IM_FMTLIST(1);
//            IMGUI_API void          TextWrapped(const char* fmt, ...)                               IM_FMTARGS(1); // shortcut for PushTextWrapPos(0.0f); Text(fmt, ...); PopTextWrapPos();. Note that this won't work on an auto-resizing window if there's no other widgets to extend the window width, yoy may need to set a size using SetNextWindowSize().
//            IMGUI_API void          TextWrappedV(const char* fmt, va_list args)                     IM_FMTLIST(1);
//            IMGUI_API void          LabelText(const char* label, const char* fmt, ...)              IM_FMTARGS(2); // display text+label aligned the same way as value+label widgets
//            IMGUI_API void          LabelTextV(const char* label, const char* fmt, va_list args)    IM_FMTLIST(2);
//            IMGUI_API void          BulletText(const char* fmt, ...)                                IM_FMTARGS(1); // shortcut for Bullet()+Text()
//            IMGUI_API void          BulletTextV(const char* fmt, va_list args)                      IM_FMTLIST(1);



// if (ImGui::Button("Stacked modals.."))
//         ImGui::OpenPopup("Stacked 1");
//         if (ImGui::BeginPopupModal("Stacked 1", NULL, ImGuiWindowFlags_MenuBar))
//         {
//         if (ImGui::BeginMenuBar())
//         {
//         if (ImGui::BeginMenu("File"))
//         {
//         if (ImGui::MenuItem("Dummy menu item")) {}
//         ImGui::EndMenu();
//         }
//         ImGui::EndMenuBar();
//         }
//         ImGui::Text("Hello from Stacked The First\nUsing style.Colors[ImGuiCol_ModalWindowDimBg] behind it.");
//
//// Testing behavior of widgets stacking their own regular popups over the modal.
//static int item = 1;
//static float color[4] = { 0.4f,0.7f,0.0f,0.5f };
//        ImGui::Combo("Combo", &item, "aaaa\0bbbb\0cccc\0dddd\0eeee\0\0");
//        ImGui::ColorEdit4("color", color);
//
//        if (ImGui::Button("Add another modal.."))
//        ImGui::OpenPopup("Stacked 2");
//
//        // Also demonstrate passing a bool* to BeginPopupModal(), this will create a regular close button which will close the popup.
//        // Note that the visibility state of popups is owned by imgui, so the input value of the bool actually doesn't matter here.
//        bool dummy_open = true;
//        if (ImGui::BeginPopupModal("Stacked 2", &dummy_open))
//        {
//        ImGui::Text("Hello from Stacked The Second!");
//        if (ImGui::Button("Close"))
//        ImGui::CloseCurrentPopup();
//        ImGui::EndPopup();
//        }
//
//        if (ImGui::Button("Close"))
//        ImGui::CloseCurrentPopup();
//        ImGui::EndPopup();
//        }

// Tooltips
//    IMGUI_API void          BeginTooltip();                                                     // begin/append a tooltip window. to create full-featured tooltip (with any kind of items).
//            IMGUI_API void          EndTooltip();
//            IMGUI_API void          SetTooltip(const char* fmt, ...) IM_FMTARGS(1);                     // set a text-only tooltip, typically use with ImGui::IsItemHovered(). override any previous call to SetTooltip().
//            IMGUI_API void          SetTooltipV(const char* fmt, va_list args) IM_FMTLIST(1);
//
//            // Popups, Modals
//            // The properties of popups windows are:
//            // - They block normal mouse hovering detection outside them. (*)
//            // - Unless modal, they can be closed by clicking anywhere outside them, or by pressing ESCAPE.
//            // - Their visibility state (~bool) is held internally by imgui instead of being held by the programmer as we are used to with regular Begin() calls.
//            //   User can manipulate the visibility state by calling OpenPopup().
//            // (*) One can use IsItemHovered(ImGuiHoveredFlags_AllowWhenBlockedByPopup) to bypass it and detect hovering even when normally blocked by a popup.
//            // Those three properties are connected. The library needs to hold their visibility state because it can close popups at any time.
//            IMGUI_API void          OpenPopup(const char* str_id);
//            IMGUI_API bool          BeginPopup(const char* str_id, ImGuiWindowFlags flags = 0);                                             // return true if the popup is open, and you can start outputting to it. only call EndPopup() if BeginPopup() returns true!
//            IMGUI_API bool          BeginPopupContextItem(const char* str_id = NULL, int mouse_button = 1);                                 // helper to open and begin popup when clicked on last item. if you can pass a NULL str_id only if the previous item had an id. If you want to use that on a non-interactive item such as Text() you need to pass in an explicit ID here. read comments in .cpp!
//            IMGUI_API bool          BeginPopupContextWindow(const char* str_id = NULL, int mouse_button = 1, bool also_over_items = true);  // helper to open and begin popup when clicked on current window.
//            IMGUI_API bool          BeginPopupContextVoid(const char* str_id = NULL, int mouse_button = 1);                                 // helper to open and begin popup when clicked in void (where there are no imgui windows).
//            IMGUI_API bool          BeginPopupModal(const char* name, bool* p_open = NULL, ImGuiWindowFlags flags = 0);                     // modal dialog (regular window with title bar, block interactions behind the modal window, can't close the modal window by clicking outside)
//            IMGUI_API void          EndPopup();                                                                                             // only call EndPopup() if BeginPopupXXX() returns true!
//            IMGUI_API bool          OpenPopupOnItemClick(const char* str_id = NULL, int mouse_button = 1);                                  // helper to open popup when clicked on last item (note: actually triggers on the mouse _released_ event to be consistent with popup behaviors). return true when just opened.
//            IMGUI_API bool          IsPopupOpen(const char* str_id);                                    // return true if the popup is open at the current begin-ed level of the popup stack.
//            IMGUI_API void          CloseCurrentPopup();




//// Widgets: Trees
//// - TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
//    IMGUI_API bool          TreeNode(const char* label);
//            IMGUI_API bool          TreeNode(const char* str_id, const char* fmt, ...) IM_FMTARGS(2);   // helper variation to easily decorelate the id from the displayed string. Read the FAQ about why and how to use ID. to align arbitrary text at the same level as a TreeNode() you can use Bullet().
//            IMGUI_API bool          TreeNode(const void* ptr_id, const char* fmt, ...) IM_FMTARGS(2);   // "
//            IMGUI_API bool          TreeNodeV(const char* str_id, const char* fmt, va_list args) IM_FMTLIST(2);
//            IMGUI_API bool          TreeNodeV(const void* ptr_id, const char* fmt, va_list args) IM_FMTLIST(2);
//            IMGUI_API bool          TreeNodeEx(const char* label, ImGuiTreeNodeFlags flags = 0);
//            IMGUI_API bool          TreeNodeEx(const char* str_id, ImGuiTreeNodeFlags flags, const char* fmt, ...) IM_FMTARGS(3);
//            IMGUI_API bool          TreeNodeEx(const void* ptr_id, ImGuiTreeNodeFlags flags, const char* fmt, ...) IM_FMTARGS(3);
//            IMGUI_API bool          TreeNodeExV(const char* str_id, ImGuiTreeNodeFlags flags, const char* fmt, va_list args) IM_FMTLIST(3);
//            IMGUI_API bool          TreeNodeExV(const void* ptr_id, ImGuiTreeNodeFlags flags, const char* fmt, va_list args) IM_FMTLIST(3);
//            IMGUI_API void          TreePush(const char* str_id);                                       // ~ Indent()+PushId(). Already called by TreeNode() when returning true, but you can call TreePush/TreePop yourself if desired.
//            IMGUI_API void          TreePush(const void* ptr_id = NULL);                                // "
//            IMGUI_API void          TreeAdvanceToLabelPos();                                            // advance cursor x position by GetTreeNodeToLabelSpacing()
//            IMGUI_API float         GetTreeNodeToLabelSpacing();                                        // horizontal distance preceding label when using TreeNode*() or Bullet() == (g.FontSize + style.FramePadding.x*2) for a regular unframed TreeNode
//            IMGUI_API void          SetNextItemOpen(bool is_open, ImGuiCond cond = 0);
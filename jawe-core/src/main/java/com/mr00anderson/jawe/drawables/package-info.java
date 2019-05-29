/**
 * Drawables are JImGui drawing code and is serializable without Artemis OBD, though the methods take a World,
 * this may not be needed, TODO TBD
 *
 * Artemis OBD does serialize the layers found in drawables.
 *
 */
package com.mr00anderson.jawe.drawables;


// TODO
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
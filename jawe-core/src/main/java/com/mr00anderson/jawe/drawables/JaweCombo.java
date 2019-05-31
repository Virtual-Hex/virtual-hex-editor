package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.components.JaweOrderedDrawables;
import org.ice1000.jimgui.JImGui;

/**
 *  Widgets: Combo Box
 *  - The new BeginCombo()/EndCombo() api allows you to manage your contents and selection state however you want it, by creating e.g. Selectable() items.
 */
public class JaweCombo implements JaweDrawable{

    public String label;
    public String previewValue = "";
    public int flags = 0;
    public JaweOrderedDrawables jaweSelectables;

    public JaweCombo() {
    }

    public JaweCombo(String label, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, int flags, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, String previewValue, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.previewValue = previewValue;
        this.jaweSelectables = jaweSelectables;
    }

    public JaweCombo(String label, String previewValue, int flags, JaweOrderedDrawables jaweSelectables) {
        this.label = label;
        this.previewValue = previewValue;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    @Override
    public void draw(JImGui imGui) {
        boolean beginCombo = imGui.beginCombo(label, previewValue, flags);
        if(beginCombo){

            // Replace with JawOrderDrawables to allow inserting
            for (int i = 0; i < jaweSelectables.length; i++) {
                JaweSelectable jaweSelectable = jaweSelectables[i];
                jaweSelectable.draw(imGui);
                System.out.println(jaweSelectable.selected);
                boolean accessValue = jaweSelectable.selected.accessValue();
                if(accessValue){
                    previewValue = jaweSelectable.label;
                    imGui.setItemDefaultFocus();
                }
            }

            imGui.endCombo();
        }
    }
}


// General BeginCombo() API, you have full control over your selection data and display type.
//// (your selection data could be an index, a pointer to the object, an id for the object, a flag stored in the object itself, etc.)
//        const char* items[] = { "AAAA", "BBBB", "CCCC", "DDDD", "EEEE", "FFFF", "GGGG", "HHHH", "IIII", "JJJJ", "KKKK", "LLLLLLL", "MMMM", "OOOOOOO" };
//static const char* item_current = items[0];            // Here our selection is a single pointer stored outside the object.
//        if (ImGui::BeginCombo("combo 1", item_current, flags)) // The second parameter is the label previewed before opening the combo.
//        {
//        for (int n = 0; n < IM_ARRAYSIZE(items); n++)
//        {
//        bool is_selected = (item_current == items[n]);
//        if (ImGui::Selectable(items[n], is_selected))
//        item_current = items[n];
//        if (is_selected)
//        ImGui::SetItemDefaultFocus();   // Set the initial focus when opening the combo (scrolling + for keyboard navigation support in the upcoming navigation branch)
//        }
//        ImGui::EndCombo();
//        }
//



// - The old Combo() api are helpers over BeginCombo()/EndCombo() which are kept available for convenience purpose.
//    IMGUI_API bool          BeginCombo(const char* label, const char* preview_value, ImGuiComboFlags flags = 0);
//    IMGUI_API void          EndCombo(); // only call EndCombo() if BeginCombo() returns true!

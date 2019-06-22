/**
 * uiDataArray are JImGui handling code and is serializable without Artemis OBD, though the methods take a World,
 * this may not be needed, TODO TBD
 * <p>
 * Artemis OBD does serialize the layers found in worldsHeader.
 * <p>
 * See https://github.com/ocornut/imgui/blob/master/imgui.h
 * <p>
 * <p>
 * TODO Will need to look at subclassing where we want to have interchangeable but restricted parts,
 * I.E ColorText and Text would just be a parent of Text which is a parent of Labeled... Or something like that
 * <p>
 * <p>
 * TODO Need to use IDs for some windows for hashcode and equals, for the cases we do not want to dril down
 * hashcodes but where we need to identify a series of object for caching purposes
 * <p>
 * <p>
 * This package represent the minimal to implement storage of JImGui state.
 * <p>
 * The classes must have a zero arg constructor. Those with labels must have labels in parameterized constructors.
 * <p>
 * <p>
 * <p>
 * Sliders: /
 * - CTRL+Click on any slider to turn them into an input box. Manually input values aren't clamped and can go off-bounds.
 * // - Adjust format string to decorate the value with a prefix, a suffix, or adapt the editing and display precision e.g. "%.3f" -> 1.234; "%5.2f secs" -> 01.23 secs; "Biscuit: %.0f" -> Biscuit: 1; etc.
 */
package com.virtual_hex.editor.data;

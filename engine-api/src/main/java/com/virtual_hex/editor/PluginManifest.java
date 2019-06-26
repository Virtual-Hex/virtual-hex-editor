package com.virtual_hex.editor;

import java.lang.annotation.*;

/**
 * For Plugin Developers
 *
 * This must be filled out by plugin developers in order to have their plugin loaded.
 *
 * <p>
 * Required
 * - Name
 * - Version
 * - Authors
 * - Organization
 * - Description
 *
 * <p>
 * See the fields for details description.
 *
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginManifest {

    /**
     *
     * @return String name of the plugin that must be unique
     */
    @Required
    String name();

    /**
     * Complex: @see com.virtual_hex.editor.ComplexVersion
     * {
     *   "type": "ComplexVersion",
     *   "major": 1,
     *   "minor": 0,
     *   "patch": 0,
     *   "state": "RELEASE",
     *   "timestamp": 0
     * }
     *
     * Simple: @see com.virtual_hex.editor.SimpleVersion
     * {
     *   "type": "SimpleVersion",
     *   "version": 1
     * }
     * @return String representation of
     */
    @Required
    String version();

    /**
     *
     * @return String representing the target. This target is checked by the plugin manager to load this plugin.
     */
    @Required
    String[] targets();

    /**
     *
     * @return String of the authors who played a part in developing this plugin
     */
    @Required
    String[] authors();

    /**
     *
     * @return String representing the organization who created this plugin
     */
    @Required
    String organization();

    /**
     *
     * @return String brief description of this plugin and its functionality
     */
    @Required
    String description();

    /**
     *
     * @return String uniformed resource locator containing the address for the organization who created this
     * plugin
     */
    String url();

    /**
     *
     * Will look for this jar in the common/libs
     * {
     *   "name": "name.jar",
     * }
     * 
     * Will look for this jar in the plugins/{plugin-name}/libs if not found will download it from 
     * "https://www.urlDownload.com/name.jar"
     * {
     *   "name": "name2.jar",
     *   "downloadUrl": "https://www.urlDownload.com"
     * }
     * 
     * Will look for this jar in the common/libs
     * {
     *   "name": "name.png",
     * }
     * 
     * Will look for this jar in the plugins/{plugin-name}/libs if not found will download it from
     * "https://www.urlDownload.com/name2.png"
     * {
     *   "name": "name2.png",
     *   "downloadUrl": "https://www.urlDownload.com"
     * }
     *
     * @return String json representing dependencies @see com.freeuniversegames.meta.JsonString
     */
    String[] dependencies() default "";

    boolean canBeStopped() default true;

    boolean canBeUnloaded() default true;

    String[] permissions() default {};

    /**
     *  Directories should end with "/", files are simply the path. {name}/ will extract the entire directory contents.
     *
     * @return
     */
    String[] jarExtracts() default {};

    String help() default "Awaiting future implementation";
}

package com.virtual_hex.editor;

public enum PluginLoadCode {
    JAR_FILE_IO_EXCEPTION("Jar File IO Exception"),
    JAR_MANIFEST_IO_EXCEPTION("Jar Manifest IO Exception"),
    NO_JAR_MANIFEST("Missing jar manifest"),
    NO_JAR_MANIFEST_VALUE("Missing jar manifest value"),
    CANNOT_FIND_CLASS_JAR_ENTRY("Cannot find class Jar Entry"),
    MALFORMED_URL("Malformed URL"),
    CANNOT_FIND_CLASS_EXCEPTION("Cannot find class Exception"),
    CLASS_PLUGIN_INHERITANCE("Class does not inherit plugin"),
    NO_TARGET("Plugin does not have a target"),
    TARGET_MISS_MATCH("Targets do not match"),
    ANNOTATION_METHOD_ILLEGAL_ACCESS_EXCEPTION("Annotation Method illegal access exception"),
    ANNOTATION_METHOD_INVOCATION_TARGET_EXCEPTION("Annotation Method invocation target exception"),
    MISSING_ANNOTATION("Missing annotation"),
    MISSING_ANNOTATION_VALUE("Missing annotation value"),
    CANNOT_PARSE_VERSION_STRING("Cannot parse Version String"),
    CANNOT_PARSE_DEPENDENCIES_STRING("Cannot parse Dependencies String"),
    PLUGIN_INSTANTIATION_EXCEPTION("Plugin Instantiation Exception"),
    PLUGIN_ILLEGAL_ACCESS_EXCEPTION("Plugin Illegal Access Exception"),
    PLUGIN_FILE_SIZE_IO_EXCEPTION("Plugin file size Io Exception"),
    PLUGIN_DATA_DIRECTORY_CREATE_ERROR("This should not happen but could not create plugin data directory"),
    PLUGIN_ON_ENABLE_EXCEPTION("This means an exception was thrown by the plugin Plugin.onEnable() code by the plugin" +
            " developer"),
    PLUGIN_ON_ENABLE_RETURNED_FALSE("Plugin.onEnable() returned false which means the plugin itself needs further" +
            "configuration before being reloaded"),
    SUCCESS_LOAD("Plugin was successfully loaded"),
    SUCCESS_ON_ENABLE("Plugin was successfully enabled")
    ;



    private final String string;

    PluginLoadCode(String string) {
        this.string = string;
    }
}

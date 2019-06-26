package com.virtual_hex.editor;

public class PluginHolder {

    private final PluginWrapper pluginWrapper;

    // Debugging stuff here
    private StatusCode statusCode;
    private final String pluginFileString;
    private String customStatusString;

    public PluginHolder(StatusCode statusCode, String pluginFileString, String pluginCustomStatusString) {
      this(null, statusCode, pluginFileString, pluginCustomStatusString);
    }

    public PluginHolder(PluginWrapper pluginWrapper, StatusCode statusCode, String pluginFileString, String customStatusString) {
        this.pluginWrapper = pluginWrapper;
        this.statusCode = statusCode;
        this.pluginFileString = pluginFileString;
        this.customStatusString = customStatusString;
    }

    public PluginHolder(PluginWrapper pluginWrapper, StatusCode statusCode, String pluginFileString) {
        this.pluginWrapper = pluginWrapper;
        this.statusCode = statusCode;
        this.pluginFileString = pluginFileString;
    }

    public PluginWrapper getPluginWrapper() {
        return pluginWrapper;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getPluginFileString() {
        return pluginFileString;
    }

    public String getPluginCustomStatusString() {
        return customStatusString;
    }

    PluginHolder setLoadCodeAndStatus(StatusCode statusCode, String customStatusString) {
        this.statusCode = statusCode;
        this.customStatusString = customStatusString;
        return this;
    }


    PluginHolder setLoadCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }


    PluginHolder setCustomStatusString(String customStatusString) {
        this.customStatusString = customStatusString;
        return this;
    }


    @Override
    public String toString() {
        return "PluginHolder{" +
                "pluginWrapper=" + pluginWrapper +
                ", statusCode=" + statusCode +
                ", pluginFileString='" + pluginFileString + '\'' +
                ", customStatusString='" + customStatusString + '\'' +
                '}';
    }

    public enum StatusCode {
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
        PLUGIN_ON_ENABLE_EXCEPTION("This means an exception was thrown by the plugin Plugin.onEnable() code by the plugin developer"),
        PLUGIN_ON_ENABLE_RETURNED_FALSE("Plugin.onEnable() returned false which means the plugin itself needs further configuration before being reloaded"),
        SUCCESS_LOAD("Plugin was successfully loaded"),
        SUCCESS_ON_ENABLE("Plugin was successfully enabled"),
        SUCCESS_ON_DISABLE("Plugin was successfully disabled"),
        SUCCESS_UNLOAD("Plugin was successfully unloaded"),


        ;

        public final String string;

        StatusCode(String string) {
            this.string = string;
        }
    }
}

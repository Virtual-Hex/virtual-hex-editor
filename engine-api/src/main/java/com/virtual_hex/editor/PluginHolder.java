package com.virtual_hex.editor;

public class PluginHolder {

    private final PluginWrapper pluginWrapper;
    private PluginLoadCode pluginLoadCode;
    private final String pluginFileString;
    private String customStatusString;

    public PluginHolder(PluginLoadCode pluginLoadCode, String pluginFileString, String pluginCustomStatusString) {
      this(null, pluginLoadCode, pluginFileString, pluginCustomStatusString);
    }

    public PluginHolder(PluginWrapper pluginWrapper, PluginLoadCode pluginLoadCode, String pluginFileString, String customStatusString) {
        this.pluginWrapper = pluginWrapper;
        this.pluginLoadCode = pluginLoadCode;
        this.pluginFileString = pluginFileString;
        this.customStatusString = customStatusString;
    }

    public PluginHolder(PluginWrapper pluginWrapper, PluginLoadCode pluginLoadCode, String pluginFileString) {
        this.pluginWrapper = pluginWrapper;
        this.pluginLoadCode = pluginLoadCode;
        this.pluginFileString = pluginFileString;
    }

    public PluginWrapper getPluginWrapper() {
        return pluginWrapper;
    }

    public PluginLoadCode getPluginLoadCode() {
        return pluginLoadCode;
    }

    public String getPluginFileString() {
        return pluginFileString;
    }

    public String getPluginCustomStatusString() {
        return customStatusString;
    }

    PluginHolder setLoadCodeAndStatus(PluginLoadCode pluginLoadCode, String customStatusString) {
        this.pluginLoadCode = pluginLoadCode;
        this.customStatusString = customStatusString;
        return this;
    }


    PluginHolder setLoadCode(PluginLoadCode pluginLoadCode) {
        this.pluginLoadCode = pluginLoadCode;
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
                ", pluginLoadCode=" + pluginLoadCode +
                ", pluginFileString='" + pluginFileString + '\'' +
                ", customStatusString='" + customStatusString + '\'' +
                '}';
    }
}

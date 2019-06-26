package com.virtual_hex.editor;



public class VersionUtils {

    public static SomeVersion parseUnknown(String version){
        if(version.contains(".")){
            return parseComplex(version);
        } else {
            return parseSimple(version);
        }
    }

    public static SimpleVersion parseSimple(String versionString){
        int i = Integer.parseInt(versionString);
        return new SimpleVersion(i);
    }


    public static ComplexVersion parseComplex(String versionString) {
        return parseComplex(versionString, 0);
    }

    public static ComplexVersion parseComplex(String versionString, long version) {
        long versionBuildTime = 0;
        String[] versionStrings = versionString.split("-");
        // Set release by default because if only version number then we are a release.
        State state = State.RELEASE;
        if(versionStrings.length == 3){
            String snapshot = versionStrings[2];
            if(snapshot.equals("SNAPSHOT")){ // or TIME STAMP
                versionBuildTime = version;
            } else {
                versionBuildTime = Long.parseLong(snapshot);
            }
            String stateString = versionStrings[1];
            state = State.valueOf(stateString);
        } else if (versionStrings.length == 2){
            String unknown = versionStrings[1];
            if(unknown.equals("SNAPSHOT")){ // or timestamp
            } else {
                try {
                    state = State.valueOf(unknown);
                } catch (Exception e){
                    e.printStackTrace(); //Temp
                    versionBuildTime = Long.parseLong(unknown);
                }
            }
        }

        String[] versionNumberStrings = versionStrings[0].split("\\.");
        int major = versionNumberStrings.length >= 1 ? Integer.parseInt(versionNumberStrings[0]) : 0;
        int minor = versionNumberStrings.length >= 2 ? Integer.parseInt(versionNumberStrings[1]) : 0;
        int patch = versionNumberStrings.length >= 3 ? Integer.parseInt(versionNumberStrings[2]) : 0;
        return new ComplexVersion(major, minor, patch, state, versionBuildTime);
    }
}

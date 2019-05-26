package com.mr00anderson.jawe.json;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import org.ice1000.jimgui.NativeBool;

public class NativeBoolSerializer implements JsonSerializer<NativeBool> {

    public NativeBoolSerializer() {
    }

    @Override
    public void write(Json json, NativeBool object, Class knownType) {
        json.writeValue(object.accessValue());
    }

    @Override
    public NativeBool read(Json json, JsonValue jsonData, Class type) {
        NativeBool nativeBool = new NativeBool();
        nativeBool.modifyValue(jsonData.asBoolean());
        return nativeBool;
    }
}

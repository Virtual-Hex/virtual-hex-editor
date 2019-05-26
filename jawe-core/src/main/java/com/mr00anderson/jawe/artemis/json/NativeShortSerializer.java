package com.mr00anderson.jawe.artemis.json;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import org.ice1000.jimgui.NativeShort;

public class NativeShortSerializer implements JsonSerializer<NativeShort> {

    public NativeShortSerializer() {
    }

    @Override
    public void write(Json json, NativeShort object, Class knownType) {
        json.writeValue(object.accessValue());
    }

    @Override
    public NativeShort read(Json json, JsonValue jsonData, Class type) {
        NativeShort nativeType = new NativeShort();
        nativeType.modifyValue(jsonData.asShort());
        return nativeType;
    }
}

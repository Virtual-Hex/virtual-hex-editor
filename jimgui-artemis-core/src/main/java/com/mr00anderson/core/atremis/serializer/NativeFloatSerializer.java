package com.mr00anderson.core.atremis.serializer;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import org.ice1000.jimgui.NativeFloat;

public class NativeFloatSerializer implements JsonSerializer<NativeFloat> {

    public NativeFloatSerializer() {
    }

    @Override
    public void write(Json json, NativeFloat object, Class knownType) {
        json.writeValue(object.accessValue());
    }

    @Override
    public NativeFloat read(Json json, JsonValue jsonData, Class type) {
        NativeFloat nativeType = new NativeFloat();
        nativeType.modifyValue(jsonData.asFloat());
        return nativeType;
    }
}

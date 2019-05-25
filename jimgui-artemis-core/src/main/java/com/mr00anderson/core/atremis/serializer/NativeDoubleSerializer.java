package com.mr00anderson.core.atremis.serializer;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import org.ice1000.jimgui.NativeDouble;

public class NativeDoubleSerializer implements JsonSerializer<NativeDouble> {

    public NativeDoubleSerializer() {
    }

    @Override
    public void write(Json json, NativeDouble object, Class knownType) {
        json.writeValue(object.accessValue());
    }

    @Override
    public NativeDouble read(Json json, JsonValue jsonData, Class type) {
        NativeDouble nativeType = new NativeDouble();
        nativeType.modifyValue(jsonData.asDouble());
        return nativeType;
    }
}

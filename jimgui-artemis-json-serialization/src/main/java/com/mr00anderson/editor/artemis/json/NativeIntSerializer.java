package com.mr00anderson.editor.artemis.json;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import org.ice1000.jimgui.NativeInt;

public class NativeIntSerializer implements JsonSerializer<NativeInt> {

    public NativeIntSerializer() {
    }

    @Override
    public void write(Json json, NativeInt object, Class knownType) {
        json.writeValue(object.accessValue());
    }

    @Override
    public NativeInt read(Json json, JsonValue jsonData, Class type) {
        NativeInt nativeType = new NativeInt();
        nativeType.modifyValue(jsonData.asInt());
        return nativeType;
    }
}

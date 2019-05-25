package com.mr00anderson.editor.artemis.json;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import org.ice1000.jimgui.NativeLong;

public class NativeLongSerializer implements JsonSerializer<NativeLong> {

    public NativeLongSerializer() {
    }

    @Override
    public void write(Json json, NativeLong object, Class knownType) {
        json.writeValue(object.accessValue());
    }

    @Override
    public NativeLong read(Json json, JsonValue jsonData, Class type) {
        NativeLong nativeType = new NativeLong();
        nativeType.modifyValue(jsonData.asLong());
        return nativeType;
    }
}

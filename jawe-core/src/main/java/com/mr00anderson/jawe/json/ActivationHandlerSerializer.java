package com.mr00anderson.jawe.json;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.mr00anderson.jawe.handlers.ActivationHandler;

public class ActivationHandlerSerializer implements JsonSerializer<ActivationHandler> {

    @Override
    public void write(Json json, ActivationHandler object, Class knownType) {
        System.out.println(object);
    }

    @Override
    public ActivationHandler read(Json json, JsonValue jsonData, Class type) {
        System.out.println(jsonData);
        return null;
    }
}

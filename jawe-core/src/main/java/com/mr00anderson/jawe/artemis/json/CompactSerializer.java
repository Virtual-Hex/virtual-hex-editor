package com.mr00anderson.jawe.artemis.json;

import com.artemis.World;
import com.artemis.io.SaveFileFormat;
import com.artemis.io.SerializationException;
import com.artemis.managers.WorldSerializationManager;

import java.io.InputStream;
import java.io.OutputStream;


// TODO
public class CompactSerializer extends WorldSerializationManager.ArtemisSerializer<Object> {

    protected CompactSerializer(World world) {
        super(world);
    }

    // Used to add additional classes for serialization
    @Override
    public WorldSerializationManager.ArtemisSerializer register(Class type, Object serializer) {
        return null;
    }

    @Override
    protected void save(OutputStream out, SaveFileFormat format) throws SerializationException {

    }

    @Override
    protected SaveFileFormat load(InputStream is, Class format) {
        return null;
    }
}

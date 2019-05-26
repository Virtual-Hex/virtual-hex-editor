package com.mr00anderson.jawe.json;

import com.artemis.World;
import com.artemis.io.JsonArtemisSerializer;
import org.ice1000.jimgui.*;

public class EnhancedJsonArtemisSerializer extends JsonArtemisSerializer {


    public EnhancedJsonArtemisSerializer(World world) {
        super(world);

        this.register(NativeBool.class, new NativeBoolSerializer());
        this.register(NativeShort.class, new NativeShortSerializer());
        this.register(NativeInt.class, new NativeIntSerializer());
        this.register(NativeLong.class, new NativeLongSerializer());
        this.register(NativeFloat.class, new NativeFloatSerializer());
        this.register(NativeDouble.class, new NativeDoubleSerializer());
    }
}

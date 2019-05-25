package com.mr00anderson.editor.jimgui.wrappers;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class BufferAbstractDataFieldMapper extends AbstractDataFieldMapper<byte[]> {

    /**
     * 255 byte buffer limit, builtin, can be larger
     *
     * @param field
     * @param object
     */
    public BufferAbstractDataFieldMapper(Field field, Object object) {
        super(new byte[255], field, object);
    }

    public BufferAbstractDataFieldMapper(byte[] nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    protected void copyStringIntoBuffer(String textInput){
        Arrays.fill(data, (byte) 0);
        System.arraycopy(textInput.getBytes(), 0, data, 0, textInput.length());
    }

    public int getBufferEndIndex(){
        return bufferEndIndex(data);
    }

    public static int bufferEndIndex(byte[] data){
        for(int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }
}

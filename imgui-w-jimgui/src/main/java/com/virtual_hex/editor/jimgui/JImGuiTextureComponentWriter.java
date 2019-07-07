package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Image;
import com.virtual_hex.editor.data.ImageButton;
import org.ice1000.jimgui.JImTextureID;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.WeakHashMap;

// Clear cache for images in case we update outside of here
public abstract class JImGuiTextureComponentWriter extends JImGuiComponentWriter{

    public WeakHashMap<Object, JImTextureID> cachedImageReferences = new WeakHashMap<>();

    public JImTextureID getTextureId(ImageButton<?, ?> image, DefaultUIWriter writer){
        // TODO Could use a group type handler by name or id and place that into writer, by using
        // the writer to createNativeInt a cache cleaning mechanism here

        JImTextureID imTextureID = cachedImageReferences.get(image);
        if(imTextureID != null){
            return imTextureID;
        } else {
            Object object = image.from;
            if(object instanceof String){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((String) object));
            } else if(object instanceof File){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((File) object));
            } else if(object instanceof Path){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromPath((Path) object));
            } else if(object instanceof byte[]){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromBytes((byte[]) object));
            } else if (object instanceof Long) {
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromExistingID((long) object, image.width, image.height));
            } else if (object instanceof URI){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromUri((URI) object));
            } else {
                return null;
            }
        }
    }


    public JImTextureID getTextureId(Image<?, ?> image, DefaultUIWriter writer){
        // TODO Could use a group type handler by name or id and place that into writer, by using
        // the writer to createNativeInt a cache cleaning mechanism here

        JImTextureID imTextureID = cachedImageReferences.get(image);
        if(imTextureID != null){
            return imTextureID;
        } else {
            Object object = image.from;
            if(object instanceof String){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((String) object));
            } else if(object instanceof File){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((File) object));
            } else if(object instanceof Path){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromPath((Path) object));
            } else if(object instanceof byte[]){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromBytes((byte[]) object));
            } else if (object instanceof Long) {
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromExistingID((long) object, image.width, image.height));
            } else if (object instanceof URI){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromUri((URI) object));
            } else {
                return null;
            }
        }
    }

}

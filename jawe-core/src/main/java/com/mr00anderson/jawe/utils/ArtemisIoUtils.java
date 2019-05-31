package com.mr00anderson.jawe.utils;

import com.artemis.Aspect;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.managers.WorldSerializationManager;
import com.artemis.utils.IntBag;
import com.mr00anderson.jawe.JaweSaveFileFormat;
import com.mr00anderson.jawe.systems.JaweRenderingSystem;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArtemisIoUtils {


    public static String saveAllString(World world) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        save(world, bos);
        bos.close();
        return new String(bos.toByteArray());
    }

    public static void saveAllFile(World world, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        FileOutputStream fos = new FileOutputStream(path.toFile(), false);
        save(world, fos);
        fos.flush();
        fos.close();
    }

    public static void save(World world, OutputStream outputStream){
        WorldSerializationManager manager = world.getSystem(WorldSerializationManager.class);
        EntitySubscription entitySubscription = world.getAspectSubscriptionManager().get(Aspect.all());
        IntBag entities = entitySubscription.getEntities();
        JaweRenderingSystem renderingSystem = world.getSystem(JaweRenderingSystem.class);
        manager.save(outputStream, new JaweSaveFileFormat(entities));
    }

}
package com.mr00anderson.core;

import com.artemis.*;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.io.SaveFileFormat;
import com.artemis.managers.TagManager;
import com.artemis.managers.WorldSerializationManager;
import com.artemis.utils.IntBag;
import com.mr00anderson.core.atremis.components.TestComponentComplex;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class WorldSerializationTest {

    public WorldSerializationTest() {
    }

    /**
     * This test is to see the artemis obd serialization
     */
//    @Disabled
    @Test
    public void worldSerTest(){
        // Create the world
        final WorldSerializationManager manager = new WorldSerializationManager();
        final TagManager tagManager = new TagManager();

        World world = new World(
                new WorldConfiguration()
                        .setSystem(manager)
                        .setSystem(tagManager));

        JsonArtemisSerializer jsonArtemisSerializer = new JsonArtemisSerializer(world).prettyPrint(true);
        manager.setSerializer(jsonArtemisSerializer);

        ComponentMapper<TestComponentComplex> mapperTwo = world.getMapper(TestComponentComplex.class);

        // Create various simple and complex entities
        int simple1 = world.create();
        tagManager.register("TestTagSimple", simple1);

        TestComponentComplex testComp = mapperTwo.create(simple1);

        world.process();

        EntitySubscription entitySubscription = world.getAspectSubscriptionManager().get(Aspect.all());
        IntBag entities = entitySubscription.getEntities();
        try(
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ){

            manager.save(bos, new SaveFileFormat(entities));
            String json = new String(bos.toByteArray());
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        // Create the world
        WorldSerializationManager manager = new WorldSerializationManager();
        TagManager tagManager = new TagManager();

        World world = new World(
                new WorldConfiguration()
                        .setSystem(manager)
                        .setSystem(tagManager));

        JsonArtemisSerializer jsonArtemisSerializer = new JsonArtemisSerializer(world).prettyPrint(true);
        manager.setSerializer(jsonArtemisSerializer);

        ComponentMapper<TestComp> mapperTwo = world.getMapper(TestComp.class);

        // Create various simple and complex entities
        int simple1 = world.create();
        tagManager.register("TestTagSimple", simple1);

        TestComp testComp = mapperTwo.create(simple1);
        testComp.testInt = 10;

        world.process();

        EntitySubscription entitySubscription = world.getAspectSubscriptionManager().get(Aspect.all());
        IntBag entities = entitySubscription.getEntities();
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ){

            manager.save(bos, new SaveFileFormat(entities));
            String json = new String(bos.toByteArray());
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws IOException {
//        // Setup the world and serializer
//        final WorldSerializationManager manager = new WorldSerializationManager();
//        final TagManager tagManager = new TagManager();
//
//        World world = new World(new WorldConfiguration().setSystem(manager).setSystem(tagManager));
//        JsonArtemisSerializer jsonArtemisSerializer = new JsonArtemisSerializer(world);
//        jsonArtemisSerializer.prettyPrint(true);
//
//        manager.setSerializer(jsonArtemisSerializer);
//
//
//        // Create various simple and complex entities
//        int simple1 = world.create();
//        int simple2 = world.create();
//        int simple3 = world.create();
//
//        // Add some test tags
//        tagManager.register("TestTag1", simple1);
//        tagManager.register("TestTag2", simple2);
//        tagManager.register("TestTag3", simple3);
//
//
//        // Process once
//        world.process();
//
//        // Collect the entities
//        EntitySubscription entitySubscription = world.getAspectSubscriptionManager().get(Aspect.all());
//        IntBag entities = entitySubscription.getEntities();
//
//        // Write to String
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        manager.save(bos, new SaveFileFormat(entities));
//        String json = new String(bos.toByteArray());
//        System.out.println(json);
//
//
//        // Write to File
//        Path path = Paths.get("level.json");
//        FileOutputStream fos = new FileOutputStream(path.toFile(), false);
//        manager.save(fos, new SaveFileFormat(entities));
//        fos.flush();
//    }
}

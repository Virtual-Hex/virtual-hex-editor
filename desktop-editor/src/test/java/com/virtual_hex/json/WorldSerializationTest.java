package com.virtual_hex.json;

import com.artemis.*;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.io.SaveFileFormat;
import com.artemis.managers.TagManager;
import com.artemis.managers.WorldSerializationManager;
import com.artemis.utils.IntBag;
import com.virtual_hex.VirtualHexDesktopEditor;
import com.virtual_hex.TestComponentComplex;
import com.virtual_hex.TestComponentNative;
import com.virtual_hex.systems.JaweRenderingSystem;
import com.virtual_hex.types.BasicApp;
import org.ice1000.jimgui.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class WorldSerializationTest implements BasicApp {

    private boolean running = true;

    public WorldSerializationTest() {
    }

//    @Disabled
    @Test
    public void desktopEditorSerializeTest(){
        VirtualHexDesktopEditor virtualHexDesktopEditor = new VirtualHexDesktopEditor();
    }

    /**
     * This test is to see the artemis obd serialization
     */
//    @Disabled
    @Test
    public void worldSerTest(){
        // Create the worldWrapper
        final WorldSerializationManager manager = new WorldSerializationManager();
        final TagManager tagManager = new TagManager();

        World world = new World(
                new WorldConfiguration()
                        .setSystem(manager)
                        .setSystem(tagManager)
                        .setSystem(new JaweRenderingSystem())
        );

        JsonArtemisSerializer jsonArtemisSerializer = new JaweJsonArtemisSerializer(world).prettyPrint(true);
        manager.setSerializer(jsonArtemisSerializer);

        ComponentMapper<TestComponentComplex> mapperTwo = world.getMapper(TestComponentComplex.class);
        ComponentMapper<TestComponentNative> mapperthree = world.getMapper(TestComponentNative.class);

        // Create various simple and complex entities
        int simple1 = world.create();
        tagManager.register("TestTagSimple", simple1);
        TestComponentComplex testComp = mapperTwo.create(simple1);


        int simple2 = world.create();
        TestComponentNative testComponentNative = mapperthree.create(simple2);
        testComponentNative.hi = 50;

        JaweRenderingSystem system = world.getSystem(JaweRenderingSystem.class);
        system.setMainApp(this, "JImGui Artemis obd World Editor (Jawe)");

        world.process();


        // Modifications after test because of worldWrapper must process and start Jimgui
        testComponentNative.nativeBool = new NativeBool();
        testComponentNative.nativeBool.modifyValue(true);

        testComponentNative.nativeShort = new NativeShort();
        testComponentNative.nativeShort.modifyValue((short) 120);

        testComponentNative.nativeInt = new NativeInt();
        testComponentNative.nativeInt.modifyValue(65);

        testComponentNative.nativeLong = new NativeLong();
        testComponentNative.nativeLong.modifyValue( 55555L);

        testComponentNative.nativeFloat = new NativeFloat();
        testComponentNative.nativeFloat.modifyValue(3.14f);

        testComponentNative.nativeDouble = new NativeDouble();
        testComponentNative.nativeDouble.modifyValue(10.25d);

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


        testComponentNative.nativeBool.deallocateNativeObject();
        testComponentNative.nativeInt.deallocateNativeObject();

        testComponentNative.dispose();

        world.dispose();
    }


    public static void main(String[] args) {
        // Create the worldWrapper
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

    @Override
    public void setRunning() {
        this.running = true;
    }

    @Override
    public void setNotRunning() {
        this.running = false;
    }

//    public static void main(String[] args) throws IOException {
//        // Setup the worldWrapper and serializer
//        final WorldSerializationManager manager = new WorldSerializationManager();
//        final TagManager tagManager = new TagManager();
//
//        World worldWrapper = new World(new WorldConfiguration().setSystem(manager).setSystem(tagManager));
//        JsonArtemisSerializer jsonArtemisSerializer = new JsonArtemisSerializer(worldWrapper);
//        jsonArtemisSerializer.prettyPrint(true);
//
//        manager.setSerializer(jsonArtemisSerializer);
//
//
//        // Create various simple and complex entities
//        int simple1 = worldWrapper.create();
//        int simple2 = worldWrapper.create();
//        int simple3 = worldWrapper.create();
//
//        // Add some test tags
//        tagManager.register("TestTag1", simple1);
//        tagManager.register("TestTag2", simple2);
//        tagManager.register("TestTag3", simple3);
//
//
//        // Process once
//        worldWrapper.process();
//
//        // Collect the entities
//        EntitySubscription entitySubscription = worldWrapper.getAspectSubscriptionManager().get(Aspect.all());
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

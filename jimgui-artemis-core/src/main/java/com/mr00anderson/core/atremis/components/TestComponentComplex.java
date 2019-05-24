package com.mr00anderson.core.atremis.components;

import com.artemis.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestComponentComplex extends Component {

    public TestComponentComplex() {
    }

    public boolean booleanPrim = true;
//        public Boolean aBoolean = true;
    public boolean[] booleansPrim = {true, true};
//        public Boolean[] aBooleans = {true, true};
    public byte bytePrim = 127;
//        public Byte aByte = 127;
    public byte[] bytesPrim = {120, 120};
//        public Byte[] aBytes = {120, 120};
    public short shortPrim = 256;
//        public Short aShort = 256;
    public short[] shortsPrim = {256, 256};
//        public Short[] aShorts = {256, 256};
    public int intPrim = 512;
//        public Integer aInteger = 512;
    public int[] intsPrim = {512, 512};
//        public Integer[] aInts = {512, 512};

    public long longPrim = 512;
    //        public Long aLong = 512;
    public long[] longsPrim = {512, 512};
    //        public Long[] aLongs = {512, 512};

    public float floatPrim = 1.2f;
    public float[] floatsPrim = {1.2f, 1.2f};
//        public Float[] aFloats = {1.0f, 1.0f};
//        public Float aFloat = 1.0f;
    public double doublePrim = 1.2d;
//        public Double aDouble = 1.0d;
    public double[] doublesPrim = {1.2d, 1.2d};
//        public Double[] aDoubles = {1.0d, 1.0};
//
    public String aString = "TestString";
    public String[] aStrings = {"TestString1", "TestString2"};
//
//
        public DictionaryComponent component = new DictionaryComponent("One", "Two");
        public Component[] components = {new DictionaryComponent("cat, dog"), new DictionaryComponent("bird, plane")};
        public Object[] objects = {"Test", "Test 2"};

        List<String> strings = makeListSimple();
        List<DictionaryComponent> dictionaryComponents = makeListComplex();

        Map<String, String> simpleMap = getSimpleMap();

        Map<String, DictionaryComponent> complexMap = getComplexMap();

        private Map<String, DictionaryComponent> getComplexMap() {
            Map<String, DictionaryComponent> simpleMap = new HashMap<>();
            simpleMap.put("TestKey1", (DictionaryComponent) components[0]);
            simpleMap.put("TestKey2", (DictionaryComponent) components[1]);
            return simpleMap;
        }

        private Map<String, String> getSimpleMap() {
            Map<String, String> simpleMap = new HashMap<>();
            simpleMap.put("TestKey1", "TestValue1");
            simpleMap.put("TestKey2", "TestValue2");
            return simpleMap;
        }

        private List<String> makeListSimple() {
            List<String> list = new ArrayList<>();

            list.add("hey");
            list.add("hello");
            list.add("chow");

            return list;
        }

        private List<DictionaryComponent> makeListComplex() {
            List<DictionaryComponent> list = new ArrayList<>();

            list.add(component);
            list.add((DictionaryComponent) components[0]);
            list.add((DictionaryComponent) components[1]);

            return list;
        }

}

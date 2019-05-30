package com.mr00anderson.jawe.types;

import java.util.Map;
import java.util.TreeMap;

//This should be project level for instancing eventually

public class Worlds {

    public final Map<String, WorldWrapper> worlds = new TreeMap<>(String::compareTo);

    public Worlds() {
    }
}

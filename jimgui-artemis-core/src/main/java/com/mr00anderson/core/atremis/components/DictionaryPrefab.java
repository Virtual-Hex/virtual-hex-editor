package com.mr00anderson.core.atremis.components;

import com.artemis.annotations.PrefabData;

// Some path
@PrefabData("prefab/")
public class DictionaryPrefab {

    public static final String UI_NAME = "Dictionary";

    public String[] words;




}
//@PrefabData("prefab/player.json")
//public class PlayerPrefab extends Prefab {
//    private ComponentMapper<Position> positionMapper;
//
//    public PlayerPrefab(World world) {
//        super(world, new FallbackFileResolver());
//    }
//
//    public SaveFileFormat create(float x, float y) {
//        SaveFileFormat l = create();
//        Entity player = l.get("player");
//        positionMapper.get(player).xy.setDataFromField(x, y);
//        return l;
//    }
//}
//
//
//
//// Test world serialization
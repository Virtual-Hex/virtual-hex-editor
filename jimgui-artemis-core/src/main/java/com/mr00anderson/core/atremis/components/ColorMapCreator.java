package com.mr00anderson.core.atremis.components;

import com.artemis.Component;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

public class ColorMapCreator extends Component {


    public Int2IntMap int2IntMap = new Int2IntOpenHashMap();

    public ColorMapCreator(Int2IntMap int2IntMap) {
        this.int2IntMap = int2IntMap;
    }
}

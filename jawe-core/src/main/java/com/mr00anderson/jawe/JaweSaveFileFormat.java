package com.mr00anderson.jawe;

import com.artemis.EntitySubscription;
import com.artemis.io.SaveFileFormat;
import com.artemis.utils.IntBag;

public class JaweSaveFileFormat extends SaveFileFormat {
    public JaweSaveFileFormat(IntBag entities) {
        super(entities);
    }

    public JaweSaveFileFormat(EntitySubscription es) {
        super(es);
    }

    public JaweSaveFileFormat() {
    }
}

package com.mr00anderson.jawe;

import com.artemis.ArtemisPlugin;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.link.EntityLinkManager;
import com.artemis.managers.TagManager;
import net.mostlyoriginal.api.event.common.EventSystem;


public class CoreEntityPlugin implements ArtemisPlugin {

    @Override
    public void setup(WorldConfigurationBuilder b) {
        /*
         * This is apart of the entity system, Used for communication for UI events and
         * or only prototyping. No major events, these all happen in the render loop and
         * synchronously
         */
        b.with(1000, new EventSystem());
        /*
         * Managed entity names
         */
        b.with(1000, new TagManager());
        /*
         * Managed entity references
         */
        b.with(1000, new EntityLinkManager());

    }
}

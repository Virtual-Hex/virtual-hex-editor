package com.mr00anderson.editor;

import com.artemis.ArtemisPlugin;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.TagManager;
import net.mostlyoriginal.api.event.common.EventSystem;


public class CoreEntityPlugin implements ArtemisPlugin {

    @Override
    public void setup(WorldConfigurationBuilder b) {
        b.with(
                /*
                 * This is apart of the entity system, Used for communication for UI events and or only prototyping.
                 * No major events, these all happen in the render loop and synchronously
                 */
                1000, new EventSystem()
        )
        .with(
                /**
                 * Managed entity names
                 */
                1000, new TagManager())
        ;
    }
}

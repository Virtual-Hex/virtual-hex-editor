package com.mr00anderson.jawe.types;

import com.artemis.World;
import com.mr00anderson.jawe.components.SomeLocation;
import com.mr00anderson.jawe.systems.JaweRenderingSystem;
import org.jetbrains.annotations.NotNull;

/**
 * Serialize this at the editor level
 *
 * Need name, location. The editor will load the world, based on the saved location information?
 */
public class WorldWrapper implements Comparable<WorldWrapper>{

    public String name;
    public SomeLocation someLocation;
    public World world;

    /**
     * This is used for methods to help get App or JImGui Access
     */
    public transient JaweRenderingSystem renderingSystem;

    public WorldWrapper() {
    }

    public WorldWrapper(String name, SomeLocation someLocation, World world) {
        this.name = name;
        this.someLocation = someLocation;
        this.world = world;
    }

    public WorldWrapper(String name, SomeLocation someLocation, World world, JaweRenderingSystem renderingSystem) {
        this.name = name;
        this.someLocation = someLocation;
        this.world = world;
        this.renderingSystem = renderingSystem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorldWrapper that = (WorldWrapper) o;

        if (!name.equals(that.name)) return false;
        return world.equals(that.world);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + world.hashCode();
        return result;
    }

    @Override
    public int compareTo(@NotNull WorldWrapper o) {
        return name.compareTo(o.name);
    }



}

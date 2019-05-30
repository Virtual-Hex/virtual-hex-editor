package com.mr00anderson.jawe.types;

import com.artemis.World;
import org.jetbrains.annotations.NotNull;

public class WorldWrapper implements Comparable<WorldWrapper>{

    public String name;
    public Location location;
    public World world;

    public WorldWrapper() {
    }

    public WorldWrapper(String name, Location location, World world) {
        this.name = name;
        this.location = location;
        this.world = world;
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

    public enum Location {
        CODE,
        CLASSPATH_FILE,
        FILE,
        URL
    }

}

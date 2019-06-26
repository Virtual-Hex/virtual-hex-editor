package com.virtual_hex.editor;

/**
 *Immutable
 */
public final class SimpleVersion implements SomeVersion<SimpleVersion> {

    public final int version;

    public SimpleVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public int compareTo(SimpleVersion version) {
        return Integer.compare(this.version, version.version);
    }

    @Override
    public SomeVersion.Type getType() {
        return SomeVersion.Type.SIMPLE_VERSION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleVersion that = (SimpleVersion) o;

        return version == that.version;
    }

    @Override
    public int hashCode() {
        return version;
    }

    @Override
    public String toString() {
        return "SimpleVersion{" +
                "version=" + version +
                '}';
    }

    @Override
    public String serializeToString() {
        return String.valueOf(version);
    }

    @Override
    public String serializeToStringPretty() {
        return String.valueOf(version);
    }

    @Override
    public String serializeToFileString(String fileName, String fileExt) {
        return fileName + version + fileExt;
    }
}

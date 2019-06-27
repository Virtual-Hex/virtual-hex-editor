package com.virtual_hex.editor;


/**
 *Immutable
 */
public class Version implements Comparable<Version> {

    public static final Version EMPTY = new Version(0, 0, 0, State.ALPHA, false);

    /**
     * Software: Major additions, functionality change and or incompatible API changes.
     * Assets:  New branches of content
     */
    public final int major;

    /**
     *  Software: Minor functionality changes in a backwards-compatible manner, and small side features
     *  Assets:  Minor adding to existing branches
     */
    public final int minor;

    /**
     * Software: Patch number when for backwards-compatible bug fixes.
     * Asset: Revisions to existing assets
     */
    public final int patch;

    /**
     * State is for which stage of development overall this version is in
     */
    public final State state;

    /**
     * Timestamps are used to indicate a snapshot and used to always use the most current version of the snapshot
     */
    public final long timestamp;

    public final boolean snapshot;

    /**
     *  Constructs a new version that is not a snapshot
     *  @param major
     * @param minor
     * @param patch
     * @param state
     */
    public Version(int major, int minor, int patch, State state, long timestamp){
        this(major, minor, patch, state, timestamp, timestamp > 0);
    }

    /**
     *  Constructs a new version that is not a snapshot
     *  @param major
     * @param minor
     * @param patch
     * @param state
     * @param snapshot
     */
    public Version(int major, int minor, int patch, State state, boolean snapshot){
        this(major, minor, patch, state, 0, snapshot);
    }

    /**
     * Constructs a new version that could be a snapshot. If the timestamp is filled out this is a snapshot
     *  @param major
     * @param minor
     * @param patch
     * @param state
     * @param timestamp long 0 for no snapshot and current time if it is a snapshot
     * @param snapshot
     */
    public Version(int major, int minor, int patch, State state, long timestamp, boolean snapshot) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.state = state;
        this.timestamp = timestamp;
        this.snapshot = snapshot;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public State getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Snapshot is a in development version. Multiple snapshots of a specific version may exist. The
     * version selector will always select the most current by timestamp snapshot.
     *
     * @return boolean if this version is a snapshot
     */
    public boolean isSnapshot(){
        return timestamp > 0;
    }

    @Override
    public int compareTo (Version version){
        int otherMajor = version.getMajor();
        if(this.major > otherMajor) return 1;
        else if(this.major < otherMajor) return -1;

        int otherMinor = version.getMinor();
        if(this.minor > otherMinor) return 1;
        else if(this.minor < otherMinor) return -1;

        int otherPatch = version.getPatch();
        if(this.patch > otherPatch) return 1;
        else if(this.patch < otherPatch) return -1;

        int thisState = state.ordinal();
        int otherState = version.getState().ordinal();
        if(thisState > otherState) return 1;
        else if (thisState < otherState) return -1;

        long otherTimestamp = version.getTimestamp();
        if(this.timestamp == 0 && otherTimestamp == 0){
            return 0;
        } else if (this.timestamp > otherTimestamp || this.timestamp == 0)
            return 1;
        else if (this.timestamp < otherTimestamp || otherTimestamp == 0)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;

        Version version = (Version) o;

        if (getMajor() != version.getMajor()) return false;
        if (getMinor() != version.getMinor()) return false;
        if (getPatch() != version.getPatch()) return false;
        if (getState().ordinal() != version.getState().ordinal()) return false;
        return getTimestamp() == version.getTimestamp();
    }

    @Override
    public int hashCode() {
        int result = getMajor();
        result = 31 * result + getMinor();
        result = 31 * result + getPatch();
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (int) (getTimestamp() ^ (getTimestamp() >>> 32));
        return result;
    }


    @Override
    public String toString() {
        return "Version{" +
                "major=" + major +
                ", minor=" + minor +
                ", patch=" + patch +
                ", state=" + state +
                ", timestamp=" + timestamp +
                ", snapshot=" + snapshot +
                '}';
    }
}

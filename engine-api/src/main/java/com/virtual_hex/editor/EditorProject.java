package com.virtual_hex.editor;


/**
 * Lifecycle - Configuration (Prepare for task), Task (do something with something)
 *
 * The project classloader will have everything loaded for it and its lifecycle is managed by the editor
 *
 * Project files added, should be moved to a project folder, but keep a link to the source for easy refresh
 *
 * Need to track when task was run, and if it needs to run again or not, should have run settings?/group
 * Task:
 *  Input
 *
 *  Output
 *
 *
 */
public class EditorProject {

    public String name;
    public Version version;

    public String resources;// TODO Place holder, for tracking previously provided urls linked to project files



    public ClassLoader classLoader;





    // Project inputs -> assets/code which are needed for task

    // Task to do stuff eventually, at first will be coded in?

    // Assets
    // Libs

    // Can retrieve by url, file, open file, maven repo through eather
}


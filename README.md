# Virtual Hex Editor
An editor that runs on ImGUi through JImGui.

Currently in early development. The purpose of this library is to allow rapid development of game nativeData. This library extends [JImGui](https://github.com/ice1000/jimgui) and [artemis-obd](https://github.com/junkdog/artemis-odb) to tie them together.


The app launches into a editor. This editor will contain menu, option to see the JImGui demo so that users can see what can be added or done. The editor will then access and allow editing of a collections of worlds under a project.

Estimated time until arrival: TBD, in the works while working on college.

- Project Layout (TBD)
data-core (Should be named other then Jawe / These are not dependant on any lib)
data-ext (Independant set of classes used to extend data by adding helper methods)
jimgui-ext (Project is for creating and drawing a serialized tree, independant of artemis)
// Load a project type which has extensions



Goals(Incomplete, not all inclusive)

- [ ] Ability to edit worlds
  - [ ] World Selection Window
  - [ ] Todo - List all Artemis parts - All API will be exposed in the editor
  - [ ] Attachable serializer API (Used to single export components or entities)
      - [ ] Provided Types
        - [ ] Buffer (Bytes, Streams, ByteBuffer) - Easier for newer coders to have a wrapper so we can accept anything
        - [ ] Universal Json (Plug and play Json backends)
        - [ ] Netty ByteBuf (Used by my game engines netcode)

      - [ ] Provided Helpers
        - [ ] Disk Writing

      -[ ] World Version Control, Load version specific, will probably need class loading here for extensions
  -[ ] When editing the world check for possible types, basic on generic and subtyping.

- [ ] World Simulation (We want to see say a generated chunk, item, or whole world)
  - [ ] Ability to simulate various game processing techniques
  - [ ] Ability to simulate the world while viewing aspects of the world
  - [ ] Investigate a window where we can actually run the game (Experimental)
  - [ ] Investigate a test running ImGui with LibGDX at the same time.

- [X] Ability to Serialize the Editor World
  - [X] Save Editor State (Artemis Json - Default Backend)
  - [ ] Load Editor State
  - [ ] Todo - Place Holder ( With the ability to serialize the editor, or parts team development could be a thing )

- [ ] Provide Helper Methods
  - [ ] LibGDX integration (Using Artemis without this library, with the nativeData build from this editor)
  - [ ] LibGDX integration Usinig ImGui with LibGDX

- [ ] JImGui Extension API
  - [ ] Helper methods for drawing
  - [ ] Write a system for anyone to be able to drap and drop like (JavaFX Scene Builder) (Experimental)
  - [ ] Write some abstraction to allow editing of the editor world safely while running
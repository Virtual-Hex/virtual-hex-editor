# jimgui-artemis-odb-world-editor
This editor runs on artemis obd and rendered by ImGUi through JImGui

Currently in early development. The purpose of this library is to allow rapid development of game data. This library extends [JImGui](https://github.com/ice1000/jimgui) and [artemis-obd](https://github.com/junkdog/artemis-odb) to tie them together.


The app launches into a editor. This editor will contain menu, option to see the JImGui demo so that users can see what can be added or done. The editor will then access and allow editing of a collections of worlds under a project.

Estimated time until arrival: TBD, in the works while working on college.

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

- [ ] World Simulation (We want to see say a generated chunk, item, or whole world)
  - [ ] Ability to simulate various game processing techniques
  - [ ] Ability to simulate the world while viewing aspects of the world
  - [ ] Investigate a window where we can actually run the game (Experimental)
  - [ ] Investigate a test running ImGui with LibGDX at the same time.

- [ ] Ability to Serialize the Editor World
  - [ ] Save Editor State
  - [ ] Load Editor State
  - [ ] Todo - Place Holder ( With the ability to serialize the editor, or parts team development could be a thing )

- [ ] Provide Helper Methods
  - [ ] LibGDX integration (Using Artemis without this library, with the data build from this editor)
  - [ ] LibGDX integration Usinig ImGui with LibGDX

- [ ] JImGui Extension API
  - [ ] Helper methods for drawing
  - [ ] Write a system for anyone to be able to drap and drop like (JavaFX Scene Builder) (Experimental)
  - [ ] Write some abstraction to allow editing of the editor world safely while running
# NeutronStar GB

NeutronStar Gb is a Gameboy emulator written in Java 9. I decided to do it after seeing [this post](http://blog.rekawek.eu/2017/02/09/coffee-gb/) in HN. So, it has the same purpose: to learn a lot :)

## TODO

- [ ] Rewrite using Go?

### Components

- [ ] DMG-CPU
  - [ ] CPU
    - [x] Registers
    - [ ] OpCodes
    - [x] Flags
    - [ ] Stack
    - [ ] ALU
      - [ ] OpCodes
  - [ ] PPU (Pixel Processing Unit)
    - [ ] Registers
    - [ ] Tiles
    - [ ] Colors
    - [ ] Background map
    - [ ] Viewport
    - [ ] Window
    - [ ] OBJ (Sprites)
    - [ ] OAM RAM
    - [ ] Vertical timing
    - [ ] Horizontal timing
  - [ ] Interrupt Controller
  - [ ] Timer
  - [ ] Memory
  - [x] Boot ROM
  - [ ] Joypad Input
  - [ ] Sound Controller
  - [ ] Bus
- [ ] RAM
- [ ] VRAM
  - [ ] Window Map
  - [ ] BG Map
  - [ ] BG Tiles
  - [ ] Sprite Tiles
- [ ] Display
- [ ] Controller
- [ ] Sound

## Resources

* [Coffee GB](https://github.com/trekawek/coffee-gb/)
* [Mooneye GB](https://github.com/Gekkio/mooneye-gb)
* [The Ultimate Game Boy Talk](https://www.youtube.com/watch?v=HyzD8pNlpwI)
* [Imran Nazar: GameBoy Emulation in JavaScript](http://imrannazar.com/GameBoy-Emulation-in-JavaScript)
* [Gameboy Bootstrap ROM](http://gbdev.gg8.se/wiki/articles/Gameboy_Bootstrap_ROM)
* [Game Boy CPU Instruction Timings](https://docs.google.com/spreadsheets/d/1tBJwCVpl4I9IUNS_AIh_CMgcxsPt0PYbfvRhkgG6vGw/edit#gid=1120213370)
* [Emulating the GameBoy CPU on .NET](https://ax-h.com/software/development/emulation/2017/12/03/emulating-the-gameboy-cpu-on-dot-net.html)
* [gomeboycolor](https://github.com/djhworld/gomeboycolor)
* [GameBoy CPU manual](http://marc.rawer.de/Gameboy/Docs/GBCPUman.pdf)
* [Gameboy opcodes](http://pastraiser.com/cpu/gameboy/gameboy_opcodes.html)
* [Nitty Gritty Gameboy cycle timing](http://blog.kevtris.org/blogfiles/Nitty%20Gritty%20Gameboy%20VRAM%20Timing.txt)
* [Video Timing](https://github.com/jdeblese/gbcpu/wiki/Video-Timing)
* [BGB emulator](http://bgb.bircd.org/)
* [The Cycle-Accurate Game Boy Docs](https://github.com/AntonioND/giibiiadvance/tree/master/docs)
* [Test ROMs](http://slack.net/~ant/old/gb-tests/)
* [Pandocs](http://bgb.bircd.org/pandocs.htm)
* [Adventures in GameBoy Emulation: Part 2 (The CPU) | WornWinter](https://wornwinter.wordpress.com/2015/02/14/adventures-in-gameboy-emulation-part-2-the-cpu/)
* GB Architecture
* GB MMU
* [What exactly are T-states doing?](https://forums.nesdev.com/viewtopic.php?f=20&t=14014)
* [How does the Gameboy's memory bank switching work?](https://retrocomputing.stackexchange.com/questions/11732/how-does-the-gameboys-memory-bank-switching-work)

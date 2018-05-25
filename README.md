# NeutronStar GB

NeutronStar Gb is a Gameboy emulator written in Java 8. I decided to do it after seeing [this post](http://blog.rekawek.eu/2017/02/09/coffee-gb/) in HN. So, it has the same purpose: to learn a lot :)

## TODO

### Components

- [ ] DMG-CPU
  - [ ] CPU
    - [ ] Registers
    - [ ] OpCodes
    - [ ] Flags
    - [ ] Stack
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
  - [ ] Boot ROM
  - [ ] Joypad Input
  - [ ] Sound Controller
- [ ] RAM
- [ ] VRAM
  - [ ] Window Map
  - [ ] BG Map
  - [ ] BG Tiles
  - [ ] Sprite Tiles
- [ ] Display
- [ ] Controller
- [ ] Sound

### Read

* [GameBoy CPU manual](http://marc.rawer.de/Gameboy/Docs/GBCPUman.pdf)
* [Gameboy opcodes](http://pastraiser.com/cpu/gameboy/gameboy_opcodes.html)
* [Nitty Gritty Gameboy cycle timing](http://blog.kevtris.org/blogfiles/Nitty%20Gritty%20Gameboy%20VRAM%20Timing.txt)
* [Video Timing](https://github.com/jdeblese/gbcpu/wiki/Video-Timing)
* [BGB emulator](http://bgb.bircd.org/) --- good for testing / debugging, works fine with Wine
* [The Cycle-Accurate Game Boy Docs](https://github.com/AntonioND/giibiiadvance/tree/master/docs)
* [Test ROMs](http://slack.net/~ant/old/gb-tests/) - included in the [src/test/resources/roms](src/test/resources/roms)
* [Pandocs](http://bgb.bircd.org/pandocs.htm)

## Resources

* [Coffee GB](https://github.com/trekawek/coffee-gb/)
* [Mooneye GB](https://github.com/Gekkio/mooneye-gb)
* [The Ultimate Game Boy Talk](https://www.youtube.com/watch?v=HyzD8pNlpwI)



import greenfoot.*;

public class EventSystem extends Actor {

    PlayWorld playWorld;
    WorldListener worldListener;
    Player player;
    Scroller scroller;
    Keanu keanu;
    Stonks stonks;
    Tsoukalos tsoukalos;

    public EventSystem() {
    }

    public EventSystem(PlayWorld playWorld) {
        this.playWorld = playWorld;
        player = playWorld.getPlayer();
        worldListener = playWorld.getWorldListener();
        scroller = playWorld.getScroller();
    }

    public void act() {
        checkEvent();

    }

    public void checkEvent() {
        if (!WorldData.PAUZA) {
            /*aici e practic cronologic povestea 
            C: e cutscene
            T: e tutorial
            D: e dialog 
            i: e item
            L: e locatie
             */
            switch (WorldData.nrEvent) {
                //ws11
                //C:wakeup
                //
                case 1: {
                    if (worldListener.getWorldSection() == 11 && player.getWorldX() == playWorld.originalX && player.getWorldY() == playWorld.originalY) {
                        playWorld.initObject(new Tutorial("Cutscene", "wakeup", 3, false), WorldData.menuX, WorldData.menuY);
                    }
                    break;
                }
                //
                //T:walk
                //
                case 2: {
                    playWorld.initObject(new Tutorial("Mechanics", "walk", 1, false), WorldData.menuX, WorldData.menuY);

                    break;
                }
                //
                //D:Keanu1
                //
                case 3: {

                    WorldData.dialogIndex = 1;
                    keanu = new Keanu(playWorld, scroller, "Keanu", WorldData.dialogIndex);
                    playWorld.initUniqueObject(keanu, 13 * 64, 6 * 64);
                    playWorld.initUniqueObject(new Pill(400), 14 * 64, 7 * 64);
                    makeObjective("Talk to Mr.K", 13 * 64, 6 * 64, 11);
                    break;
                }
                //
                //T:inventory
                //
                case 4: {
                    playWorld.initObject(new Tutorial("Mechanics", "inventory", 2, false), WorldData.menuX, WorldData.menuY);
                    break;
                }
                //
                //i:laser
                //T:laser
                case 5: {
                    makeObjective("Get Laser", 17 * 64, 7 * 64, 11);
                    playWorld.initUniqueObject(new PickUp("laser"), 17 * 64, 7 * 64);
                    break;
                }
                //
                //D:Keanu2
                //
                case 6: {

                    WorldData.dialogIndex = 2;
                    if (playWorld.getObjects(Keanu.class).isEmpty()) {
                        keanu = new Keanu(playWorld, scroller, "Keanu", WorldData.dialogIndex);
                        playWorld.initUniqueObject(keanu, 1, 1);
                    } else {
                        keanu = playWorld.getObjects(Keanu.class).get(0);
                    }
                    keanu.setNrDialog(WorldData.dialogIndex);
                    keanu.setLocation(45 * 64 - Scroller.scrolledX, 8 * 64 - Scroller.scrolledY);
                    makeObjective("Talk to Mr.K", 45 * 64, 8 * 64, 11);
                    break;
                }
                //
                //T:map
                //
                case 7: {
                    playWorld.initObject(new Tutorial("Mechanics", "map", 1, false), WorldData.menuX, WorldData.menuY);
                    break;
                }
                //
                //i:sword
                //T:sword
                case 8: {

                    playWorld.initUniqueObject(new PickUp("sword"), 46 * 64, 115 * 64);
                    makeObjective("Get Sword", 46 * 64, 115 * 64, 11);
                    break;
                }
                //
                //D:Keanu3
                //
                case 9: {
                    WorldData.dialogIndex = 3;
                    if (playWorld.getObjects(Keanu.class).isEmpty()) {
                        keanu = new Keanu(playWorld, scroller, "Keanu", WorldData.dialogIndex);
                        playWorld.initUniqueObject(keanu, 1, 1);
                    } else {
                        keanu = playWorld.getObjects(Keanu.class).get(0);
                    }
                    keanu.setNrDialog(WorldData.dialogIndex);
                    keanu.setLocation(72 * 64 - Scroller.scrolledX, 17 * 64 - Scroller.scrolledY);
                    makeObjective("Talk to Mr.K", 72 * 64, 17 * 64, 11);
                    break;
                }
                //
                //C:wall
                //
                case 10: {

                    playWorld.initObject(new Tutorial("Cutscene", "wall", 1, false), WorldData.menuX, WorldData.menuY);
                    break;
                }
                //
                //L:pod
                //
                case 11: {
                    makeObjective("Get To Bridge", 90 * 64, 60 * 64, 11);
                    if (worldListener.getWorldSection() == 11 && player.getWorldX() >= 90 * 64 && player.getWorldY() >= 60 * 64) {
                        WorldData.nrEvent++;
                    }
                    break;
                }
                //
                //
                //ws12
                //D:Stonks4
                //
                case 12: {
                    makeObjective("Talk to Mr.S", 44 * 64, 105 * 64, 12);
                    if (worldListener.getWorldSection() == 12) {
                        WorldData.dialogIndex = 4;
                        stonks = new Stonks(playWorld, scroller, "Stonks", WorldData.dialogIndex);
                        playWorld.initUniqueObject(stonks, 44 * 64, 105 * 64);

                    }
                    break;
                }
                //
                //L:CastleDoor
                //
                case 13: {
                    makeObjective("Get To Door", 53 * 64, 99 * 64, 12);
                    if (worldListener.getWorldSection() == 12 && player.getWorldX() >= 40 * 64 && player.getWorldY() >= 100 * 64) {
                        WorldData.nrEvent++;
                    }
                    break;
                }
                //
                //i:lantern
                //
                case 14: {
                    makeObjective("Get Lantern", 47 * 64, 103 * 64, 12);
                    if (worldListener.getWorldSection() == 12) {
                        playWorld.initUniqueObject(new PickUp("lantern"), 47 * 64, 103 * 64);
                    }
                    break;
                }
                //
                //D:Stonks5
                //
                case 15: {
                    WorldData.dialogIndex = 5;
                    if (playWorld.getObjects(Stonks.class).isEmpty()) {
                        stonks = new Stonks(playWorld, scroller, "Stonks", WorldData.dialogIndex);
                        playWorld.initUniqueObject(stonks, 1, 1);
                    } else {
                        stonks = playWorld.getObjects(Stonks.class).get(0);
                    }
                    stonks.setNrDialog(WorldData.dialogIndex);
                    stonks.setLocation(64 * 64 - Scroller.scrolledX, 113 * 64 - Scroller.scrolledY);
                    makeObjective("Talk to Mr.S", 64 * 64, 113 * 64, 12);
                    break;
                }
                //
                //
                //ws22
                //C:field
                //
                case 16: {
                    makeObjective("Get To Field", 64 * 64, 3 * 64, 22);
                    if (worldListener.getWorldSection() == 22 && player.getWorldX() >= 64 * 64 && player.getWorldY() >= 3 * 64) {
                        playWorld.initObject(new Tutorial("Cutscene", "field", 1, false), WorldData.menuX, WorldData.menuY);
                    }
                    break;
                }
                //
                //D:Tsoukalos6
                //
                case 17: {
                    makeObjective("Talk to Mr.T", 110 * 64, 6 * 64, 22);
                    if (worldListener.getWorldSection() == 22) {
                        WorldData.dialogIndex = 6;
                        tsoukalos = new Tsoukalos(playWorld, scroller, "Tsoukalos", WorldData.dialogIndex);
                        playWorld.initUniqueObject(tsoukalos, 110 * 64, 6 * 64);

                    }
                    break;
                }
                //ws23
                //D:Tsoukalos7
                //
                case 18: {
                    makeObjective("Talk to Mr.T", 45 * 64, 101 * 64, 23);
                    if (worldListener.getWorldSection() == 23) {
                        WorldData.dialogIndex = 7;
                        tsoukalos = new Tsoukalos(playWorld, scroller, "Tsoukalos", WorldData.dialogIndex);
                        playWorld.initUniqueObject(tsoukalos, 45 * 64, 101 * 64);

                    }
                    break;
                }
                //
                //i:blackhole
                //
                case 19: {
                    makeObjective("Get Black Hole", 60 * 64, 45 * 64, 23);
                    if (worldListener.getWorldSection() == 23) {
                        playWorld.initUniqueObject(new PickUp("blackhole"), 60 * 64, 45 * 64);
                    }
                    break;
                }
                //
                //D:Keanu8
                //
                case 20: {
                    makeObjective("Talk to Mr.K", 20 * 64, 11 * 64, 23);

                    WorldData.dialogIndex = 8;
                    if (playWorld.getObjects(Keanu.class).isEmpty()) {
                        keanu = new Keanu(playWorld, scroller, "Keanu", WorldData.dialogIndex);
                        playWorld.initUniqueObject(keanu, 1, 1);
                    } else {
                        keanu = playWorld.getObjects(Keanu.class).get(0);
                    }
                    keanu.setNrDialog(WorldData.dialogIndex);
                    keanu.setLocation(20 * 64 - Scroller.scrolledX, 11 * 64 - Scroller.scrolledY);

                    break;
                }
                //ws13
                //i:portalgun
                //
                case 21: {
                    makeObjective("Get Portal Gun", 80 * 64, 10 * 64, 13);
                    if (worldListener.getWorldSection() == 13) {
                        playWorld.initUniqueObject(new PickUp("portalgun"), 80 * 64, 10 * 64);
                    }
                    break;
                }
                //
                //D:Stonks9
                //
                case 22: {
                    WorldData.dialogIndex = 9;
                    if (playWorld.getObjects(Keanu.class).isEmpty()) {
                        stonks = new Stonks(playWorld, scroller, "Stonks", WorldData.dialogIndex);
                        playWorld.initUniqueObject(stonks, 1, 1);
                    } else {
                        stonks = playWorld.getObjects(Stonks.class).get(0);
                    }
                    stonks.setNrDialog(WorldData.dialogIndex);
                    stonks.setLocation(20 * 64 - Scroller.scrolledX, 125 * 64 - Scroller.scrolledY);
                    makeObjective("Talk to Mr.S", 20 * 64, 125 * 64, 13);
                    break;
                }
                //ws21
                //D:Tsoukalos10
                //
                case 23: {
                    makeObjective("Talk to Mr.T", 125 * 64, 85 * 64, 21);
                    if (worldListener.getWorldSection() == 21) {
                        WorldData.dialogIndex = 10;
                        tsoukalos = new Tsoukalos(playWorld, scroller, "Tsoukalos", WorldData.dialogIndex);
                        playWorld.initUniqueObject(tsoukalos, 125 * 64, 85 * 64);

                    }
                    break;

                }
                //
                //i:icelock
                //
                case 24: {
                    makeObjective("Get Ice Lock", 63 * 64, 67 * 64, 21);
                    if (worldListener.getWorldSection() == 21) {
                        playWorld.initUniqueObject(new PickUp("icelock"), 63 * 64, 67 * 64);
                    }
                    break;
                }
                //ws22
                //D:Keanu11
                //
                case 25: {
                    makeObjective("Talk to Mr.K", 100 * 64, 5 * 64, 22);
                    if (worldListener.getWorldSection() == 22) {
                        WorldData.dialogIndex = 11;
                        keanu = new Keanu(playWorld, scroller, "Keanu", WorldData.dialogIndex);
                        playWorld.initUniqueObject(keanu, 100 * 64, 5 * 64);
                    }
                    break;
                }
                //ws12
                //C:bossfight
                //
                case 26: {
                    makeObjective("Defeat Stroke", 55 * 64, 15 * 64, 12);
                    WorldData.metStroke = false;
                    WorldData.nrEvent++;
                    break;
                }
                default: {
                    makeObjective("Defeat Stroke", 55 * 64, 15 * 64, 12);
                    break;
                }
            }
        }

    }

    public void makeObjective(String name, int x, int y, int ws) {
        WorldData.objective = "\n" + name;
        WorldData.objectiveX = x;
        WorldData.objectiveY = y;
        WorldData.objectiveWS = ws;

    }

}

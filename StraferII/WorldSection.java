
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldSection extends Actor {

    PlayWorld world;

    Scroller scroller;
    Player player;

    public void initWorldSection11() {
        WorldData.visitedWorldSections[1][1] = true;

        world.initObject(new Fps(), 150, 50);

        //world.initObject(new Tutorial("Cutscene", "wakeup", 1, false), WorldData.menuX, WorldData.menuY);
        if (!WorldData.hasSword) {
            world.initObject(new PickUp("sword"), 2000, 2000);
        }
        WorldSection11 ws = new WorldSection11(world, scroller, player);
        ws.init();

    }

    public void initWorldSection12() {
        WorldData.visitedWorldSections[1][2] = true;

        getWorld().addObject(new Fps(), 150, 50);
        WorldSection12 ws = new WorldSection12(world, scroller, player);
        ws.init();

    }

    public void initWorldSection13() {
        WorldData.visitedWorldSections[1][3] = true;

        WorldSection12 ws = new WorldSection12(world, scroller, player);
        ws.init();
    }

    public void initWorldSection21() {
        WorldData.visitedWorldSections[2][1] = true;

        WorldSection21 ws = new WorldSection21(world, scroller, player);
        ws.init();
    }

    public void initWorldSection22() {
        WorldData.visitedWorldSections[2][2] = true;

        WorldSection22 ws = new WorldSection22(world, scroller, player);
        ws.init();
    }

    public void initWorldSection23() {
        WorldData.visitedWorldSections[2][3] = true;

        WorldSection23 ws = new WorldSection23(world, scroller, player);
        ws.init();
    }

    public void act() {
        // Add your action code here.
    }

    public PlayWorld getWorld() {
        return world;
    }

    public Scroller getScroller() {
        return scroller;
    }

    public Player getPlayer() {
        return player;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class WorldSection extends Actor
{
    PlayWorld world;
    Scroller scroller;
    Player player;
   
    public void initWorldSection11() {
        WorldData.visitedWorldSections[1][1] = true;
        world.initObject(new Fps(), 150, 50);
        
        
        
        
        
        /// Npcs
        world.initObject(new Keanu(world, scroller, "Keanu", 1), 1000, 300);

        world.initObject(new SchrodingersCat(scroller, 100, 200), 100, 200);

        world.initObject(new Goblin(scroller, 1000, 2000), 1000, 2000);
        world.initObject(new Goblin(scroller, 3000, 200), 3000, 200);
        world.initObject(new Goblin(scroller, 4000, 2000), 4000, 2000);
       // world.initObject(new Dolpatian(scroller, 400, 200), 400, 200);
       
        world.initObject(new Droid(scroller,2000,1000,"ox",600),2000,1000);
         world.initObject(new Droid(scroller,2000,1000,"oy",400),1000,1000);
///Npcs
            
        
        
        
        
        
        ///WorldStructures
        world.initObject(new PereteInvizibil("D", 1, "mic90"), 2648, 1536);
        world.initObject(new PereteInvizibil("D", 1, "mic90"), 2648, 1600);
        world.initObject(new PereteInvizibil("D", 1, "mic90"), 2648, 1664);
        world.initObject(new PereteInvizibil("S", 1, "mic"), 2688, 1496);
        world.initObject(new PereteInvizibil("W", 1, "mic"), 2688, 1704);
        world.initObject(new PereteInvizibil("S", 1, "mic"), 2752, 1496);
        world.initObject(new PereteInvizibil("W", 1, "mic"), 2752, 1704);
        world.initObject(new PereteInvizibil("S", 1, "mic"), 2816, 1496);
        world.initObject(new PereteInvizibil("W", 1, "mic"), 2816, 1704);
        world.initObject(new PereteInvizibil("S", 1, "mic"), 2880, 1496);
        world.initObject(new PereteInvizibil("W", 1, "mic"), 2880, 1704);
        world.initObject(new PereteInvizibil("A", 1, "mic90"), 2920, 1536);
        world.initObject(new PereteInvizibil("A", 1, "mic90"), 2920, 1600);
        world.initObject(new PereteInvizibil("A", 1, "mic90"), 2920, 1664);

        for (int i = 1; i <= 10500; i += 1024) {
            world.initObject(new PereteInvizibil("W", 1, "mare"), i, 16);
        }

        ///WorldStructures
    }

    public void initWorldSection12() {
        WorldData.visitedWorldSections[1][2] = true;

        getWorld().addObject(new Fps(), 150, 50);
    }

    public void initWorldSection13() {
        WorldData.visitedWorldSections[1][3] = true;
    }

    public void initWorldSection21() {
        WorldData.visitedWorldSections[2][1] = true;
    }

    public void initWorldSection22() {
        WorldData.visitedWorldSections[2][2] = true;
    }

    public void initWorldSection23() {
        WorldData.visitedWorldSections[2][3] = true;
    }
    public void act() 
    {
        // Add your action code here.
    }    
}

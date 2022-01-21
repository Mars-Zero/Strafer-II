
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class WorldListener extends Actor {

    Scroller scroller;
    Player player;
    PlayWorld world;

    public int worldSection = 11;
    int playerDirection = 0;

    List<GreenfootImage> fundaluri = new ArrayList<>();

    public WorldListener(PlayWorld world) {

        this.world = world;

        scroller = world.getScroller();
        player = world.getObjects(Player.class).get(0);
        loadImages();
    }

    void changeWorldSection() {
        scroller.setScrollImage(fundaluri.get(getImageIndex()));
        relocatePlayer();
        clearWorldObjects();

        addObjects();

    }

    public void act() {

        if (player.isAtEdge()) {
            this.worldSection = getNextWorldSection();

            changeWorldSection();
        }

    }

    /**
     * This method deletes all objects in the world
     */
    void clearWorldObjects() {
        List<Actor> list = world.getObjects(Actor.class);
        for (Actor actor : list) {
            if (actor instanceof Player || actor instanceof WorldListener) {
            } else {
                world.removeObject(actor);
            }
        }

    }

    /*
    *pune playerul in functie de directie acolo unde trebuie ca sa fie in continuarea sectiunii din care pleaca
     */
    int relocateX = 60, relocateY = 60;

    void relocatePlayer() {

        relocateX = 60;
        relocateY = 60;

        switch (playerDirection) {
            case 0: {
                relocateY = scroller.getScrollMaxHigh() - relocateY;
                relocateX = player.getWorldX();
                break;
            }
            case 1: {
                relocateX = scroller.getScrollMaxWide() - relocateX;
                relocateY = player.getWorldY();
                break;
            }
            case 2: {
                relocateX = player.getWorldX();
                break;
            }
            case 3: {
                relocateY = player.getWorldY();
                break;
            }
        }

        player.setLocation(relocateX - Scroller.scrolledX, relocateY - Scroller.scrolledY);

    }

    /*
     * This method adds all objects that need to be put in the world
     */
    void addObjects() {
        //add the player to it's position
        //call different methods based on the world that needs to be changed
        switch (worldSection) {
            case 11: {
                initWorldSection11();
                break;
            }
            case 12: {
                initWorldSection12();
                break;
            }
            case 13: {

                break;
            }
            case 21: {

                break;
            }
            case 22: {

                break;
            }
            case 23: {

                break;
            }
        };
    }

    /*
       - vedem toata mapa organizata ca o matrice (linii,coloane) de sectiuni de mapa(8192x8192 pixeli) fiecare in continuarea celeilalte
         cu background 
        ________
       |11|12|13|
       |__|__|__|
       |21|22|23|
       |__|__|__|
    
    
    
        sectiunea colt stanga sus este                  11
        mai jos                                         21
        la dreapta                                      12
        stanga si sus se opreste mapa deci avem         0
    
    
     -   avem un vector de vecini pt modul in care sunt asezate sectiunile de mapa una fata de celalata, deci practic un graf in care conteaza asezarea
        
    
    - ex pt sectiunea 11:
           
    index:          0      1        2        3
    directie:      sus   stanga    jos    dreapta
                   _______________________________
nr sectiunii      |  0 |   0     | 21    |   12   |
urmatoare:        |____|_________|_______|________|

    
    


     */
    int[] sectionNeighbour11 = {0, 0, 21, 12};
    int[] sectionNeighbour12 = {0, 11, 22, 13};
    int[] sectionNeighbour13 = {0, 12, 23, 0};
    int[] sectionNeighbour21 = {11, 0, 0, 22};
    int[] sectionNeighbour22 = {12, 21, 0, 23};
    int[] sectionNeighbour23 = {13, 22, 0, 0};

    private int getNextWorldSection() {

        playerDirection = player.getDirection();
        switch (this.worldSection) {
            case 11: {
                return sectionNeighbour11[playerDirection];
            }
            case 12: {
                return sectionNeighbour12[playerDirection];
            }
            case 13: {
                return sectionNeighbour13[playerDirection];
            }
            case 21: {
                return sectionNeighbour21[playerDirection];
            }
            case 22: {
                return sectionNeighbour22[playerDirection];
            }
            case 23: {
                return sectionNeighbour23[playerDirection];
            }
            default: {
                return 11;
            }

        }
    }

    public void initWorldSection11() {
        System.out.println("11");

        world.initObject(new Fps(), 150, 50);

        ///WorldStructures
        // getWorld().addObject(new PereteInvizibil("A", 1, "mare90"), 0-Scroller.scrolledX, 300-Scroller.scrolledY);//margini
        /* getWorld().addObject(new PereteInvizibil("W", 1, "mic"), 690, 340);
        getWorld().addObject(new PereteInvizibil("W", 1, "mic"), 750, 340);
        getWorld().addObject(new PereteInvizibil("W", 1, "mic"), 690, 340);

        getWorld().addObject(new PereteInvizibil("A", 1, "mic90"), 795, 300);
        getWorld().addObject(new PereteInvizibil("D", 1, "mic90"), 585, 300);

        getWorld().addObject(new PereteInvizibil("S", 1, "mic"), 630, 255);
        getWorld().addObject(new PereteInvizibil("S", 1, "mic"), 690, 255);
        getWorld().addObject(new PereteInvizibil("S", 1, "mic"), 750, 255);

        getWorld().addObject(new PereteInvizibil("D", 1, "mic90"), 1185, 0);//margini
        getWorld().addObject(new PereteInvizibil("D", 1, "mic90"), 1185, 60);//margini
        getWorld().addObject(new PereteInvizibil("D", 1, "mic90"), 1185, 120);//margini
        getWorld().addObject(new PereteInvizibil("D", 1, "mic90"), 1185, 180);//margini
         */
        for (int i = 1; i <= 10500; i += 1024) {
            world.initObject(new PereteInvizibil("W", 1, "mare"), i, 16);
        }

        ///WorldStructures
        
        
        
        /// Npcs
        world.initObject(new Keanu(world, scroller, "Keanu", 1), 1000, 300);

          world.initObject(new Goblin(scroller, 100, 200), 100, 200);
          world.initObject(new Goblin(scroller, 1000, 2000), 1000, 2000);
          world.initObject(new Goblin(scroller, 3000, 200), 3000, 200);
          world.initObject(new Goblin(scroller, 4000, 2000), 4000, 2000);
        ///Npcs
    }

    public void initWorldSection12() {
        System.out.println("12");

        getWorld().addObject(new Fps(), 150, 50);
    }

    public void initWorldSection13() {

    }

    public void initWorldSection21() {

    }

    public void initWorldSection22() {

    }

    public void initWorldSection23() {

    }

    public int getWorldSection() {
        return worldSection;
    }

    public void setWorldSection(int worldSection) {
        this.worldSection = worldSection;
    }

    void loadImages() {
        fundaluri.add(new GreenfootImage("map/worldSection/worldSection" + 11 + ".png"));

        fundaluri.add(new GreenfootImage("map/worldSection/worldSection" + 12 + ".png"));

        fundaluri.add(new GreenfootImage("map/worldSection/worldSection" + 13 + ".png"));

        fundaluri.add(new GreenfootImage("map/worldSection/worldSection" + 21 + ".png"));

        fundaluri.add(new GreenfootImage("map/worldSection/worldSection" + 22 + ".png"));

        fundaluri.add(new GreenfootImage("map/worldSection/worldSection" + 23 + ".png"));
    }

    int getImageIndex() {
        switch (worldSection) {
            case 11: {
                return 0;
            }
            case 12: {
                return 1;
            }
            case 13: {
                return 2;
            }
            case 21: {
                return 3;
            }
            case 22: {
                return 4;
            }
            case 23: {
                return 5;
            }

        }
        return 0;
    }

}

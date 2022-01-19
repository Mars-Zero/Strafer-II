
import greenfoot.*;
import java.util.List;

public abstract class WorldListener extends Actor {

    Scroller scroller;
    Player player;
    World world;

    int worldSection = 11;
    int playerDirection = 0;

    boolean changed = false;

    public WorldListener(World world) {
        scroller = world.getObjects(Scroller.class).get(0);
        player = world.getObjects(Player.class).get(0);
        this.world = world;
    }

    void changeWorldSection() {
        scroller.setScrollImage(new GreenfootImage("map/worldSection/worldSection" + worldSection + ".png"));
        clearWorldObjects();
        addObjects();
        relocatePlayer();
    }

    public void act() {
        if (!changed) {
            if (player.isAtEdge()) {
                this.worldSection = getNextWorldSection();
                changed = true;
                changeWorldSection();
            }
        }
    }

    /**
     * This method deletes all objects in the world
     */
    void clearWorldObjects() {
        List<Actor> list = world.getObjects(Actor.class);
        for (Actor actor : list) {
            if (actor instanceof Player) {
            } else {
                world.removeObject(actor);
            }
        }

    }

    void relocatePlayer() {
        int x = 60, y = 60;
        switch (playerDirection) {
            case 0: {
                y = scroller.getScrollMaxHigh() - y;
                x = player.getWorldX();
            }
            case 1: {
                x = scroller.getScrollMaxWide() - x;
                y = player.getWorldY();
            }
            case 2: {
                x = player.getWorldX();
            }
            case 3: {
                y = player.getWorldY();
            }
        }
        player.setLocation(x, y);
    }

    /*
     * This method adds all objects that need to be put in the world
     */
    void addObjects() {
        //add the player to it's position
        //call different methods based on the world that needs to be changed
        switch (worldSection) {
            case 11: {

                break;
            }
            case 12: {

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

        if (player.getX() >= WorldData.WIDTH) {
            playerDirection = 3;
        }
        if (player.getX() <= 0) {
            playerDirection = 1;
        }
        if (player.getY() >= WorldData.HIGHT) {
            playerDirection = 2;
        }
        if (player.getY() <= 0) {
            playerDirection = 0;
        }
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

    private void worldSection11() {

    }

    private void worldSection12() {

    }

    private void worldSection13() {

    }

    private void worldSection21() {

    }

    private void worldSection22() {

    }

    private void worldSection23() {

    }
    
    

    public int getWorldSection() {
        return worldSection;
    }

    public void setWorldSection(int worldSection) {
        this.worldSection = worldSection;
    }

}

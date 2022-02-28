
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class WorldListener extends WorldSection {

    public int worldSection = 11;
    int playerDirection = 0;
    boolean loaded = false;

    List<GreenfootImage> fundaluri = new ArrayList<>();

    public WorldListener(PlayWorld world) {
        this.loaded = false;
        this.world = world;

        scroller = world.getScroller();
        player = world.getObjects(Player.class).get(0);
        loadImages();

    }

    void changeWorldSection(boolean atLoad) {     //daca e schimbata pt load sau pt miscare
        scroller.setScrollImage(fundaluri.get(WorldData.getWorldSectionShort(worldSection) - 1));
        WorldData.worldSection=this.worldSection;
        if (!atLoad) {
            relocatePlayer();
            
        } 
        clearWorldObjects();

        addObjects();
        SaveSystem.save(WorldData.saveFileNumber, player);
    }

    int cnt = 0;

    public void act() {
        addBoss();
        if (player.isAtEdge()) {
            this.worldSection = getNextWorldSection();

            changeWorldSection(false);
        }
        load();

    }

    public void load() {
        if (!WorldData.PAUZA) {
            cnt++;
        }
        if (cnt > 1) {
            if (!loaded) {
                changeWorldSection(true);
                loaded = true;
            }
        }

    }

    /**
     * This method deletes all objects in the world
     */
    void clearWorldObjects() {
        List<Actor> list = world.getObjects(Actor.class);

        for (Actor actor : list) {
            if (actor instanceof Player || actor instanceof WorldListener ||actor instanceof EventSystem|| actor instanceof HealthBarPlayer || actor instanceof Picture || actor instanceof Buton) {
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


    void addObjects() {
        
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
                initWorldSection13();
                break;
            }
            case 21: {
                initWorldSection21();
                break;
            }
            case 22: {
                initWorldSection22();
                break;
            }
            case 23: {
                initWorldSection23();
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

        playerDirection = player.getExitDirection();
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
   
    void addBoss(){                 ///il adauga pe stroke cand e posibil bossfightul
     if(!WorldData.PAUZA){
            if(!WorldData.metStroke){
                if(WorldData.nrEvent>=26 && worldSection==12){
                    PlayWorld playWorld=(PlayWorld)(getWorld());
                    playWorld.initUniqueObject(new Stroke(playWorld.getScroller(),55*64,15*64,player),55*64,15*64);
                    
                   WorldData.metStroke=true;
                }
            }
        }
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

}

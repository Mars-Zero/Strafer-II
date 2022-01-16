
import greenfoot.*;

public abstract class WorldListener extends Actor {

    Scroller scroller;
    Player player;
    World world;
    int change=1;
    
    public WorldListener(World world) {
        scroller = world.getObjects(Scroller.class).get(0);
        player = world.getObjects(Player.class).get(0);
        this.world=world;
    }
    
    
    /**
     * This method deletes all objects in the world
     */
    void deleteObjects(){
        List<Actor> list=world.getObjects(Actor.class);
        for(Actor actor:list)
        {
            world.removeObject(actor);
        }
        
    }
    
    /**
     * This method adds all objects that need to be put in the world
     */
    void addObjects(){
        //add the player to it's position
        //call different methods based on the world that needs to be changed
        switch(change){
          case 1:{
              
            break;
        }  
        };
    }
    
    public boolean isChangeWorld() {

        if (player.isAtEdge()) {
            if (scroller.getScrolledX() + WorldData.WIDTH / 2 >= scroller.getWide()) {

            }
            if (scroller.getScrolledX() + WorldData.WIDTH / 2 >= scroller.getWide()) {

            }
        }
        return false;
    }

    public void act() {

    }
}

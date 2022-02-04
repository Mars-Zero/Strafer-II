import greenfoot.*;
import java.util.List;


public class Item extends Actor
{
  
    @Override public String toString(){
         //the name of the class
         return this.getClass().getSimpleName();
     }
    
    public static String itemGif="D";//the state of the item
    
    protected void move()
    {
        List players = getWorld().getObjects(Player.class);
       
        if (!players.isEmpty()){
            Actor player = (Actor)players.get(0);
             setLocation(player.getX(),player.getY()+15);
        }
        /*
        if(Greenfoot.isKeyDown("a")){
            itemGif="A";
        }
        else if(Greenfoot.isKeyDown("d")){
            itemGif="D";
        }
        else if(Greenfoot.isKeyDown("w")){
            itemGif="W";
        }
        else if(Greenfoot.isKeyDown("s")){
            itemGif="S";
        }
        else{
            itemGif="S";
        }*/
    }
    
    public void act() 
    {
       
    }    
}

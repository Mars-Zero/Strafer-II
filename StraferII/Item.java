import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;


public class Item extends Actor
{
  
    public static String itemGif="D";
    
      protected void move()
    {
        List players = getWorld().getObjects(Player.class);
       
        if (!players.isEmpty()){
            Actor player = (Actor)players.get(0);
             setLocation(player.getX(),player.getY()+15);
        }
        if(Greenfoot.isKeyDown("a")){
            itemGif="left";
        }
        else if(Greenfoot.isKeyDown("d")){
            itemGif="right";
        }
        else if(Greenfoot.isKeyDown("w")){
            itemGif="up";
        }
        else if(Greenfoot.isKeyDown("s")){
            itemGif="down";
        }
        else{
            itemGif="down";
        }
    }
    
    public void act() 
    {
        
    }    
}

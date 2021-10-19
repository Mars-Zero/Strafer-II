import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inamic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inamic extends Npc

{
    private Animation animation;
    private GifImage gif;
    
    public Inamic(){
        //animation
       
        java.util.List<GreenfootImage> imgs = new GifImage("images/player/player_m_A.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i=0; i<imgs.size(); i++){ 
                images[i] = (GreenfootImage)imgs.get(i);
        }
        
        animation = new Animation(this, images);
        animation.setCycleActs(70);
        animation.setCycleCount(3);
        animation.run();
        animation.setActiveState(true);
        //animation
        
        //gif
        gif=new GifImage("images/player/player_m_D.gif");
        setImage(gif.getCurrentImage());
        //gif
        
    }
    public void act()
    {
       
           animation.run();
            //setImage(gif.getCurrentImage());
    }   
}

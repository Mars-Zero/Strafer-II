import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Pause extends Menu{

    GreenfootImage img=new GreenfootImage("UI/menu/pauseMenu.png");
    
    public Pause(){
        setImage(img);
    }
    
    public void act() {
        if(!WorldData.PAUZA){
            getWorld().removeObject(this);
        }
    }    
}

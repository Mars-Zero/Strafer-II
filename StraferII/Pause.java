import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Pause extends Menu{

    GreenfootImage img=new GreenfootImage("UI/menu/pauseMenu.png");
    boolean butoanead=false;
    public Pause(){
        setImage(img);
        
    }
    
    private void addButoane(){
        this.getWorld().addObject(new Buton("Resume",this),97,173);
        this.getWorld().addObject(new Buton("Map",this),97,200);
        this.getWorld().addObject(new Buton("Tutorials",this),97,250);
        this.getWorld().addObject(new Buton("Main Menu",this),97,300);
    }
    
    public void act() {
        if(!butoanead){
            addButoane();
            butoanead=true;
        }
    }    
}

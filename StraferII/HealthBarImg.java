import greenfoot.*; 


public class HealthBarImg extends HealthBar
{
    
    GreenfootImage img;
    String imageName;
    boolean moveable;
    int x,y;
    boolean added=false;
    public HealthBarImg() {
        super("","",1,1);
        added=false;
        
        img = new GreenfootImage("npc/inamic/stroke/healthBar.png");
        setImage(img);
        moveable=false;
    }
    
    public void act() {
        if(!added){
            added=true;
            x=getX();
            y=getY();
        }
   
            setLocation(x,y);
  
    }

      
}

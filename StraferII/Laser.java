import greenfoot.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Laser extends Item
{
  
    private final int damage=15;
    public int getLaserDmg(){
        return damage;
    }
     
    private long time=0;
    private final int speed=6;
    private int caz=0;
    
    GifImage laserImg=new GifImage("item/laserPlayer.gif");
    
    public Laser(double  grade){
        
        this.setRotation((int)grade);
   
        
        this.time=0;
       

        //GreenfootSound sunet=new GreenfootSound("shootshoot.mp3");
       // sunet.play();
    }
    
    
     
    
    protected void atac(){
       move(speed);
    }
    
    public void act() 
    {
        
        //super.damage();
        setImage(laserImg.getCurrentImage());
         
        if(!WorldData.PAUZA){
            if(isAtEdge()|| isTouching(WorldStructures.class)){
                getWorld().removeObject(this);
                return;
            }
            
        
            atac();
        }
    }    
}

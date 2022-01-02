import greenfoot.*;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PortalGun extends Item{    
     
     HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage portalGunImg=directie.get(super.itemGif);
    private long time=0;
   private static Actor player;
    public PortalGun(){
        
         directie.put("D",new GifImage("images/item/portalGunD.gif"));
        directie.put("W",new GifImage("images/item/portalGunW.gif"));
        directie.put("A",new GifImage("images/item/portalGunA.gif"));
        directie.put("S",new GifImage("images/item/portalGunS.gif"));
       
        portalGunImg=directie.get("D");
        this.time=0;
    
     
        
    } 
    
    protected void move(){
        super.move();
       
        portalGunImg=directie.get(super.itemGif);
    }
    
    private void addPortal(){
        if(Greenfoot.mouseClicked(null)){
            if(Greenfoot.getMouseInfo().getButton()==1){  //right 3 left 1
                getWorld().addObject(new Portal(),Greenfoot.getMouseInfo().getX(),Greenfoot.getMouseInfo().getY());        
            }
        }
    }
    
    private void removePortalGun(){
        if(!Player.equipPortalGun){
            getWorld().removeObject(this);
        }
    }
    
    public void act() {
        
        if(!WorldData.PAUZA){
            move();
            addPortal();
            removePortalGun(); 
        }
       setImage(portalGunImg.getCurrentImage());
    }    
}

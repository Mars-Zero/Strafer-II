
import greenfoot.*; 
import java.util.HashMap;

public class ItemSelect extends Inventory{
    
   GreenfootImage overlay=new GreenfootImage("UI/hud/selectOverlay.png");
    
    
   public ItemSelect(){
       setImage(overlay);
   }
   
   
   private void select(){
         if(Greenfoot.mouseClicked(this)){
            if(Greenfoot.getMouseInfo().getButton()==1){
                
                //System.out.println(super.getItemSelected());
                switch(super.getItemSelected()){
                    case "sword":{
                         Player.equipSword=!Player.equipSword;
                         break;
                    }
                    case "icelock":{
                         Player.equipIceLock=!Player.equipIceLock;
                          break;
                    }
                    case "blackhole":{
                         Player.equipBlackHole=!Player.equipBlackHole;
                          break;
                    }
                    case "lantern":{
                         Player.equipLantern=!Player.equipLantern;
                          break;
                    }
                    case "laser":{
                         Player.equipLaser=!Player.equipLaser;
                          break;
                    }
                    case "portalgun":{
                         Player.equipPortalGun=true;
                          break;
                    }
                    default:{
                    }
                }
               Player.toggledInventory=false;
               
            }
        }
   }
 
   
    public void act(){
        select();
        setLocation(875,430);
        if(!Player.toggledInventory){
            getWorld().removeObject(this);
        }
        
    }    
}

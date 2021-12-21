import greenfoot.*;  
import java.util.HashMap;

public class SabieHold extends Item{
    

     public SabieHold(){
         setImage("item/sabieHold_"+Item.itemGif+".png");
       
    }
                                                                                                                       
    
    private void exi(){
        if(getWorld().getObjects(Sabie.class)!=null){
            getWorld().removeObject(this);
        }
    }
    protected void move(){
        super.move();
        
       
    }
    
    
    public void act() {
        
         setImage("item/sabieHold_"+Item.itemGif+".png");
        if(!WorldData.PAUZA){
            move(); 
            exi();
        }
         

    }    
}

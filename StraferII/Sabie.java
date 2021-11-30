import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;


public class Sabie extends Item
{
   
    public static int damage=25;
    
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage sabieImg=directie.get(Item.itemGif);
    private long time=0;
    
    public Sabie(){
        directie.put("right",new GifImage("item/sabie_a_D.gif"));
        directie.put("up",new GifImage("item/sabie_a_W.gif"));
        directie.put("left",new GifImage("item/sabie_a_A.gif"));
        directie.put("down",new GifImage("item/sabie_a_S.gif"));
       
        sabieImg=directie.get("right");
        this.time=0;
     
    }
    
    protected void move(){
        super.move();
       
        sabieImg=directie.get(super.itemGif);
    }
    
    
    protected  void atac(){
        sabieImg=directie.get(Item.itemGif);
        //super.damage();
    }
    
    public void act() 
    {
        
        if(!WorldData.PAUZA){
            atac();
            move();
        
            time++;
            if(time>20){
                Player.equipSabie=false;
                getWorld().removeObject(this);
            }
        }
        setImage(sabieImg.getCurrentImage());
    }
}
          

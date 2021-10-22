import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math; 

public class Player extends Astroneer
{
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage playerImg=directie.get(super.gif);
    private long timpSab=0,timpLaser=0,timpBoaba=0,timpPumni=0;
    
    public Player(){
        //variabile initiale
        //super.inViata=true;
        //super.hp=400;
        //super.speed=5;
        
        //moarte
        directie.put("death",new GifImage("images/player/player_death.gif"));
        
        //merge
        directie.put("D",new GifImage("images/player/player_m_D.gif"));
        directie.put("W",new GifImage("images/player/player_m_W.gif"));
        directie.put("A",new GifImage("images/player/player_m_A.gif"));
        directie.put("S",new GifImage("images/player/player_m_S.gif"));
        
        //sta
        directie.put("idle", new GifImage("images/player/player_m_Idle.gif"));
        
        //se uita
       /* directie.put("right",new GifImage("vedere_D.gif"));
        directie.put("up",new GifImage("vedere_W.gif"));
        directie.put("left",new GifImage("vedere_A.gif"));
        directie.put("down",new GifImage("vedere_S.gif"));
       */ 
       // HealthBar bara=new HealthBar("Jucator","Viata",this.hp, this.hp);
       // getWorld().addObject(bara,0,0);
        
        
        super.timpPrec=System.currentTimeMillis();
    }
    
    
    public void move(){
        
        //if(super.gif!="idle" && super.gif!="right" &&super.gif!="up" &&super.gif!="down" && super.gif!="left"){atingeNpc();}
        super.move();
       // if(super.gif!="idle" && super.gif!="right" &&super.gif!="up" &&super.gif!="down" && super.gif!="left"){atingeNpc();}
        
        playerImg=directie.get(super.gif);
    }
    protected void vedere(){
   
        playerImg=directie.get(super.gif);
    }
    
    protected void lovit(){
       
    }
        
    public void act() 
    {
        // Add your action code here.
       lovit();
     
          
            move();
       
        setImage(playerImg.getCurrentImage());
        
       
        
        
    }    
}
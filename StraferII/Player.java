import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math; 

public class Player extends Jucator
{
     HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage playerImg=directie.get(this.gif);
    
    static int direction;
    
    int moveTime = 7;
   
    
    boolean isMoving;
    static int originalX, originalY;
    public static int worldX, worldY;
    static int previousWorldX, previousWorldY;
    
    
    public static boolean inViata=true;
    public static int hp=400;
    public static final int hpMax=400;
    public  static int speed = 5;
    
    
    
    protected String gif="D";
    public static String gifSabie;
    public static String gifLaser;
    public static String gifPortalGun;
    public static HashSet<String> iteme = new HashSet<String>();

    protected long timpPrec;
    private long timp=0;
    private long timpSab=0,timpLaser=0,timpBoaba=0,timpPumni=0;
    
    public static boolean apas;
    
    
    public Player(){
          
        //sta
        directie.put("idle", new GifImage("player/player_m_Idle.gif"));
        //moarte
        directie.put("death",new GifImage("player/player_death.gif"));
        
        //merge
        directie.put("D",new GifImage("player/player_m_D.gif"));
        directie.put("W",new GifImage("player/player_m_W.gif"));
        directie.put("A",new GifImage("player/player_m_A.gif"));
        directie.put("S",new GifImage("player/player_m_S.gif"));
      
        //se uita
        directie.put("right",new GifImage("player/vedere_D.gif"));
        directie.put("up",new GifImage("player/vedere_W.gif"));
        directie.put("left",new GifImage("player/vedere_A.gif"));
        directie.put("down",new GifImage("player/vedere_S.gif"));
      
       // HealthBar bara=new HealthBar("Jucator","Viata",this.hp, this.hp);
       // getWorld().addObject(bara,0,0);
        
        
       this.timpPrec=System.currentTimeMillis();
    }
    
    protected void checkMove(){
        apas=false;
        //
        originalX = getX();
        originalY = getY();
        previousWorldX = worldX;
        previousWorldY = worldY;
      //
        
        if(Greenfoot.isKeyDown("w")){
            //merge in nord 
            apas=true;
            gif="W";
            setLocation(getX(), getY() - speed);
            worldY -= speed;
        }
        
        if(Greenfoot.isKeyDown("s")){
            //merge in sud   
            apas=true;
            gif="S";
            setLocation(getX(), getY() + speed);
            worldY += speed;
        
        }
        if(Greenfoot.isKeyDown("d")){
            //merg in est
            apas=true;
            gif="D";
            setLocation(getX()+speed,getY());
            worldX += speed;
        }
        
        if(Greenfoot.isKeyDown("a")){
            //merg in vest
            apas=true;
            gif="A";
            setLocation(getX()-speed,getY());
            worldX -= speed;
            }
        
        if(apas==false)
        {
            gif="idle";
        }
        
    }
    
    
    public void move(){
        
        //if(super.gif!="idle" && super.gif!="right" &&super.gif!="up" &&super.gif!="down" && super.gif!="left"){atingeNpc();}
        checkMove();
       // if(super.gif!="idle" && super.gif!="right" &&super.gif!="up" &&super.gif!="down" && super.gif!="left"){atingeNpc();}
      
        playerImg=directie.get(this.gif);
    }
    protected void vedere(){
   
        playerImg=directie.get(this.gif);
    }
    
    protected void lovit(){
       
    }
        
    
    public void act() {
       lovit();
       move();
       
        setImage(playerImg.getCurrentImage());    
    }   
    
    
    
}
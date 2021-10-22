import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashSet;
/**
 * Write a description of class Astroneer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Astroneer extends Jucator
{static int direction;
    
    int moveTime = 7;
   
    boolean isMoving;
    
    static int originalX, originalY;
    static int worldX, worldY;
    static int previousWorldX, previousWorldY;
    
    
    public static boolean inViata=true;
    public static int hp=400;
    public static final int hpMax=400;
    public  static int speed = 5;
    
    
    
    protected String gif="D";
    public static String gifSabie;
    public static String gifLaser;
    public static HashSet<String> iteme = new HashSet<String>();
    protected long timpPrec;
    private long timp=0;
    
    
    public static boolean apas;
 
    protected void move()
    {
        //
         originalX = getX();
        originalY = getY();
        previousWorldX = worldX;
        previousWorldY = worldY;
      //
        
        if(Greenfoot.isKeyDown("w"))
        {
        //merge in nord 
         gif="W";
         
         //
          setLocation(getX(), getY() - speed);
                worldY -= speed;
         //
         
        // setLocation(getX(),getY()-speed);
        }
        if(Greenfoot.isKeyDown("s"))
        {
         //merge in sud   
         gif="S";
        
         //
         setLocation(getX(), getY() + speed);
                worldY += speed;
         //
         
         //setLocation(getX(),getY()+speed);
        }
        if(Greenfoot.isKeyDown("d"))
        {
            //merg in est
            gif="D";
            //
            move(speed);
                worldX += speed;
            //
               // setLocation(getX()+speed,getY());
        }
        if(Greenfoot.isKeyDown("a"))
        {
            //merg in vest
            gif="A";
            move(-speed);
                worldX -= speed;
            //setLocation(getX()-speed,getY());
        }
        
        
        
    }
    
}
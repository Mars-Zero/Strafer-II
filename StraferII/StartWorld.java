import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public StartWorld()
    {    super(1280, 720, 1); 
        GreenfootImage background=new GreenfootImage("test/ankha.png");//imi pun fundalul
        setBackground(background);
        addObject(new Inamic(),200,200);
       
        
    }
}


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class Perete here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Perete extends WorldStructures{

    /**
     * Act - do whatever the Perete wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static String tip;
    HashMap<String, GreenfootImage> textura = new HashMap<String, GreenfootImage>();
    //GifImage playerImg=textura.get();
    public static boolean atinsPerete;

    public Perete() {

    }

    public boolean isInScreen() {
        if (this.getX() >= 0 && this.getX() <= 1024
                && this.getY() >= 0 && this.getY() <= 576) {
            return true;
        }
        return false;
    }

    public void act() {
        // Add your action code here.
    }
}

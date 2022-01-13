
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Friendly extends Npc {

    private PlayWorld playWorld;

    public Friendly(PlayWorld playWorldref, Scroller scrl,String dialogFile) {
        super(scrl);
        playWorld = playWorldref;
    }

    public void addDialogs() {
    }

    public PlayWorld getPlayWorld(){
        return playWorld;
    }
    
    public void act() {
        
    }
}

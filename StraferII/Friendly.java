
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

    public boolean nearPlayer(int range) {
        List players = getWorld().getObjects(Player.class);
        Player player = (Player) players.get(0);
        int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
        if (deltaPGX < 0) {
            deltaPGX *= (-1);
        }
        int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
        if (deltaPGY < 0) {
            deltaPGY *= (-1);
        }
        if (deltaPGX <= range && deltaPGY <= range) {
            return true;
        }
        return false;
    }

    public PlayWorld getPlayWorld(){
        return playWorld;
    }
    
    public void act() {
        
    }
}

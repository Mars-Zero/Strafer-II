
import greenfoot.*;

public class Stroke extends Inamic {

    Player player;
    int ochix,ochiy;

    
    public Stroke(Scroller scrl, int x, int y,Player player) {
        super(scrl, x, y);
        this.player=player;
        ochix=x-50;
        ochiy=y;

    }
    
    protected void addLasers(){
        getWorld().addObject(new LaserStroke(this,100),WorldData.menuX,WorldData.menuY);
    }

    public void act() {

    }

    
    
    public Player getPlayer() {
        return player;
    }
    public int getOchix() {
        return ochix;
    }
    public int getOchiy() {
        return ochiy;
    }
}

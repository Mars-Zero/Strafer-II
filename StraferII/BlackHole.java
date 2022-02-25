
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class BlackHole extends Item {

    GifImage gif = new GifImage("item/blackHole.gif");

    public BlackHole() {
        setImage(gif.getCurrentImage());
    }
 
    public void suck() {
        List npcs = getObjectsInRange(1000, Inamic.class);
        for (Object in : npcs) {
            Inamic inamic = (Inamic) in;
            if (!(inamic instanceof SchrodingersCat)) {
                if ((inamic instanceof Droid) || (inamic instanceof Goblin)) {
                    inamic.turnTowards(this.getX(), this.getY());
                    inamic.move(10);
                }
            }
        }

    }

    public void act() {

        if (!WorldData.PAUZA) {
            suck();
            setImage(gif.getCurrentImage());
        }

    }
}

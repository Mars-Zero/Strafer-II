
import greenfoot.*;
import java.util.List;

public class NpcItem extends Movers {

    protected void move(Actor npc) {
        setLocation(npc.getX(), npc.getY() );
    }
    
    protected void moveOffset(Actor npc, int offsetx, int offsety) {        //arunca un obiect
        setLocation(npc.getX() + offsetx, npc.getY() + offsety);
    }

    public void act() {

    }
}

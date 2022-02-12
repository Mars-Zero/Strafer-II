
import greenfoot.*;
import java.util.List;

public class LaserDroid extends NpcItem {

    public int damage = 8;

    private int speed = 6;
    boolean turned = false;
    Player player;

    GifImage laserImg = new GifImage("npc/inamic/droid/laserDroid.gif");

    public LaserDroid() {
        //  GreenfootSound sunet=new GreenfootSound("shootshoot.mp3");
        //sunet.play();
        

    }

    private void atac() {
        move(speed);
        setImage(laserImg.getCurrentImage());
        if (isAtEdge() || isTouching(Perete.class)) {
            getWorld().removeObject(this);
            return;
        }
        player = ((PlayWorld) getWorld()).getPlayer();
        if (!turned) {
            turnTowards(player.getX(), player.getY());
            turned = true;
        }
        if (isTouching(Player.class)) {
            player.takeDamage(damage);
            getWorld().removeObject(this);
        }

    }

    public void act() {
        if (!WorldData.PAUZA) {
            atac();
        }
    }

    public int getDamage() {
        return damage;
    }
}

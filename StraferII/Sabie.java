
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class Sabie extends Item {

    public static final int damage = 25;
    public static final int mass = 2;

    /**
     * HashMap containing all the GIFs for each direction
     */
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage sabieImg = directie.get(Item.itemGif);
    /**
     * Time variable for each frame
     */
    private long time = 0;
    /**
     * The erase time for each "Sabie" instance
     */

    Actor playerul;

    private final long constantEraseTime = 25;

    public Sabie(Player player) {
        playerul=player;
        directie.put("D", new GifImage("item/sabie_a_D.gif"));
        directie.put("W", new GifImage("item/sabie_a_W.gif"));
        directie.put("A", new GifImage("item/sabie_a_A.gif"));
        directie.put("S", new GifImage("item/sabie_a_S.gif"));

        sabieImg = directie.get("D");
        this.time = 0;

    }

    public Actor getPlayer() {
        return playerul;
    }

    protected void move() {
        super.move();

        sabieImg = directie.get(Item.itemGif);
    }

    protected void atac() {
        sabieImg = directie.get(Item.itemGif);
        //super.damage();
    }

    public void act() {

        if (!WorldData.PAUZA) {
            atac();
            move();

            time++;
            if (time > constantEraseTime) {

                getWorld().removeObject(this);
            }
            setImage(sabieImg.getCurrentImage());
        }
        
    }
}

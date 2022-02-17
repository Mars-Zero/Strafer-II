import greenfoot.*;  
import java.util.HashMap;


public class TaserDolpatian extends NpcItem{
   
       public static final int damage = 30;
    public static final int mass = 1;

   
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage sabieImg = directie.get(Item.itemGif);
 
    
    private long time = 0;

    Actor dolpatian;

    private final long constantEraseTime = 25;
    boolean gaveDamage=false;

    public TaserDolpatian(Dolpatian dolpatian) {
        this.dolpatian=dolpatian;
        directie.put("D", new GifImage("npc/inamic/dolpatian/taserDolpatian_D.gif"));
        directie.put("W", new GifImage("npc/inamic/dolpatian/taserDolpatian_W.gif"));
        directie.put("A", new GifImage("npc/inamic/dolpatian/taserDolpatian_A.gif"));
        directie.put("S", new GifImage("npc/inamic/dolpatian/taserDolpatian_S.gif"));

        sabieImg = directie.get("D");
        this.time = 0;

    }

    public Actor getGoblin() {
        return this.dolpatian;
    }

    protected void move() {
        super.move(this);

        sabieImg = directie.get(((Inamic)dolpatian).getGifItem());
    }

    protected void atac() {
        sabieImg = directie.get(((Inamic)dolpatian).getGifItem());
        if(isTouching(Player.class)){
           Player player= getWorld().getObjects(Player.class).get(0);
           if(player!=null){
               player.knockbacked=true;
                   player.knockback(0.1, dolpatian, this.mass, 80);
               if(!gaveDamage){
                   player.takeDamage(this.damage);
                   gaveDamage=true;
               }
            }
        }
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

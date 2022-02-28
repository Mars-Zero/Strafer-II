import greenfoot.*;  
import java.util.HashMap;

public class SabieGoblin extends NpcItem{
       public static final int damage = 5;
    public static final int mass = 2;

   
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage sabieImg = directie.get(Item.itemGif);
 
    
    private long time = 0;

    Actor goblin;

    private final long constantEraseTime = 25;
    boolean gaveDamage=false;

    public SabieGoblin(Goblin goblin) {
        this.goblin=goblin;
        directie.put("D", new GifImage("npc/inamic/goblin/sabie_goblin_D.gif"));
        directie.put("W", new GifImage("npc/inamic/goblin/sabie_goblin_W.gif"));
        directie.put("A", new GifImage("npc/inamic/goblin/sabie_goblin_A.gif"));
        directie.put("S", new GifImage("npc/inamic/goblin/sabie_goblin_S.gif"));

        sabieImg = directie.get("D");
         setImage(sabieImg.getCurrentImage());
        this.time = 0;

    }

    public Actor getGoblin() {
        return this.goblin;
    }

    protected void move() {
        super.move(this);

        sabieImg = directie.get(((Inamic)goblin).getGifItem());
    }

    protected void atac() {
        sabieImg = directie.get(((Inamic)goblin).getGifItem());
        if(isTouching(Player.class)){
           Player player= getWorld().getObjects(Player.class).get(0);
           if(player!=null){
               player.knockbacked=true;
                   player.knockback(0.1, goblin, this.mass, 80);
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
            setImage(sabieImg.getCurrentImage());
            time++;
            if (time > constantEraseTime) {
                ((Inamic)goblin).setUsedItem(false);
                getWorld().removeObject(this);
                
            }
            
           
            
        }
        
    }
}

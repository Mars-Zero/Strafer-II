import greenfoot.*;  


public class RumbleStroke extends NpcItem{
       public static final int damage = 5;
    public static final int mass = 2;
    GifImage gif=new GifImage("npc/inamic/stroke/rumble.gif");
    
    private long time = 0;

    Actor stroke;

    private final long constantEraseTime = 25;
    boolean gaveDamage=false;

    public RumbleStroke(Stroke stroke) {
        this.stroke=stroke;
        this.time = 0;

    }

    public Actor getStroke() {
        return this.stroke;
    }

    protected void move() {
        super.move(this);
    }

    protected void atac() {
        if(isTouching(Player.class)){
           Player player= getWorld().getObjects(Player.class).get(0);
           if(player!=null){
               player.knockbacked=true;
                   player.knockback(0.1, stroke, this.mass, 80);
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
                ((Inamic)stroke).setUsedItem(false);
                getWorld().removeObject(this);
                
            }
            setImage(gif.getCurrentImage());
        }
        
    }
}

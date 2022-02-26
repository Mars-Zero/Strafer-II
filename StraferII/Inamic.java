
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Inamic extends Npc {

    public static int speed = 5;
    protected int hp = 150;

    Scroller scroller;
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage npcImg = directie.get(super.gif);
    protected int pasi;
    protected int dist = 0;

    protected boolean atingePlayer = false;
    protected long timpSab = 0;
    protected long timpBolt = 0;
    protected long timpAtins = 0;
    protected boolean mort = false;
    protected long timpPrec;
    public boolean usedItem;
    public boolean freeze=false;



    protected long timpPauzaRange;
    protected long timpPauzaMelee;
    protected long nextRangeAttack;
    protected long nextMeleeAttack;

    public String gifItem;

    
  

    public Inamic(Scroller scrl, int x, int y) {
        super(scrl);
        prevsx = Scroller.scrolledX;
        prevsy = Scroller.scrolledY;
        worldX = x - Scroller.scrolledX;
        worldY = y - Scroller.scrolledY;

        this.mort = false;

        gif = "idle";

        this.timpSab = 0;
        this.timpBolt = 0;
        this.timpAtins = 0;

        this.timpPrec = System.currentTimeMillis();
    }

    protected void gaseste() {
        List players = getWorld().getObjects(Player.class);
        Player player = (Player) players.get(0);
        if (!players.isEmpty()) {

            int playerX = (player.getWorldX()) / super.rez;
            if (player.getWorldX() % super.rez > 0) {
                playerX++;
            }

            int playerY = (player.getWorldY()) / super.rez;
            if (player.getWorldY() % super.rez > 0) {
                playerY++;
            }

            int gY = (worldY + Scroller.scrolledY) / super.rez;
            if ((worldY + Scroller.scrolledY) % super.rez > 0) {

                gY++;
            }

            int gX = (worldX + Scroller.scrolledX) / super.rez;
            if ((worldX + Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }
            
            
            super.Lee(gY, gX, playerY, playerX);
        }

        if (super.gasit == true) {

            if (isTouching(Jucator.class) && (player.getWorldY() <= (worldY + Scroller.scrolledY) - 10 || player.getWorldX() <= (worldX + Scroller.scrolledX) - 15
                    || (player.getWorldY()) >= (worldY + Scroller.scrolledY) + 10 || player.getWorldX() >= (worldX + Scroller.scrolledX) + 15)) {
                gif = "idle";

            } else {
                move();
            }
        } else {
            gif = "idle";
        }
        npcImg = directie.get(super.gif);

    }

    protected void move() {
        pasi = super.ord.size() - 1;
        if (pasi > 0) {

            String directie = super.ord.get(pasi).toString();
            int gY = (worldY + Scroller.scrolledY) / super.rez;
            if ((worldY + Scroller.scrolledY) % super.rez > 0) {
                gY++;
            }
            int gX = (worldX + Scroller.scrolledX) / super.rez;
            if ((worldX + Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }
            if (super.matElem[gY][gX] != -1) {
                super.matElem[gY][gX] = 0;
            }
            int difpx = Scroller.scrolledX - prevsx;
            int difpy = Scroller.scrolledY - prevsy;

            worldX -= difpx;
            worldY -= difpy;

            switch (directie) {
                case "W": {

                    gif = "W";
                    dist += speed;

                    worldY -= (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "D": {

                    gif = "D";
                    dist += speed;

                    worldX += (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "S": {

                    gif = "S";
                    dist += speed;

                    worldY += (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "A": {

                    gif = "A";
                    dist += speed;

                    worldX -= (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "WD": {
                    gif = "D";
                    dist += speed;

                    worldX += (speed);
                    worldY -= (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "WA": {
                    gif = "A";
                    dist += speed;

                    worldX -= (speed);
                    worldY -= (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "SD": {
                    gif = "D";
                    dist += speed;

                    worldY += (speed);
                    worldX += (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }
                case "SA": {
                    gif = "A";
                    dist += speed;

                    worldX -= (speed);
                    worldY += (speed);
                    super.setLocation(worldX, worldY);
                    gifItem = gif;

                    break;
                }

            }
            prevsx = Scroller.scrolledX;
            prevsy = Scroller.scrolledY;
            
            if (dist >= 10) {
                dist = 0;
                pasi--;
            }
            
            gY = (worldY + Scroller.scrolledY) / super.rez;
            if ((worldY + Scroller.scrolledY) % super.rez > 0) {
                gY++;
            }
            gX = (worldX + Scroller.scrolledX) / super.rez;
            if ((worldX + Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }

            if(super.matElem[gY][gX]!=-1){super.matElem[gY][gX]=-2;}
            //
        }
    }

   int blinks=0;
    protected void knockbackMove() {//boolean knockbacked) {

        if (knockbacked) {
            this.frameuri_trecute++;
            blinks++;
            if(this.frameuri_trecute%2==0&& blinks<5){
                getImage().setTransparency(80);
                
            }
            else{
                getImage().setTransparency(80);
            }
            setLocation((int) (getX() + viteza_frame_x * Math.cos(grade_rezultanta)), (int) (getY() + viteza_frame_y * Math.sin(grade_rezultanta)));
            worldX += viteza_frame_x * Math.cos(grade_rezultanta);
            worldY += viteza_frame_y * Math.sin(grade_rezultanta);
            

            if (this.frameuri_trecute >= this.timp_knockback * 60) {
                this.frameuri_trecute = 0;
                 getImage().setTransparency(100);
                knockbacked = false;
            }
        }
        else{
        getImage().setTransparency(255);
        }
    }

    protected void lovitSabie(int masa) {

        List players = getWorld().getObjects(Player.class);
        Player player = (Player) players.get(0);
        List sabii = getWorld().getObjects(Sabie.class);
        if (!sabii.isEmpty()) {
            Sabie sabia = (Sabie) sabii.get(0);

            int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
            if (deltaPGX < 0) {
                deltaPGX *= (-1);
            }
            int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
            if (deltaPGY < 0) {
                deltaPGY *= (-1);
            }

            if (player.isEquipSword() == true && deltaPGX <= 85 && deltaPGY <= 100) {
                knockbacked = true;
                super.knockback(0.1, sabia.getPlayer(), Sabie.mass, masa);
            }
        }

  
    }

    protected void lovitLaser() {

        Actor a = (Laser) getOneIntersectingObject(Laser.class);
        if (a != null) {
            timpBolt++;//cat timp ating ating laserul
            if (timpBolt >= 8) {
                removeTouching(Laser.class);

                health(Laser.damage);
                if (!(traiesc())) {
                    super.gif = "death";
                    npcImg = directie.get(super.gif);
                    mort = true;
                }
                timpBolt = 0;
            }
        } else if (timpBolt > 0) {
            timpBolt = 0;
        }
    }

    protected void atac() {
        usedItem=true;
    }

    protected void attackRange() {

        //aici ar fi bine sa se verifice cu o metoda daca e in range
        if (nextRangeAttack > timpPauzaRange) {

            //getWorld().addObject(new Laser(),getX(),getY());
            nextRangeAttack = System.currentTimeMillis();

        }
        nextRangeAttack = System.currentTimeMillis();
    }

    protected void attackMelee() {

        //aici ar fi bine sa se verifice cu o metoda daca e in range
        if (nextMeleeAttack > timpPauzaMelee) {

            //getWorld().addObject(new Laser(),getX(),getY());
            nextMeleeAttack = System.currentTimeMillis();
            if (gifItem == "D") {
                //getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX)+36,(getY()+scrolledY));
            } else if (gifItem == "A") {
                //getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX)-36,(getY()+scrolledY));
            } else if (gifItem == "S") {
                //getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX),(getY()+scrolledY)+20);
            } else if (gifItem == "W") {
                //getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX),(getY()+scrolledY)-20);
            }

        }
        nextMeleeAttack = System.currentTimeMillis();
    }
    long timpMort = 0;

    protected void health(int dmg) {
        hp -= dmg;
    }

    protected boolean traiesc() {
        return hp > 0;
    }
    
    
    
    
    
      public long getpX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public long getpY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public String getGifItem() {
        return gifItem;
    }
    
    public boolean isUsedItem() {
        return usedItem;
    }

    public void setUsedItem(boolean usedItem) {
        this.usedItem = usedItem;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
    
    /**
     * This method spawns a pill that heals the player a random amount 
     */
    protected void generateRandomHealthBoost(){
        int chance = Greenfoot.getRandomNumber(6);
        if(chance==0){
            //add the healthBoost
            int currentHp=getWorld().getObjects(Player.class).get(0).getHp();
            int possibleHealth=Player.hpMax-currentHp;
            int alwaysAdd=possibleHealth*40/100;
            chance=Greenfoot.getRandomNumber(10);
            if(chance==0){
                alwaysAdd=possibleHealth;
            }
            getWorld().addObject(new Pill(alwaysAdd),getX(),getY());
        }
    }
}

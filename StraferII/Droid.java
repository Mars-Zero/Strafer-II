
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Droid extends Inamic {

    private String axa;
    private int dist;
    private int distParc;
    private boolean sensPoz = true;
    int gX, gY;
    GifImage img = new GifImage("npc/inamic/droid/droid.gif");

    public static int speed = 3;
    private int hp = 75;
    private int mass = 60;
    private final int pauza = 50;

    private long timpLaser = 0;
    private boolean mort = false;
    Animation animation;
    boolean startedAnimation = false;
    int cntDeath = 0;

    private long timpSab = 0;
    private long timpBolt = 0;

    public String gifLaser;

    public Droid(Scroller scrl, int x, int y, String xy, int dist) {
        super(scrl, x, y);
        this.dist = dist;
        distParc = 0;
        this.axa = xy;
        gX = x;
        gY = y;
        sensPoz = true;

        changeAnimation();
        animation.setActiveState(false);
    }

    protected void gaseste() {
        List players = getWorld().getObjects(Player.class);
        if (!players.isEmpty()) {
            Actor player = (Actor) players.get(0);
            turnTowards(player.getX(), player.getY());
        }

    }

    protected void move() {
        lovitSabie();
        lovitLaser();
        if (axa.equals("ox")) {
            int difpx = Scroller.scrolledX - prevsx;
            int difpy = Scroller.scrolledY - prevsy;

            worldX -= difpx;
            worldY -= difpy;
            super.move();

            if (sensPoz == true) {
                //sens pozitiv ,cresc X
                worldX += speed;
                distParc += speed;
                if (dist - distParc <= 0) {
                    sensPoz = false;
                    distParc = 0;
                }
            } else {
                worldX -= speed;
                distParc += speed;
                if (dist - distParc <= 0) {
                    sensPoz = true;
                    distParc = 0;
                }
            }

        } else if (axa.equals("oy")) {

            if (sensPoz == true) {
                //sens pozitiv ,cresc Y
                worldY += speed;
                distParc += speed;
                if (dist - distParc <= 0) {
                    sensPoz = false;
                    distParc = 0;
                }
            } else {
                worldY -= speed;
                distParc += speed;
                if (dist - distParc <= 0) {
                    sensPoz = true;
                    distParc = 0;
                }
            }

        }
        setLocation(worldX, worldY);
        if (timpLaser > pauza) {

            getWorld().addObject(new LaserDroid(), getX(), getY());
            timpLaser = 0;

        }
        timpLaser++;

    }

    protected void lovitSabie() {
        super.lovitSabie(this.mass);
        if (isTouching(Sabie.class)) {
            timpSab++;
            if (timpSab >= 15) {
                takeDamage(Sabie.damage);
                timpSab = 0;
            }

        }

    }

    protected void lovitLaser() {
        super.lovitLaser();
        Actor a = (Laser) getOneIntersectingObject(Laser.class);
        if (a != null) {
            timpBolt++;//cat timp ating ating laserul
            if (timpBolt >= 5) {
                removeTouching(Laser.class);
                takeDamage(Laser.damage);
                timpBolt = 0;
            }
        }
    }
  private void suckedBlackHole(){
        if(isTouching(BlackHole.class)){
            takeDamage(10);
        }
    }
    private void takeDamage(int dmg) {
        hp -= dmg;
    }

    private void die() {
        if (hp <= 0) {
            mort = true;
        }
    }

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true && !freeze) {

            if (mort == true) {
                cntDeath++;
                if (cntDeath > 2) {
                    if (!startedAnimation) {
                        animation.setActiveState(true);
                        startedAnimation = true;
                    }
                }
                if (animation.isActive()) {
                    animation.run();
                }
                if (startedAnimation && !animation.isActive()) {
                    getWorld().removeObject(this);
                }
            } else {

                lovitSabie();
                lovitLaser();
                suckedBlackHole();
                die();

                timpAtins = 0;//{
                atingePlayer = true;//ataca

                //daca e in range sa nu l caute in toata lumea
                List players = getWorld().getObjects(Player.class);
                Player player = (Player) players.get(0);
                
                move();
                
                int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
                if (deltaPGX < 0) {
                    deltaPGX *= (-1);
                }
                int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
                if (deltaPGY < 0) {
                    deltaPGY *= (-1);
                }
                if (deltaPGX <= 600 && deltaPGY <= 400) {
                    //aici intra functia de move 
                    gaseste();
                    
                }
                int difpx = Scroller.scrolledX - prevsx;
                int difpy = Scroller.scrolledY - prevsy;

                worldX -= difpx;
                worldY -= difpy;
                prevsx = Scroller.scrolledX;
                prevsy = Scroller.scrolledY;

                this.knockbackMove();
            }
            if (animation.isActive()) {
                animation.run();
            } else {
                setImage(img.getCurrentImage());
            }
        }

    }

    private void changeAnimation() {
        java.util.List<GreenfootImage> imgs = new GifImage("npc/inamic/droid/droid_death.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        animation = new Animation(this, images);
        animation.setCycleActs(20);
        animation.setCycleCount(1);
        animation.setScalar(5);
        animation.run();
        animation.setActiveState(true);
    }
 public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
}

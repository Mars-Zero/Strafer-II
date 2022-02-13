
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
    private int mass=60;
    private final int pauza = 50;

    private long timpLaser = 0;
    private boolean mort = false;
    private long timpPrec;

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
            if (timpSab >= 16) {
                takeDamage(Sabie.damage);
                
                if (!(traiesc())) {
                    mort = true;
                }
                timpSab = 0;
            }

        } else if (timpSab > 0) {
            timpSab--;//modific timpul daca nu 
        }

    }

    protected void lovitLaser() {
        super.lovitLaser();
        Actor a = (Laser) getOneIntersectingObject(Laser.class);
        if (a != null) {
            timpBolt++;//cat timp ating ating laserul
            if (timpBolt >= 8) {
                removeTouching(Laser.class);

                takeDamage(Laser.damage);
                if (!(traiesc())) {
                    //playerImg = new GifImage("droid_death.gif");
                    mort = true;
                }
                timpBolt = 0;
            }
        } else if (timpBolt > 0) {
            timpBolt = 0;
        }
    }

    private void takeDamage(int dmg) {
        hp -= dmg;
    }

    private void die(){
        if(hp<=0){
            mort=true;
        }
    }
    
    long timpMort = 0;

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

            if (mort == true) {
                //moare
                //playAnimation
                if (timpMort >= super.rez + 45) {

                    getWorld().removeObject(this);//dispare
                    return;
                }
                timpMort++;
            } else {

                lovitSabie();
                lovitLaser();
                die();
                
                timpAtins = 0;//{
                atingePlayer = true;//ataca

                //daca e in range sa nu l caute in toata lumea
                List players = getWorld().getObjects(Player.class);
                Player player = (Player) players.get(0);
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
                    move();
                }
                int difpx = Scroller.scrolledX - prevsx;
                int difpy = Scroller.scrolledY - prevsy;

                worldX -= difpx;
                worldY -= difpy;
                prevsx = Scroller.scrolledX;
                prevsy = Scroller.scrolledY;

                this.knockbackMove();
            }

            setImage(img.getCurrentImage());
        }

    }
}


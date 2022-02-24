
import greenfoot.*;
import java.util.List;

public class Dolpatian extends Goblin {

    boolean eVizibil = false;
    public static int mass = 100;
    int hp = 75;

    private long timpSab = 0;
    private long timpBolt = 0;
    
    Animation animation;
    boolean startedAnimation = false;
    int cntDeath = 0;

    public Dolpatian(Scroller scrl, int x, int y) {
        super(scrl, x, y);

        eVizibil = false;
        //am gif-urile schimbate
        directie.put("D", new GifImage("npc/inamic/dolpatian/dolpatian_m_D.gif"));
        directie.put("W", new GifImage("npc/inamic/dolpatian/dolpatian_m_W.gif"));
        directie.put("A", new GifImage("npc/inamic/dolpatian/dolpatian_m_A.gif"));
        directie.put("S", new GifImage("npc/inamic/dolpatian/dolpatian_m_S.gif"));
        directie.put("idle", new GifImage("npc/inamic/dolpatian/dolpatian_m_S.gif"));
        directie.put("inviz", new GifImage("npc/inamic/dolpatian/dolpatian_m_Idle.gif"));

        changeAnimation();
        animation.setActiveState(false);
    }

    long timpVizibilPrec = 0;

    void atinsLight() {
        if (isTouching(Light.class)) {
            eVizibil = true;
            timpVizibilPrec = System.currentTimeMillis();
        } else {
            if (eVizibil) {
                long timpCurent = System.currentTimeMillis();
                if (timpCurent - timpPrec >= 9950) {
                    eVizibil = false;
                }
            }
        }
    }

    void atingPlayer() {
        List l = getObjectsInRange(40, Player.class);
        if (l.size() > 0) {
            timpVizibilPrec = System.currentTimeMillis();
            npcImg = directie.get(super.gif);
        }
    }

    protected void atac() {
        if (!usedItem) {
            getWorld().addObject(new TaserDolpatian(this), getX(), getY());
        }
        usedItem=true;
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

            gif = "idle";

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
                atinsLight();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atac();///////{

                    gif = "idle";
                    eVizibil = true;

                    atingPlayer();
                } else {

                    long wait = Greenfoot.getRandomNumber(20) + 30 + waitseed;//{
                    wait = 0;

                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= wait) ///////////////////////ia o pauza
                        {
                            atingePlayer = false;
                            usedItem = false;

                        }
                    }///////////////////////////////////////////////{
                    else {
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

                            gaseste();//cauta playerul

                            if (!eVizibil) {
                                gif = "inviz";
                            }
                        }
                        atingPlayer();
                        int difpx = Scroller.scrolledX - prevsx;
                        int difpy = Scroller.scrolledY - prevsy;

                        worldX -= difpx;
                        worldY -= difpy;
                        prevsx = Scroller.scrolledX;
                        prevsy = Scroller.scrolledY;
                    }

                }
                this.knockbackMove();
                die();
            }

            if (animation.isActive()) {
                animation.run();
            } else {
                npcImg = directie.get(gif);
                setImage(npcImg.getCurrentImage());
            }
        }

    }

    private void changeAnimation() {
        java.util.List<GreenfootImage> imgs = new GifImage("npc/inamic/dolpatian/dolpatian_death.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        animation = new Animation(this, images);
        animation.setCycleActs(22);
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

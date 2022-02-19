
import greenfoot.*;
import java.util.List;

public class Stroke extends Inamic {

    Player player;
    int ochix, ochiy;

    public static int speed = 6;

    int hp = 10000;

    private long timpSab = 0;
    private long timpBolt = 0;

    Animation animation;
    boolean startedAnimation = false;
    int cntDeath = 0;

    public Stroke(Scroller scrl, int x, int y, Player player) {
        super(scrl, x, y);
        this.player = player;

        directie.put("D", new GifImage("npc/inamic/stroke/stroke_m.gif"));
        directie.put("W", new GifImage("npc/inamic/stroke/stroke_m.gif"));
        directie.put("A", new GifImage("npc/inamic/stroke/stroke_m.gif"));
        directie.put("S", new GifImage("npc/inamic/stroke/stroke_m.gif"));
        directie.put("idle", new GifImage("npc/inamic/stroke/stroke_idle.gif"));

        changeAnimation();
        animation.setActiveState(false);

    }

    protected void atac() {
        super.atac();
    }

    protected void addLasers() {
        ochix = getX() - 50;
        ochiy = getY() - 35;
        getWorld().addObject(new LaserStroke(this, 100), WorldData.menuX, WorldData.menuY);
        super.atac();
    }

    protected void atacLaser() {
        if (!usedItem) {
            addLasers();
        }

    }
    
    protected void atacMelee(){
        //quierres
        super.atac();
    }

     protected void lovitSabie() {
        if (isTouching(Sabie.class)) {
            timpSab++;
            if (timpSab >= 6) {
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

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

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
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atacMelee();///////{trebuie dat overload la atac

                    gif = "idle";

                    npcImg = directie.get(super.gif);

                } else {

                    long wait = Greenfoot.getRandomNumber(20) + 30 + waitseed;//{
                    wait = 0;

                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= wait) ///////////////////////ia o pauza
                        {
                            atingePlayer = false;
                            usedItem = false;
                            //randomize

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
                        }
                        int difpx = Scroller.scrolledX - prevsx;
                        int difpy = Scroller.scrolledY - prevsy;

                        worldX -= difpx;
                        worldY -= difpy;
                        prevsx = Scroller.scrolledX;
                        prevsy = Scroller.scrolledY;
                    }

                    die();
                }
            }
            if (animation.isActive()) {
                animation.run();
            } else {
                npcImg = directie.get(gif);
                setImage(npcImg.getCurrentImage());
            }
        }

    }

    public Player getPlayer() {
        return player;
    }

    public int getOchix() {
        return ochix;
    }

    public int getOchiy() {
        return ochiy;
    }

    private void changeAnimation() {
        java.util.List<GreenfootImage> imgs = new GifImage("npc/inamic/goblin/goblin_death.gif").getImages();
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

}

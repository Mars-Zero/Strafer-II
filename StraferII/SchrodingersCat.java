
import greenfoot.*;
import java.util.List;

public class SchrodingersCat extends Goblin {

    Animation animation;  ///enter // exit // explode
    boolean changedAnimation;
    String animationName;

    boolean isExploding;

    public SchrodingersCat(Scroller scrl, int x, int y) {
        super(scrl, x, y);

        directie.put("D", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("W", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("A", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("S", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("idle", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m_idle.gif"));

        changeAnimation("Exit", 10, 60);
        animation.setActiveState(false);
        changedAnimation = false;

    }

    private void closeBox() {

    }

    private void openBox() {
        int seed = Greenfoot.getRandomNumber(1);

        switch (seed) {
            case 0: {
                explode();
                break;
            }
            case 1: {
                changeAnimation("Exit", 10, 5);
                break;
            }
        }

    }

    private void explode() {
        changeAnimation("Explode", 16, 6);

    }

    private void checkExplode() {
        if (animationName == "Explode") {
            if (!animation.isActive()) {
                getWorld().removeObject(this);
            }
        }
    }

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

            gif = "idle";

            if (mort == true) {
                //moare

            } else {

                lovitSabie();
                lovitLaser();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    if (!changedAnimation) {

                        openBox();
                        changedAnimation = true;
                    }
                    gif = "idle";

                    //atingPlayer();
                } else {

                    long wait = Greenfoot.getRandomNumber(20) + 90 + waitseed;//{

                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= wait) ///////////////////////ia o pauza
                        {

                            atingePlayer = false;
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

                            //if (!eVizibil) {
                            //gif = "inviz";
                            //}
                        }

                        //atingPlayer();
                        int difpx = Scroller.scrolledX - prevsx;
                        int difpy = Scroller.scrolledY - prevsy;

                        worldX -= difpx;
                        worldY -= difpy;
                        prevsx = Scroller.scrolledX;
                        prevsy = Scroller.scrolledY;
                    }

                }

                this.knockbackMove();
            }
            checkExplode();
            if (animation.isActive()) {
                animation.run();

            } else {
                catImg = directie.get(gif);
                setImage(catImg.getCurrentImage());
            }
        }
    }

    private void changeAnimation(String anim, int nrframeuri, int scalar) {

        animationName = anim;
        java.util.List<GreenfootImage> imgs = new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_" + anim + ".gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        animation = new Animation(this, images);
        animation.setCycleActs(nrframeuri);
        animation.setCycleCount(1);
        animation.setScalar(scalar);
        animation.run();
        animation.setActiveState(true);
    }

}

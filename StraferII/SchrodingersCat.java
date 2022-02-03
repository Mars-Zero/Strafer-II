
import greenfoot.*;
import java.util.List;

public class SchrodingersCat extends Goblin {

    Animation animation;  ///enter // exit // explode
    boolean changedAnimation;
    String animationName;
    
    public SchrodingersCat(Scroller scrl, int x, int y) {
        super(scrl, x, y);
        
        directie.put("D", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("W", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("A", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("S", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m.gif"));
        directie.put("idle", new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_m_idle.gif"));
        
        changeAnimation("Exit",12);
        animation.setActiveState(false);
        changedAnimation=false;
        
    }
    
    
    private void openBox(){
        
    }
    

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

            gif = "idle";
            System.out.println(mort);
            System.out.println(hp);
            if (mort == true) {
                //moare

            } else {

                lovitSabie();
                lovitLaser();
                //atinsLight();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    super.atac();///////{

                    gif = "idle";
                    //eVizibil = true;

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

                            if(!changedAnimation){
                            changeAnimation("Explode",20);
                            
                            }
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

            if (!animation.isActive()) {
                catImg = directie.get(gif);
                System.out.println(gif);
                System.out.println(catImg);
                setImage(catImg.getCurrentImage());
            }
            else{
                animation.run();
            }
        }
    }

    private void changeAnimation(String anim, int nrframeuri) {
        changedAnimation=true;
        
        animationName=anim;
        java.util.List<GreenfootImage> imgs = new GifImage("npc/inamic/schrodinger's cat/schrodinger'sCat_"+anim+".gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        animation = new Animation(this, images);
        animation.setCycleActs(nrframeuri);
        animation.run();
        animation.setActiveState(true);
    }

   
}

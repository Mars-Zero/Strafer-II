
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Goblin extends Inamic {

    public static int speed = 5;
    public static int mass = 80;

    public Goblin(Scroller scrl, int x, int y) {
        super(scrl, x, y);

        directie.put("D", new GifImage("npc/inamic/goblin/goblin_m_D.gif"));
        directie.put("W", new GifImage("npc/inamic/goblin/goblin_m_W.gif"));
        directie.put("A", new GifImage("npc/inamic/goblin/goblin_m_A.gif"));
        directie.put("S", new GifImage("npc/inamic/goblin/goblin_m_S.gif"));
        directie.put("idle", new GifImage("npc/inamic/goblin/goblin_m_Idle.gif"));

        directie.put("death", new GifImage("npc/inamic/goblin/goblin_death.gif"));

    }

    protected void atac() {
        if (!usedItem) {
            getWorld().addObject(new SabieGoblin(this), getX(), getY());
        }
        super.atac();
    }

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

            gif = "idle";

            if (mort == true) {
                //moare

            } else {

                lovitSabie(this.mass);
                lovitLaser();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {
                 

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atac();///////{trebuie dat overload la atac

                    gif = "idle";
                    
                    npcImg = directie.get(super.gif);

                } else {
                    //lovitSabie();
                    //lovitLaser();

                    long wait = Greenfoot.getRandomNumber(20) + 30 + waitseed;//{
                    wait = 0;

                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= wait) ///////////////////////ia o pauza
                        {
                            atingePlayer = false;
                            usedItem=false;

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

                }
                this.knockbackMove();
            }

            npcImg = directie.get(gif);

            setImage(npcImg.getCurrentImage());
        }

    }

}

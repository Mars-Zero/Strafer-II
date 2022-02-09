import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Spiker extends Inamic
{
    public Spiker(Scroller scrl, int x, int y) {
        super(scrl,x,y);
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
                    //worldX=getX();
                    //worldY=getY();

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atac();///////{trebuie dat overload la atac

                    gif = "idle";
                    //lovitSabie();//{
                    // lovitLaser();//verifica daca e lovit
                    //{
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

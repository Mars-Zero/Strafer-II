import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Dolpatian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dolpatian extends Goblin
{
    boolean eVizibil=false;
    
    
    public Dolpatian(Scroller scrl, int x, int y) {
          super(scrl,x,y);
          
          eVizibil=false;
          //am gif-urile schimbate
        directie.put("D", new GifImage("npc/inamic/dolpatian/dolpatian_m_D.gif"));
        directie.put("W", new GifImage("npc/inamic/dolpatian/dolpatian_m_W.gif"));
        directie.put("A", new GifImage("npc/inamic/dolpatian/dolpatian_m_A.gif"));
        directie.put("S", new GifImage("npc/inamic/dolpatian/dolpatian_m_S.gif"));
        directie.put("idle", new GifImage("npc/inamic/dolpatian/dolpatian_m_S.gif"));
        directie.put("inviz", new GifImage("npc/inamic/dolpatian/dolpatian_m_Idle.gif"));

      //  directie.put("death", new GifImage("npc/inamic/goblin/goblin_death.gif"));
    }
    
    long timpVizibilPrec=0;
    void atinsLight(){
        if(isTouching(Light.class)){
            eVizibil=true;
            timpVizibilPrec=System.currentTimeMillis();
        }
        else{
            if(eVizibil){
                long timpCurent = System.currentTimeMillis();
                if (timpCurent - timpPrec >= 9950) {
                      eVizibil=false;
                }
            }
        }
    }
    
    void atingPlayer()
    {
        List l = getObjectsInRange(40, Player.class);
        if(l.size()>0)
        {
            timpVizibilPrec = System.currentTimeMillis();
             catImg = directie.get(super.gif);
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
                atinsLight();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    super.atac();///////{

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
            }

            catImg = directie.get(gif);
            System.out.println(gif);
            System.out.println(catImg);
                   setImage(catImg.getCurrentImage());
        }

    }
}

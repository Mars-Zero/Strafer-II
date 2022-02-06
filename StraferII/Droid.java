
import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Droid extends Inamic {
    
    private String axa;
    private int dist;
    private boolean sensPoz=true;
    public Droid(Scroller scrl, int x, int y,String xy, int dist) {
        super(scrl, x, y);
        this.dist=dist;
        this.axa=xy;
        sensPoz=true;
    }
    
    
    
    protected void move(){
        lovitSabie();
        lovitLaser();
        if(axa.equals("ox")){
            
            
            if(sensPoz==true){
                //sens pozitiv ,cresc X
                //setLocation(getX()+speed,getY());
                /*if(getX()-gX>dist){
                    sensPoz=false;
                }*/
            }
            else{
                //setLocation(getX()-speed,getY());
                /*if(gX-getX()>dist){
                    sensPoz=true;
                }*/
            }
           
         }
        else if(axa.equals("oy")){
        
            if(sensPoz==true){
                //sens pozitiv ,cresc Y
                //setLocation(getX(),getY()+speed);
                /*if(getY()-gY>dist){
                    sensPoz=false;
                }*/
            }
            else{
                //setLocation(getX(),getY()-speed);
                /*if(gY-getY()>dist){
                    sensPoz=true;
                }*/
            }
            
            
        
        }
        
        
    }  
    protected void atac(){
        //attackRange();
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
                long waitseed = Greenfoot.getRandomNumber(2500);
                atac();
                if (isTouching(Jucator.class)) {
                    //worldX=getX();
                    //worldY=getY();

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    

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
                            //aici intra functia de move
                            move();
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

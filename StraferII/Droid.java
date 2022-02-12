
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
        distParc=0;
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
                distParc+=speed;
                if (dist-distParc<=0) {
                    sensPoz = false;
                    distParc=0;
                }
            } else {
                worldX -= speed;
                distParc+=speed;
                if (dist-distParc<=0) {
                    sensPoz = true;
                    distParc=0;
                }
            }

        } else if (axa.equals("oy")) {

            if (sensPoz == true) {
                //sens pozitiv ,cresc Y
                worldY += speed;
                distParc+=speed;
                if (dist-distParc<=0) {
                    sensPoz = false;
                    distParc=0;
                }
            } else {
                 worldY-=speed;
                 distParc+=speed;
                if (dist-distParc<=0) {
                    sensPoz = true;
                    distParc=0;
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

        if (isTouching(Sabie.class)) {
            timpSab++;//cat timp ating ating asteroidul
            if (timpSab >= 16) {
                takeDamage(Sabie.damage);
                if (!(traiesc())) {
                    //playerImg = new GifImage("droid_death.gif");
                    mort = true;
                }
                timpSab = 0;
            }

        } else if (timpSab > 0) {
            timpSab--;//modific timpul daca nu 
        }

    }

    protected void lovitLaser() {

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

    public void atac() {
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
                long waitseed = Greenfoot.getRandomNumber(2500);
                atac();
                //worldX=getX();
                //worldY=getY();

                timpAtins = 0;//{
                atingePlayer = true;//ataca

                //lovitSabie();
                //lovitLaser();
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

/*

public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

          
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

                    //lovitSabie();//{
                    // lovitLaser();//verifica daca e lovit
                    //{
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

            setImage(img.getCurrentImage());
        }

    }


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Droid extends Npc{
   
    public static int speed=3;
    private int hp=75;
    private final int pauza=50;
    
    
    GifImage playerImg=new GifImage("droid.gif");
    
    private long timpLaser=0;
    private boolean mort=false;
    private long timpPrec;
    
    private long timpSab=0;
    private long timpBolt=0;
    
    public String gifLaser;
     private String axa;
     private int dist;
     private int gX,gY;
     private boolean sensPoz=true;
     private boolean pozInit=true;
     public Droid(String xy, int dist){
        
         this.mort=false;
        
        this.dist=dist;
        this.axa=xy;
        this.sensPoz=true;
        this.pozInit=true;
        
         this.timpSab=0;
        this.timpBolt=0;
        
        
       
        
        
        this.timpLaser=0;
 
        

        }
     
    private void gaseste(){
        List players = getWorld().getObjects(Player.class);
        if (!players.isEmpty())
        {
            Actor player = (Actor)players.get(0);
            turnTowards(player.getX(), player.getY());
        }
       
      
    }
    
    private void move(){
        lovitSabie();
            lovitLaser();
        if(axa.equals("ox")){
            
            
            if(sensPoz==true){
                //sens pozitiv ,cresc X
                setLocation(getX()+speed,getY());
                if(getX()-gX>dist){
                    sensPoz=false;
                }
            }
            else{
                setLocation(getX()-speed,getY());
                if(gX-getX()>dist){
                    sensPoz=true;
                }
            }
           
         }
        else if(axa.equals("oy")){
        
            if(sensPoz==true){
                //sens pozitiv ,cresc Y
                setLocation(getX(),getY()+speed);
                if(getY()-gY>dist){
                    sensPoz=false;
                }
            }
            else{
                setLocation(getX(),getY()-speed);
                if(gY-getY()>dist){
                    sensPoz=true;
                }
            }
            
            
        
        }
        if(timpLaser>pauza){
            
            getWorld().addObject(new LaserDroid(),getX(),getY());
            timpLaser=0;
        
        }
        timpLaser++;
        
    }
    
    protected void lovitSabie(){
        
         
        if(isTouching(Sabie.class)){
            timpSab++;//cat timp ating ating asteroidul
            if(timpSab>=16)
            {
               health(Sabie.damage);
               if(!(traiesc()))
               {
                   playerImg=new GifImage("droid_death.gif");
                   mort=true;
                }
               timpSab=0;
            }
            
        }
        else if(timpSab>0)
        {
            timpSab--;//modific timpul daca nu 
        }
    
    
    }
    protected void lovitLaser(){
        
        Actor a=(Laser)getOneIntersectingObject(Laser.class);
        if(a!=null){
            timpBolt++;//cat timp ating ating laserul
            if(timpBolt>=8)
            {
                removeTouching(Laser.class);
               
               health(Laser.damage);
               if(!(traiesc()))
               {
                    playerImg=new GifImage("droid_death.gif");
                   mort=true;
                }
               timpBolt=0;
            }
        }
        else if(timpBolt>0){
            timpBolt=0;
        }
    }
    private void health(int dmg){
        hp-=dmg;
    }
    private boolean traiesc(){
        return hp>0;
    }
    public void atac(){}
    long timpMort=0;
    public void act() 
    {
        
        
        
         if(mort==true)
        {
           //moare
            
            //super.gif="death"; 
            //playerImg=directie.get(super.gif);
            
        
            setImage(playerImg.getCurrentImage());
            if(timpMort>=super.rez+45)
            {
                
                World lume=getWorld();
                if(lume instanceof Nivel2_4){
                    Nivel2_4.inamici--;
                }
                if(lume instanceof Nivel2_5){
                    Nivel2_5.inamici--;
                }
                if(lume instanceof Nivel4_2){
                    Nivel4_2.inamici--;
                }
                getWorld().removeObject(this);//dispare
                return;
            }
           timpMort++;

           
        }
        else{
            lovitSabie();
            lovitLaser();
        if(pozInit==true){
            pozInit=false;
            gY=getY();
            gX=getX();
        }
       setImage(playerImg.getCurrentImage());
        gaseste();
        move();
        
        }
        
        // Add your action code here.
    }    
}
 */

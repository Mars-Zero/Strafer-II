
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Goblin extends Npc {

    public static int speed = 5;
    private int hp = 150;

    Scroller scroller;
    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage playerImg = directie.get(super.gif);
    private int pasi;
    private int dist = 0;

    private boolean atingePlayer = false;
    private long timpSab = 0;
    private long timpBolt = 0;
    private long timpAtins = 0;
    private boolean mort = false;
    private long timpPrec;
    public String gifSabie;

  
    public long getpX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public long getpY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    Vector2d vect;

    public Goblin(Scroller scrl, int x, int y) {
        super(scrl);
        prevsx=Scroller.scrolledX;
        prevsy=Scroller.scrolledY;
        worldX = x;
        worldY = y;

        this.mort = false;

        directie.put("D", new GifImage("npc/inamic/goblin/goblin_m_D.gif"));
        directie.put("W", new GifImage("npc/inamic/goblin/goblin_m_W.gif"));
        directie.put("A", new GifImage("npc/inamic/goblin/goblin_m_A.gif"));
        directie.put("S", new GifImage("npc/inamic/goblin/goblin_m_S.gif"));
        directie.put("idle", new GifImage("npc/inamic/goblin/goblin_m_Idle.gif"));

        directie.put("death", new GifImage("npc/inamic/goblin/goblin_death.gif"));

        gif = "idle";

        this.timpSab = 0;
        this.timpBolt = 0;
        this.timpAtins = 0;

        this.timpPrec = System.currentTimeMillis();
    }

    protected void gaseste() {
        List players = getWorld().getObjects(Player.class);
        if (!players.isEmpty()) {
            Actor player = (Actor) players.get(0);

            int playerX = (Player.worldX) / super.rez;
            if (Player.worldY % super.rez > 0) {
                playerX++;
            }

            int playerY = (Player.worldY) / super.rez;
            if (Player.worldY % super.rez > 0) {
                playerY++;
            }

            int gY = (worldY+Scroller.scrolledY) / super.rez;
            if ((worldY+Scroller.scrolledY) % super.rez > 0) {

                gY++;
            }

            int gX = (worldX+Scroller.scrolledX) / super.rez;
            if ((worldX+Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }

            super.Lee(gY, gX, playerY, playerX);
        }

        if (super.gasit == true) {
            if (isTouching(Jucator.class) && ((Player.worldY) <= (worldY + Scroller.scrolledY) - 10 || (Player.worldX) <= (worldX+Scroller.scrolledX) - 15
                    || (Player.worldY) >= (worldY+Scroller.scrolledY) + 10 || (Player.worldX) >= (worldX+Scroller.scrolledX) + 15)) {
                gif = "idle";

            } else {
                move();
            }
        } else {
            gif = "idle";
        }
        playerImg = directie.get(super.gif);

    }

    protected void move() {
        pasi = super.ord.size() - 1;
        if (pasi > 0) {

            String directie = super.ord.get(pasi).toString();
            int gY = (worldY+Scroller.scrolledY) / super.rez;
            if ((worldY+Scroller.scrolledY) % super.rez > 0) {
                gY++;
            }
            int gX = (worldX+Scroller.scrolledX) / super.rez;
            if ((worldX+Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }
            if (super.matElem[gY][gX] != -1) {
                super.matElem[gY][gX] = 0;
            }
            int difpx=Scroller.scrolledX-prevsx;
            int difpy=Scroller.scrolledY-prevsy;
            
            worldX-=difpx;
            worldY-=difpy;
            
            switch (directie) {
                case "W": {

                    gif = "W";
                    dist += speed;

                    worldY -= (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "D": {

                    gif = "D";
                    dist += speed;
                    
                    worldX += (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "S": {

                    gif = "S";
                    dist += speed;

                    worldY += (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "A": {

                    gif = "A";
                    dist += speed;

                    worldX -= (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "WD": {
                    gif = "D";
                    dist += speed;

                    worldX += (speed);
                    worldY -= (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "WA": {
                    gif = "A";
                    dist += speed;

                    worldX -= (speed);
                    worldY -= (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "SD": {
                    gif = "D";
                    dist += speed;
                    
                    worldY += (speed);
                    worldX += (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                case "SA": {
                    gif = "A";
                    dist += speed;

                    worldX -= (speed);
                    worldY += (speed);
                    super.setLocation(worldX, worldY);
                    gifSabie = gif;

                    break;
                }
                 

            }
            prevsx=Scroller.scrolledX;
            prevsy=Scroller.scrolledY;
            // lovitSabie();
            // lovitLaser();
            if (dist >= 10) {
                dist = 0;
                pasi--;
            }
            gY = (worldY+Scroller.scrolledY) / super.rez;
            if ((worldY+Scroller.scrolledY) % super.rez > 0) {
                gY++;
            }
            gX = (worldX+Scroller.scrolledX) / super.rez;
            if ((worldX+Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }

            //if(super.matElem[gY][gX]!=-1){super.matElem[gY][gX]=-2;}
            //
        }
    }

    protected void lovitSabie() {

        /* 
        if(isTouching(Sabie.class)){
            timpSab++;//cat timp ating ating asteroidul
            if(timpSab>=16)
            {
               health(Sabie.damage);
               if(!(traiesc()))
               {
                   super.gif="death"; 
                   playerImg=directie.get(super.gif);
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
                   super.gif="death"; 
                playerImg=directie.get(super.gif);
                   mort=true;
                }
               timpBolt=0;
            }
        }
        else if(timpBolt>0){
            timpBolt=0;
        }*/
    }

    protected void atac() {

        /*
            long timpCurent=System.currentTimeMillis(); 
            if(timpCurent-timpPrec>=950)
            {
                
               
                if(gifSabie=="D"){
                getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX)+36,(getY()+scrolledY));
                }
                else if(gifSabie=="A"){
                    getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX)-36,(getY()+scrolledY));
                }
                else if(gifSabie=="S"){
                    getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX),(getY()+scrolledY)+20);
                }
                else if(gifSabie=="W"){
                    getWorld().addObject(new SabieGoblin(this),(getX()+scrolledX),(getY()+scrolledY)-20);
                }
                timpPrec=timpCurent;
            }
            
         */
    }
    long timpMort = 0;

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {
            
          
            
            gif = "idle";
            if (mort == true) {
                //moare

                /* 
                setImage(playerImg.getCurrentImage());
                if(timpMort>=super.rez+28)
                {
                
                    World lume=getWorld();
                    if(lume instanceof Nivel2_7){
                        Nivel2_7.inamici--;
                    }
                    if(lume instanceof Nivel3_4){
                        Nivel3_4.inamici--;
                    }
                    if(lume instanceof Nivel4_2){
                        Nivel4_2.inamici--;
                    }
                    getWorld().removeObject(this);//dispare
                    return;
                }
                timpMort++;
                 */
            } else {

                //lovitSabie();
                //lovitLaser();
                int x = (worldX); /////{
                int y = (worldY);   //calculeaza un nr in functie de care sa setam o pauza
                long a = x * y / 2000; //}

                if (isTouching(Jucator.class)) {
                    //worldX=getX();
                    //worldY=getY();

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atac();///////{

                    gif = "idle";
                    //lovitSabie();//{
                    // lovitLaser();//verifica daca e lovit
                    //{
                    playerImg = directie.get(super.gif);

                } else {
                    // lovitSabie();
                    // lovitLaser();

                    long pauza = Greenfoot.getRandomNumber(20) + 30 + a;//{
                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= pauza) ///////////////////////ia o pauza
                        {
                            atingePlayer = false;

                        }
                    }///////////////////////////////////////////////{
                    else {

                        gaseste();//cauta playerul

                    }

                }
            }
        }

        playerImg = directie.get(gif);
        setImage(playerImg.getCurrentImage());
        
    }

    private void health(int dmg) {
        hp -= dmg;
    }

    private boolean traiesc() {
        return hp > 0;
    }
}

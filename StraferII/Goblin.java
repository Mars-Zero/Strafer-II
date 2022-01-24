
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

 

    public Goblin(Scroller scrl, int x, int y) {
        super(scrl);
        prevsx = Scroller.scrolledX;
        prevsy = Scroller.scrolledY;
        worldX = x-Scroller.scrolledX;
        worldY = y-Scroller.scrolledY;

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
        Player player = (Player) players.get(0);
        if (!players.isEmpty()) {

            int playerX = (player.getWorldX()) / super.rez;
            if (player.getWorldX() % super.rez > 0) {
                playerX++;
            }

            int playerY = (player.getWorldY()) / super.rez;
            if (player.getWorldY() % super.rez > 0) {
                playerY++;
            }

            int gY = (worldY + Scroller.scrolledY) / super.rez;
            if ((worldY + Scroller.scrolledY) % super.rez > 0) {

                gY++;
            }

            int gX = (worldX + Scroller.scrolledX) / super.rez;
            if ((worldX + Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }

            super.Lee(gY, gX, playerY, playerX);
        }

        if (super.gasit == true) {

            if (isTouching(Jucator.class) && (player.getWorldY() <= (worldY + Scroller.scrolledY) - 10 || player.getWorldX() <= (worldX + Scroller.scrolledX) - 15
                    || (player.getWorldY()) >= (worldY + Scroller.scrolledY) + 10 || player.getWorldX() >= (worldX + Scroller.scrolledX) + 15)) {
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
            int gY = (worldY + Scroller.scrolledY) / super.rez;
            if ((worldY + Scroller.scrolledY) % super.rez > 0) {
                gY++;
            }
            int gX = (worldX + Scroller.scrolledX) / super.rez;
            if ((worldX + Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }
            if (super.matElem[gY][gX] != -1) {
                super.matElem[gY][gX] = 0;
            }
            int difpx = Scroller.scrolledX - prevsx;
            int difpy = Scroller.scrolledY - prevsy;

            worldX -= difpx;
            worldY -= difpy;

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
            prevsx = Scroller.scrolledX;
            prevsy = Scroller.scrolledY;
            // lovitSabie();
            // lovitLaser();
            if (dist >= 10) {
                dist = 0;
                pasi--;
            }
            gY = (worldY + Scroller.scrolledY) / super.rez;
            if ((worldY + Scroller.scrolledY) % super.rez > 0) {
                gY++;
            }
            gX = (worldX + Scroller.scrolledX) / super.rez;
            if ((worldX + Scroller.scrolledX) % super.rez > 0) {
                gX++;
            }

            //if(super.matElem[gY][gX]!=-1){super.matElem[gY][gX]=-2;}
            //
        }
    }

    protected void knockbackMove() {//boolean knockbacked) {

        if (knockbacked) {
            this.frameuri_trecute++;
            setLocation((int) (getX() + viteza_frame * Math.cos(grade_rezultanta)), (int) (getY() + viteza_frame * Math.sin(grade_rezultanta)));
            worldX += viteza_frame * Math.cos(grade_rezultanta);
            worldY += viteza_frame * Math.sin(grade_rezultanta);
            System.out.println(distance_added);

            if (this.frameuri_trecute >= this.timp_knockback * 60) {
                this.frameuri_trecute = 0;
                knockbacked = false;
            }
        }

    }

    protected void lovitSabie() {

        List players = getWorld().getObjects(Player.class);
        Player player = (Player) players.get(0);
        List sabii = getWorld().getObjects(Sabie.class);
        if (!sabii.isEmpty()) {
            Sabie sabia = (Sabie) sabii.get(0);

            int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
            if (deltaPGX < 0) {
                deltaPGX *= (-1);
            }
            int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
            if (deltaPGY < 0) {
                deltaPGY *= (-1);
            }

            if (player.isEquipSword() == true && deltaPGX <= 85 && deltaPGY <= 100) {
                knockbacked = true;
                super.knockback(0.1, sabia.getPlayer(), 2, 80);
            }
        }

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

            } else {

                lovitSabie();
                //lovitLaser();
                long waitseed = Greenfoot.getRandomNumber(2500);

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

            playerImg = directie.get(gif);

                   setImage(playerImg.getCurrentImage());
        }

    }

    private void health(int dmg) {
        hp -= dmg;
    }

    private boolean traiesc() {
        return hp > 0;
    }
}

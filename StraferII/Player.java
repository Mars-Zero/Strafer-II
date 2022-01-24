
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math;

public class Player extends Jucator {

    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage playerImg = directie.get(this.gif);

    public String gif = "Ds";

    int direction;

    int moveTime = 7;

    //{
    //xy pe toata mapa
    //public static int worldX, worldY;
    //xy pe toata mapa
    //{
    private int worldX, worldY;

    public static int floorLevel = 1;

    boolean isMoving;
    
   private boolean inViata = true;

    public boolean isInViata() {
        return inViata;
    }

    public void setInViata(boolean inViata) {
        this.inViata = inViata;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isEquipSword() {
        return equipSword;
    }

    public void setEquipSword(boolean equipSword) {
        this.equipSword = equipSword;
    }

    public boolean isEquipLaser() {
        return equipLaser;
    }

    public void setEquipLaser(boolean equipLaser) {
        this.equipLaser = equipLaser;
    }

    public boolean isEquipPortalGun() {
        return equipPortalGun;
    }

    public void setEquipPortalGun(boolean equipPortalGun) {
        this.equipPortalGun = equipPortalGun;
    }

    public boolean isEquipIceLock() {
        return equipIceLock;
    }

    public void setEquipIceLock(boolean equipIceLock) {
        this.equipIceLock = equipIceLock;
    }

    public boolean isEquipLantern() {
        return equipLantern;
    }

    public void setEquipLantern(boolean equipLantern) {
        this.equipLantern = equipLantern;
    }

    public boolean isEquipBlackHole() {
        return equipBlackHole;
    }

    public void setEquipBlackHole(boolean equipBlackHole) {
        this.equipBlackHole = equipBlackHole;
    }

    public boolean isToggledInventory() {
        return toggledInventory;
    }

    public void setToggledInventory(boolean toggledInventory) {
        this.toggledInventory = toggledInventory;
    }

    public int getHpMax() {
        return hpMax;
    }

    private int hp = 400;
    private final int hpMax = 400;
    private int speed = 7;

    private boolean equipSword = false;
    private boolean equipLaser = false;
    private boolean equipPortalGun = false;
    private boolean equipIceLock = false;
    private boolean equipLantern = false;
    private boolean equipBlackHole = false;

    private boolean toggledInventory = false;
    public static boolean toggledPause = false;

    private  HashSet<String> iteme = new HashSet<String>();
    public HashSet<String> getIteme() {
        return iteme;
    }

    protected long timpPrec;
    private long timp = 0;
    private long timpSab = 0, timpLaser = 0, timpBoaba = 0, timpPumni = 0;

    public static boolean apas;

    public Player() {

        prepareData();

        //sta
        directie.put("idle", new GifImage("player/player_m_Idle.gif"));
        //moarte
        directie.put("death", new GifImage("player/player_death.gif"));

        //merge
        directie.put("D", new GifImage("player/player_m_D.gif"));
        directie.put("W", new GifImage("player/player_m_W.gif"));
        directie.put("A", new GifImage("player/player_m_A.gif"));
        directie.put("S", new GifImage("player/player_m_S.gif"));

        //se uita
        directie.put("Ds", new GifImage("player/vedere_D.gif"));
        directie.put("Ws", new GifImage("player/vedere_W.gif"));
        directie.put("As", new GifImage("player/vedere_A.gif"));
        directie.put("Ss", new GifImage("player/vedere_S.gif"));

        //gif="idle";
        // HealthBar bara=new HealthBar("Jucator","Viata",this.hp, this.hp);
        // getWorld().addObject(bara,0,0);
        this.timpPrec = System.currentTimeMillis();
    }

    private void prepareData() {

        equipSword = false;
        equipLaser = false;
        equipPortalGun = false;
        equipIceLock = false;
        equipLantern = false;
        equipBlackHole = false;

        toggledInventory = false;
        toggledPause = false;

        WorldData.PAUZA = false;

        this.floorLevel = 1;
    }

    protected void checkMove() {
        apas = false;

        if (Greenfoot.isKeyDown("w")) {
            //merge in nord 
            apas = true;
            gif = "W";
            Item.itemGif = "W";
            setLocation(getX(), getY() - speed);

        }

        if (Greenfoot.isKeyDown("s")) {
            //merge in sud   
            apas = true;
            gif = "S";
            Item.itemGif = "S";
            setLocation(getX(), getY() + speed);

        }
        if (Greenfoot.isKeyDown("d")) {
            //merg in est
            apas = true;
            gif = "D";
            Item.itemGif = "D";
            setLocation(getX() + speed, getY());

        }

        if (Greenfoot.isKeyDown("a")) {
            //merg in vest
            apas = true;
            gif = "A";
            Item.itemGif = "A";
            setLocation(getX() - speed, getY());

        }
        if (apas == false) {
            if (equipSword == false && equipLaser == false && equipPortalGun == false && equipIceLock == false && equipLantern == false && equipBlackHole == false) {
                gif = "idle";

            }

            switch (gif) {
                case "W": {
                    gif = "Ws";
                    Item.itemGif = "W";
                    break;
                }

                case "A": {
                    gif = "As";
                    Item.itemGif = "A";
                    break;
                }

                case "S": {
                    gif = "Ss";
                    Item.itemGif = "S";
                    break;
                }
                case "D": {
                    gif = "Ds";
                    Item.itemGif = "D";
                    break;
                }

                case "Ws": {
                    Item.itemGif = "W";
                    break;
                }
                case "As": {
                    Item.itemGif = "A";
                    break;
                }
                case "Ss": {
                    Item.itemGif = "S";
                    break;
                }
                case "Ds": {
                    Item.itemGif = "D";
                    break;
                }
                default: {
                    Item.itemGif = "S";
                }

            }

        }

    }

    public void move() {

        //if(super.gif!="idle" && super.gif!="right" &&super.gif!="up" &&super.gif!="down" && super.gif!="left"){atingeNpc();}
        checkMove();
        // if(super.gif!="idle" && super.gif!="right" &&super.gif!="up" &&super.gif!="down" && super.gif!="left"){atingeNpc();}
        worldX = getX() + Scroller.scrolledX;
        worldY = getY() + Scroller.scrolledY;

        playerImg = directie.get(this.gif);
    }

    protected void vedere() {

        playerImg = directie.get(this.gif);
    }

    protected void toggleMenu() {

        if (!toggledInventory) {

            if (Greenfoot.isKeyDown("E")) {
                toggledInventory = !toggledInventory;
                getWorld().addObject(new Inventory(this), 875, 430);

                getWorld().addObject(new ItemSelect(this), 875, 430);
            }

        }

    }

    protected void useItem() {

        //portalGun
        if (equipPortalGun) {
            long timpCurent = System.currentTimeMillis();
            if (timpCurent - timpPrec >= 20) {
                if (getWorld().getObjects(PortalGun.class).isEmpty()) {
                    getWorld().addObject(new PortalGun(), getX(), getY());
                }
                timpPrec = timpCurent;
            }
            Item.itemGif = gif;

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipPortalGun = false;
                }
            }
        }
        //portalGun

        //sabie
        if (equipSword) {

            if (getWorld().getObjects(Sabie.class).isEmpty()) {
                getWorld().addObject(new SabieHold(), getX(), getY());
            }

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 1) {  //right 3 left 1

                    long timpCurent = System.currentTimeMillis();
                    if (timpCurent - timpPrec >= 20) {
                        if (getWorld().getObjects(Sabie.class).isEmpty()) {

                            getWorld().addObject(new Sabie(this), getX(), getY());
                        }
                        timpPrec = timpCurent;
                    }
                    Item.itemGif = gif;
                }
            }
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipSword = false;
                }
            }
        }
        //sabie

        //laser
        if (equipLaser) {

            if (getWorld().getObjects(Sabie.class).isEmpty()) {
                getWorld().addObject(new LaserHold(), getX(), getY());
            }

            long timpCurent = System.currentTimeMillis();
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 1) {
                    if (timpCurent - timpPrec >= 20) {

                        if (getWorld().getObjects(PortalGun.class).isEmpty()) {

                            MouseInfo mouseul = Greenfoot.getMouseInfo();
                            if (mouseul != null) {

                                double delta_x = Greenfoot.getMouseInfo().getX() - getX();
                                double delta_y = Greenfoot.getMouseInfo().getY() - getY();
                                double grade = Math.toDegrees(Math.atan2(delta_y, delta_x));
                                
                                
                                int gr = (int) grade;
                                gr -= gr % 10;gr += (Greenfoot.getRandomNumber(2)-1 )*5;  //reduce din precizie
                                grade=gr;
                                
                                
                                if (grade >= -45 && grade < 45) {       //se intoarce in dir in care trage

                                    this.gif = "D";
                                }
                                if (grade >= 45 && grade < 135) {

                                    this.gif = "S";

                                }
                                if (grade >= 135 && grade < 225) {

                                    this.gif = "A";

                                }
                                if (grade >= -180 && grade < -45) {

                                    this.gif = "W";
                                }
                                vedere();
                                
                                getWorld().addObject(new Laser(grade), getX(), getY());

                            }
                            timpPrec = timpCurent;
                        }

                    }
                    Item.itemGif = gif;
                }
            }
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipLaser = false;
                }
            }
        }
        //laser

        //lantern
        if (equipLantern) {
            long timpCurent = System.currentTimeMillis();
            if (timpCurent - timpPrec >= 20) {
                if (getWorld().getObjects(Lantern.class).isEmpty()) {
                    getWorld().addObject(new Lantern(this), getX() + 30, getY());
                }
                timpPrec = timpCurent;
            }

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipLantern = false;
                }
            }
        }
        //lantern

    }

    protected void checkPauza() {

        if (!toggledPause) {

            if (Greenfoot.isKeyDown("escape")) {
                toggledPause = !toggledPause;
                WorldData.PAUZA = true;

                Pause pause = new Pause();
                getWorld().addObject(pause, ConstantVariables.MainMenuX, ConstantVariables.MainMenuY);

            }

        }

        if (Greenfoot.isKeyDown("0")) {
            WorldData.PAUZA = false;
            toggledPause = false;
        }

    }

    protected void lovit() {

    }

    public void act() {

        //System.out.println("\nwx");
        //System.out.println(worldX);
        checkPauza();

        if (!WorldData.PAUZA) {
            useItem();
            lovit();
            move();
            toggleMenu();
            setImage(playerImg.getCurrentImage());
        }

    }

    public int getDirection() {
      
      if(getX()>=1020){
          direction= 3;
      }
      if(getX()<=5){
          direction=1;
      }
      if(getY()>=570){
          direction=2;
      }
      if(getY()<=5){
          direction=0;
      }
      

        return direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int val) {
        worldX = val;
    }

    public void setWorldY(int val) {
        worldY = val;
    }

}

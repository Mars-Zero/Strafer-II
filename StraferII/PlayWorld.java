
import greenfoot.*;

public class PlayWorld extends World {

    int WIDE, HIGH;

    public static int originalX = 150, originalY = 200;

    public WorldListener worldListener;
    public MainStoryline mainStoryline;

    public Scroller scroller;

    Player player;

    public HealthBarPlayer healthBar;
    private boolean addedHealthBar = false;

    public GreenfootSound musicIdle = new GreenfootSound("sounds/music/Default.mp3");
    public GreenfootSound musicCombat = new GreenfootSound("sounds/music/Combat.mp3");

    public PlayWorld() {
        super(WorldData.WIDTH, WorldData.HIGHT, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume

        setPaintOrder(Buton.class, Menu.class, HealthBar.class, Text.class, Picture.class, MapMenu.class, Tutorial.class, Dialog.class,
                 Item.class, NpcItem.class, Lantern.class, Light.class, Droid.class, Player.class, Npc.class);

        WIDE = WorldData.WIDTH;
        HIGH = WorldData.HIGHT;

        WorldData.loadWorldMatrices();

        addPlayer();
        WorldData.addedDialogs = false;
        addedHealthBar = false;

        musicCombat.setVolume(45);
        musicIdle.setVolume(45);

        addMainMenu();

    }

    private void addMainMenu() {
        addObject(new MainMenu(), WorldData.menuX, WorldData.menuY);
    }

    public void addPlayer() {
        GreenfootImage background = new GreenfootImage("map/worldSection/worldSection11.png");//imi pun fundalul
        scroller = new Scroller(this, background, 8192, 8192);

        player = new Player();

        addObject(player, originalX, originalY);//pozitia pe newGame

        scroll();

        worldListener = new WorldListener(this);
        mainStoryline = new MainStoryline();

        addObject(worldListener, 1, 1);
        addObject(mainStoryline, 1, 1);

        addObject(new Fps(), 150, 50);
    }

    Picture barBack = new Picture("UI/hud/healthBar.png");

    private void addHealthBar() {

        healthBar = new HealthBarPlayer("", "", player.getHp(), player.getHpMax());

        Color colorSafeHealth = new Color(95, 205, 228), colorDangerHealth = new Color(222, 93, 18);
        healthBar.setSafeColor(colorSafeHealth);
        healthBar.setDangerColor(colorDangerHealth);
        healthBar.setBarWidth(181);
        healthBar.setBarHeight(14);
        healthBar.setReferenceText("");
        healthBar.setTextColor(new Color(4, 69, 85, 214));

        addObject(barBack, 148, 40);
        addObject(healthBar, 172, 32);
    }

    void relocBar() {
        if (WorldData.PAUZA) {
            if (getObjects(Inventory.class).isEmpty()) {
                barBack.setLocation(-300, -100);
            }
        } else {
            barBack.setLocation(148, 40);
        }
    }

    //adauga obiectele pe toata mapa, nu doar pe suprafata de display
    protected <T extends Actor> void initObject(T actor, int x, int y) {
        this.addObject(actor, x - Scroller.scrolledX, y - Scroller.scrolledY);
    }

    ///scrolleaza lumea
    public void scroll() {
        if (player != null) {
            int dsX = player.getX() - WIDE / 2;
            int dsY = player.getY() - HIGH / 2;
            scroller.scroll(dsX, dsY);

        }
    }

    public Scroller getScroller() {
        return scroller;
    }

    public void act() {

        scroll();
        if (!addedHealthBar) {
            addHealthBar();
            addedHealthBar = true;
        }
        relocBar();
        updateMusic();

    }

    public WorldListener getWorldListener() {
        return worldListener;
    }

    public Player getPlayer() {
        return player;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public GreenfootSound getMusicIdle() {
        return musicIdle;
    }

    public GreenfootSound getMusicCombat() {
        return musicCombat;
    }

    public void updateMusic() {
        if (!WorldData.PAUZA) {
            if (!musicIdle.isPlaying()) {
                if(WorldData.isWalking&&!WorldData.isFighting){
                    musicIdle.play();
                }
            }
            if (!musicCombat.isPlaying()) {
                if(WorldData.isFighting){
                    musicCombat.play();
                    musicIdle.pause();
                }
            }
            
            if (musicCombat.isPlaying()) {
                if(!WorldData.isFighting){
                    musicCombat.pause();
                }
            }
        } else {
            if (musicIdle.isPlaying()) {
                musicIdle.pause();
            }
            if (musicCombat.isPlaying()) {
                musicCombat.pause();
            }
        }
    }
 
}

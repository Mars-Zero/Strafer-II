
import greenfoot.*;

public class PlayWorld extends World {

    int WIDE, HIGH;
    int originalX = 30, originalY = 200;

    public WorldListener worldListener;

    public Scroller scroller;

    Player player;

    public HealthBar healthBar;
    private boolean addedHealthBar = false;

    public PlayWorld() {
        super(WorldData.WIDTH, WorldData.HIGHT, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume

        setPaintOrder(Buton.class, Menu.class, HealthBar.class, Text.class, Picture.class,MapMenu.class, Tutorial.class, Dialog.class,
                Floor.class, Item.class, Lantern.class, Light.class, Player.class, Npc.class);

        WIDE = WorldData.WIDTH;
        HIGH = WorldData.HIGHT;
        addPlayer();
        WorldData.addedDialogs = false;
        addedHealthBar = false;
        addMainMenu();

        //AssetsCache.storeAssets();
    }

    private void addMainMenu() {
        addObject(new MainMenu(), ConstantVariables.MainMenuX, ConstantVariables.MainMenuY);
    }

    public void addPlayer() {
        GreenfootImage background = new GreenfootImage("map/worldSection/worldSection" + 11 + ".png");//imi pun fundalul
        scroller = new Scroller(this, background, 8192, 8192);
        player = new Player();

        addObject(player, originalX, originalY);

        player.setWorldX(originalX);
        player.setWorldY(originalY);

        scroll();

        worldListener = new WorldListener(this);
        addObject(worldListener, 1, 1);

        addObject(new Fps(), 150, 50);
    }

    Picture barBack=new Picture("UI/hud/healthBar.png");
    private void addHealthBar() {
        
        healthBar = new HealthBar("", "", player.getHp(), player.getHpMax());
        
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
    void relocBar(){
        if(WorldData.PAUZA){
            barBack.setLocation(-300,-100); 
        }
        else{
            barBack.setLocation(148,40); 
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
        

    }

    public Player getPlayer() {
        return player;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

}


import greenfoot.*;

public class PlayWorld extends World {

    int WIDE, HIGH;
    
    int originalX = 100, originalY=100;

    public WorldListener worldListener;
public MainStoryline mainStoryline;

   
    public Scroller scroller;

    Player player;

    public HealthBar healthBar;
    private boolean addedHealthBar = false;

    public PlayWorld() {
        super(WorldData.WIDTH, WorldData.HIGHT, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume

        setPaintOrder(Buton.class, Menu.class, HealthBar.class, Text.class, Picture.class,MapMenu.class, Tutorial.class, Dialog.class,
                Floor.class, Item.class,NpcItem.class, Lantern.class, Light.class, Droid.class,Player.class, Npc.class);

        WIDE = WorldData.WIDTH;
        HIGH = WorldData.HIGHT;

         WorldData.loadWorldMatrices();
        
        addPlayer();
        WorldData.addedDialogs = false;
        addedHealthBar = false;
        addMainMenu();

        //AssetsCache.storeAssets();
    }

    private void addMainMenu() {
        addObject(new MainMenu(), WorldData.menuX, WorldData.menuY);
    }

    public void addPlayer() {
        GreenfootImage background = new GreenfootImage("map/worldSection/worldSection11.png");//imi pun fundalul
        scroller = new Scroller(this, background, 8192, 8192);
        
        player = new Player();

        addObject(player, 1, 1);

        scroll();

        worldListener = new WorldListener(this);
        mainStoryline=new MainStoryline();
        
        addObject(worldListener, 1, 1);
        addObject(mainStoryline,1,1);
        
        
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
            if(getObjects(Inventory.class).isEmpty()){
            barBack.setLocation(-300,-100); 
            }
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

    

    
    public WorldListener getWorldListener() {
        return worldListener;
    }

    public Player getPlayer() {
        return player;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

}

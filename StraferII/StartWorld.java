import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartWorld extends World{
    
    Scroller scroller;
    Player player;
    static final int WIDE=1024, HIGH= 576;
    static int originalX=30,originalY=200;
   
    public StartWorld(){
        super(WIDE,HIGH, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume
        addPlayer();
        addObject(new TestActor(),1000,2000);
        //setBackground(background);
    }
    
     public void addPlayer()
    {
        GreenfootImage background=new GreenfootImage("images/test/rosu.png");//imi pun fundalul
        scroller = new Scroller(this, background, 10000, 10000);
        player = new Player();
        addObject(player, originalX, originalY);
        Player.originalX = originalX;
        Player.originalY = originalY;
        Player.worldX = originalX;
        Player.worldY = originalY;
     
        scroll();
    }
    
    public void scroll(){
        if(player != null)
        {
            int dsX = player.getX() - WIDE / 2;
            int dsY = player.getY() - HIGH / 2;
            scroller.scroll(dsX, dsY);
        }
    }
    
    
    
     public void act(){
        scroll();
    }
}

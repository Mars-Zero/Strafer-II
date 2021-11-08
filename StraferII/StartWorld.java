import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartWorld extends World{
    
    static final int WIDE=1024, HIGH= 576;
    
   public Scroller scroller;
    Player player;  static int originalX=30,originalY=200;
    Fps fps;
    
  
   
    public StartWorld(){
        super(WIDE,HIGH, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume
        addPlayer();
       addWorldObjects();
        
         for(int i=0; i<110; i++){
          for(int j=0; j<110; j++){
            Npc.matElem[i][j]=0;
           }
        }
    }
    
     public void addPlayer()
    {
        GreenfootImage background=new GreenfootImage("images/test/map.png");//imi pun fundalul
        scroller = new Scroller(this, background, 10000, 10000);
        player = new Player();
        addObject(player, originalX, originalY);
        Player.originalX = originalX;
        Player.originalY = originalY;
        Player.worldX = originalX;
        Player.worldY = originalY;
     
        scroll();
        
        fps=new Fps(); 
        addObject(new Fps(), 150, 50);
    }
     
    
     
     
     public void addWorldObjects(){ 
       
        addObject(new Goblin(scroller),100,200);
        addObject(new PereteInvizibil("A","mare90"),0,300);//margini
          addObject(new PereteInvizibil("W","mic"), 690,340);
         addObject(new PereteInvizibil("W","mic"), 750,340);
           addObject(new PereteInvizibil("W","mic"), 690,340);
    
         
         
         addObject(new PereteInvizibil("A","mic90"), 795,300);
         addObject(new PereteInvizibil("D","mic90"), 585,300);
                    
         addObject(new PereteInvizibil("S","mic"), 630,255);
         addObject(new PereteInvizibil("S","mic"), 690,255);
         addObject(new PereteInvizibil("S","mic"), 750,255);
                
       addObject(new PereteInvizibil("D","mic90"),1185,0);//margini
        addObject(new PereteInvizibil("D","mic90"),1185,60);//margini
        addObject(new PereteInvizibil("D","mic90"),1185,120);//margini
        addObject(new PereteInvizibil("D","mic90"),1185,180);//margini
        addObject(new TestActor(scroller),1000,2000);
       for(int i=1;i<=10500;i+=1024){
           addObject(new PereteInvizibil("W", "mare"),i,16);
        }
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

import greenfoot.*;  

public class StartWorld extends World{

    int WIDE,HIGH;
    
    
    Fps fps;
   public Scroller scroller;
    Player player;  int originalX=30,originalY=200;
    
   

    public StartWorld(){
        super(WorldData.WIDTH,WorldData.HIGHT, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume
        
        setPaintOrder(Buton.class,Menu.class,Floor.class,Item.class,Player.class,Npc.class);
        
        WIDE=WorldData.WIDTH;
        HIGH=WorldData.HIGHT;
        addPlayer();
        addWorldObjects();

        /*for(int i=0; i<110; i++){
        for(int j=0; j<110; j++){
        Npc.matElem[i][j]=0;
        }
        }*/
        
    }

     public void addPlayer(){
        GreenfootImage background=new GreenfootImage("images/test/map.png");//imi pun fundalul
        scroller = new Scroller(this, background, 8192, 8192);
        player = new Player();
        addObject(player, originalX, originalY);
        Player.worldX=originalX;
        Player.worldY=originalY;
        
        scroll();

        fps=new Fps(); 
        addObject(new Fps(), 150, 50);
    }

     
     public void addWorldObjects(){ 
        //addObject(new Goblin(scroller,100,200),100,200);
        
        
        addObject(new PereteInvizibil("A",1,"mare90"),0,300);//margini
          addObject(new PereteInvizibil("W",1,"mic"), 690,340);
         addObject(new PereteInvizibil("W",1,"mic"), 750,340);
           addObject(new PereteInvizibil("W",1,"mic"), 690,340);

         
         addObject(new PereteInvizibil("A",1,"mic90"), 795,300);
         addObject(new PereteInvizibil("D",1,"mic90"), 585,300);

         addObject(new PereteInvizibil("S",1,"mic"), 630,255);
         addObject(new PereteInvizibil("S",1,"mic"), 690,255);
         addObject(new PereteInvizibil("S",1,"mic"), 750,255);

       addObject(new PereteInvizibil("D",1,"mic90"),1185,0);//margini
        addObject(new PereteInvizibil("D",1,"mic90"),1185,60);//margini
        addObject(new PereteInvizibil("D",1,"mic90"),1185,120);//margini
        addObject(new PereteInvizibil("D",1,"mic90"),1185,180);//margini
        addObject(new TestActor(scroller),1000,2000);
       for(int i=1;i<=10500;i+=1024){
           addObject(new PereteInvizibil("W",1, "mare"),i,16);
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inamic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestActor extends Npc

{
    private Animation animation;
    private GifImage gif;
     public static MouseInfo m; 
    public TestActor(Scroller scrl){
        //animation
        super(scrl);
        java.util.List<GreenfootImage> imgs = new GifImage("images/world/casuta.png").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i=0; i<imgs.size(); i++){ 
                images[i] = (GreenfootImage)imgs.get(i);
        }
        
        animation = new Animation(this, images);
        animation.setCycleActs(70);
        animation.setCycleCount(3);
        animation.run();
        animation.setActiveState(true);
        //animation
        
        //gif
        gif=new GifImage("images/world/casuta.png");
        setImage(gif.getCurrentImage());
        //gif
        
    }
    
     protected void arataPoz(String p){
        
        //if(getY()>300){y=getY()+75;}
        //else{y=getY()-75;
            if(Greenfoot.isKeyDown("enter")){
                System.out.println("#####"+"######");
                System.out.println( p+ "       "+" X"+getX()+" .  Y"+getY()+"{   }" );
                System.out.println("#####"+"######");
    }
    
  
}

    public void act()
    {
            arataPoz("casa");
           animation.run();
            //setImage(gif.getCurrentImage());
    }   
}

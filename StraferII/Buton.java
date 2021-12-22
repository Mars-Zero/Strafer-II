import greenfoot.*; 
public class Buton extends UI{
    
    GreenfootImage img0,img1;

    public buton(String img){
        img0=new GreenfootImage(img+"0.png");
        img1=new GreenfootImage(img+"1.png");
    }

    protected void checkMouse(){
         MouseInfo mouseul = Greenfoot.getMouseInfo();
        if(mouseul!=null){
            if(mouseul.getX()<=img0.getX()+img0.getHeight()/2){
                setImage(img1);
            }
        }
    }

    public void act() {

    }    
}

import greenfoot.*;  
import java.util.*;

public class Inventory extends Menu{
    
    
    GreenfootImage wheel0=new GreenfootImage("UI/hud/itemWheel.png");
    GreenfootImage wheel1=new GreenfootImage("UI/hud/itemWheelSelect.png");
    
    
    public Inventory(){
        Player.toggledInventory=true;
    }
    
    public void checkMouse(){
        
         if (Greenfoot.mouseMoved(this)){
             
            setImage(wheel1);//cand sunt pe buton
            double delta_x = Greenfoot.getMouseInfo().getX() - 915;
            double delta_y =  Greenfoot.getMouseInfo().getY()-475;
            double grade =Math.toDegrees( Math.atan2(delta_y, delta_x));
            
           System.out.println(grade);
            
           if(grade>=-90&&grade<-30){
               this.setRotation(0);
           }
           if(grade>=-30&&grade<30){
               this.setRotation(60);
           }
           if(grade>=30&&grade<90){
               this.setRotation(120);
           }
           if(grade>=90&&grade<150){
               this.setRotation(180);
           }
           if(grade>=150&&grade<210){
               this.setRotation(240);
           }
           if(grade>=-150&&grade<-90){
               this.setRotation(300);
           }
           
                
         }
          if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
            setImage(wheel0);//altfel
            setRotation(0);
         }
          
          
            
    }
    
    
    public void act(){
        
        this.setLocation(915,475);
        checkMouse();
    }    
}

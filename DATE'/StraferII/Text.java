import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Text extends Menu
{
    
    Font fontu=new Font("Calibri",true,false,0);
    
    String txt;
    int size;
    public Text(String txtref, int sizeref){
        txt=txtref;
        size=sizeref;
        
        GreenfootImage textImage=new GreenfootImage(1000,500);
        textImage.setFont(new Font("Dialog", true, false , size*2));
        textImage.drawString(txt,size,size);
        textImage.scale(textImage.getWidth()/4,textImage.getHeight()/4);
        setImage(textImage);
        GreenfootImage textImagePixelated;
        textImagePixelated=getImage();
        textImagePixelated.scale(textImagePixelated.getWidth()*2,textImagePixelated.getHeight()*2);
        setImage(textImagePixelated);
        
        
    }
    
    
    
    
    
    
   
    
    
    public void act() 
    {
        // Add your action code here.
    }    
}







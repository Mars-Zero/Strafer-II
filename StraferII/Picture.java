
import greenfoot.*;

public class Picture extends Menu {

    GreenfootImage img;
    String imageName;
    boolean moveable;
    int x,y;
    boolean added=false;
    
    public Picture(String imgref) {
        added=false;
        imageName = imgref;
        img = new GreenfootImage(imgref);
        setImage(img);
        moveable=false;
    }
    public Picture(String imgref,boolean moveable) {
        added=false;
        imageName = imgref;
        img = new GreenfootImage(imgref);
        setImage(img);
        this.moveable=moveable;
        
    }

    public void act() {
        if(!added){
            added=true;
            x=getX();
            y=getY();
        }
        if(moveable){
            setLocation(x,y);
        }
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {

        this.imageName = imageName;
        this.setImage(imageName);
    }

}

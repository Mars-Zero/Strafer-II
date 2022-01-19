
import greenfoot.*;

public class Picture extends Menu {

    GreenfootImage img;
    String imageName;

    public Picture(String imgref) {
        imageName=imgref;
        img = new GreenfootImage(imgref);
        setImage(img);
    }

    public void act() {

    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}

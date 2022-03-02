import greenfoot.*;  


public class Light extends Lantern
{
    Lantern lantern;
    public Light(Lantern l){
        super(null);
        setImage("effects/light.png");
        lantern=l;
        
    }
    
    public void act() {
        if(!WorldData.PAUZA){
            setLocation(lantern.getX(),lantern.getY());
        }
    }    
}


import greenfoot.*;

public class PickUp extends Item {

    protected boolean picked = false;
    String name;
    GifImage gif;
    
    public PickUp(String name) {
        this.name = name;
        gif=new GifImage("item/pickUp/"+name+"PickUp.gif");
    }
    public PickUp(){}

    public void act() {
        if(!WorldData.PAUZA){
            setImage(gif.getCurrentImage());
            pick();
        }
    }

    protected void pick() {
        if (isTouching(Player.class)) {
            if (!picked) {
                WorldData.items.add(name);
                switch(name){
                    case "sword":{
                        WorldData.hasSword=true;
                        
                        break;
                    }
                    case "laser":{
                        
                        WorldData.hasLaser=true;
                        break;
                    }
                    case "blackhole":{
                        
                        WorldData.hasBlackHole=true;
                        break;
                    }
                    case "icelock":{
                        
                        WorldData.hasIceLock=true;
                        break;
                    }
                    case "lantern":{
                        
                        WorldData.hasLantern=true;
                        break;
                    }
                    case "portalgun":{
                        
                        WorldData.hasPortalGun=true;
                        break;
                    }
                }
                getWorld().addObject(new Tutorial("Items",name, 3,false), WorldData.menuX, WorldData.menuY);
                picked = true;
                getWorld().removeObject(this);
            }
        }
    }

    protected Player getPlayer() {
        return (Player) (getWorld().getObjects(Player.class).get(0));
    }
}

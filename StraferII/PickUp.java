import greenfoot.*;  

public class PickUp extends Item{
   
    protected boolean picked=false;
    
    public void act() {
        
    }
    
    protected void pick(){
        if(isTouching(Player.class)){
            picked=true;
        }
    }
    
    protected Player getPlayer(){
        return (Player)(getWorld().getObjects(Player.class).get(0));
    }
}

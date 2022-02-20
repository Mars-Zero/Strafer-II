import greenfoot.*;  


public class Pill extends PickUp{
    
    int hpToAdd;

   
    public Pill(){
        
    }
    
    protected void pick(){
    
        super.pick();
    }
    
    public void act() {
        
    }     
    public void setHpToAdd(int hpToAdd) {
        this.hpToAdd = hpToAdd;
    }
    
}

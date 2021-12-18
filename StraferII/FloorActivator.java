import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class FloorActivator extends Floor{
    int floorLevel; 
    int time;
     boolean hasInteracted;
     int playerX,playerY;
    String enterDirection;
     
    public FloorActivator(String enterDirection,//directia din care vine playeru cand urca pe floor
                          int floorLevel){
        
        this.floorLevel=floorLevel;
        this.enterDirection=enterDirection;
    
    }
    
     public void checkPlayer(){
        Player player = (Player)getWorld().getObjects(Player.class).get(0);
       
        
        switch(enterDirection){
            
            case "W":{    
            
                if(isTouching(Player.class)){
                    hasInteracted = true;
                    if(Player.gif=="W"){
                          Player.floorLevel=this.floorLevel;
                    }
                    if(player.gif=="S"){
                        
                    }
                }
                break;
            }
         
            
          //????????????????????????????????????????????????????
            case"A":{
            
                if(isTouching(Player.class)   ){
                    hasInteracted = true;
                    
                }
                break;
            }
            case"S":{
                
                if(isTouching(Player.class) ){
                    hasInteracted = true;
                    
                }
                break;
            }
            case"D":{
            
                if(isTouching(Player.class)  ){
                    hasInteracted = true;
                    
                }
                break;
            }
            
        }
    }
    
    public void act() {
        checkPlayer();
        if(hasInteracted){
            if(Player.floorLevel!=this.floorLevel){
                Player.floorLevel=this.floorLevel;
                hasInteracted=false;
            }
            if(Player.floorLevel==this.floorLevel){
                Player.floorLevel=1;
                hasInteracted=false;
            }
        }            
    }    
}



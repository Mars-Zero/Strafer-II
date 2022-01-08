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
    
     public void checkPlayer(Player player){
        
       
        
        switch(enterDirection){
            
            case "W":{    
            
                if(isTouching(Player.class)){
                    hasInteracted = true;
                    if(player.gif=="W"){
                          player.floorLevel=this.floorLevel;
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
        Player player = (Player)getWorld().getObjects(Player.class).get(0);
        checkPlayer(player);
        if(hasInteracted){
            if(player.floorLevel!=this.floorLevel){
                player.floorLevel=this.floorLevel;
                hasInteracted=false;
            }
            if(player.floorLevel==this.floorLevel){
                player.floorLevel=1;
                hasInteracted=false;
            }
        }            
    }    
}



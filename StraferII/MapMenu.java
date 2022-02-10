import greenfoot.*; 
import java.util.ArrayList;

public class MapMenu extends Pause{
  
    GreenfootImage img=new GreenfootImage("UI/mapMenu/mapMenu.png");
    boolean butoanead=false;
    PlayWorld playWorld;
    Player player;
    
    Picture markerPlayer=new Picture("UI/mapMenu/marker.png");
    Picture markerObjective=new Picture("UI/mapMenu/markerObjective.png");
    Text objectiveText;
    
    ArrayList<Actor> thingsToClear=new ArrayList<Actor>();
    
    ///worldsize/mapmenu size=33.3 (3% zoom)
    
    public MapMenu(PlayWorld playWorldref){
        setImage(img);
        playWorld=playWorldref;
        player=playWorld.getPlayer();
        
        
    }
    
    private void addButoane(){
        Buton buton=new Buton("X",this);
        playWorld.addObject(buton,1000,20);
    }
    
    private void addMarkers(){
        playWorld.addObject(markerPlayer, 0, -20);
        thingsToClear.add(markerPlayer);
        if(player.hasObjective()){
           //adauga marker acolo unde e obiectivul pe mapa
           //thingsToClear.add(markerObjective);
        }
    }
    
    private void addText(){
        if(player.hasObjective()){
            //adaug text cu obiectivul
            thingsToClear.add(objectiveText);
        }
    }
    
    private void addMapSections(){
        for(int i=1;i<=2;i++){
            for(int j=1;j<=3;j++){
                int ws=i*10+j;
                if(WorldData.visitedWorldSections[i][j]==true){
                    Picture pic=new Picture("UI/mapMenu/mapMenu"+ws+".png");
                    playWorld.addObject(pic,246*j -84,246*i -84);
                    thingsToClear.add(pic);
                }
            }
        }
        //playWorld.addObject(new Picture("UI/mapMenu/mapMenu"+11+".png"),246*3-122+39,246*2-122+39);
    }
    
    public void clear(){
        playWorld.removeObjects(thingsToClear);
    }
    
    public void act() {
        if(!butoanead){
            addButoane();
            addMapSections();
            addMarkers();
            addText();
            butoanead=true;
        }
    }    
    
    
    private int getMapMenuCoordinateX(int ws,int wx){   //sectiunea si dist globala pe sectiunea aia
        
        
        return 39;
    }
    private int getMapMenuCoordinateY(int ws,int wy){
        
        
        return 39;
    }
}

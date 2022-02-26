
import greenfoot.*;
import java.util.ArrayList;

public class MapMenu extends Pause {

    GreenfootImage img = new GreenfootImage("UI/mapMenu/mapMenu.png");
    boolean butoanead = false;
    PlayWorld playWorld;
    Player player;

    Picture markerPlayer = new Picture("UI/mapMenu/marker.png");
    Picture markerObjective = new Picture("UI/mapMenu/markerObjective.png");
    Text objectiveText;

    ArrayList<Actor> thingsToClear = new ArrayList<Actor>();

    ///worldsize/mapmenu size=33.3 (3% zoom)
    public MapMenu(PlayWorld playWorldref) {
        setImage(img);
        playWorld = playWorldref;
        player = playWorld.getPlayer();

    }

    private void addButoane() {
        Buton buton = new Buton("X", this);
        playWorld.addObject(buton, 1000, 20);
    }

    private void addMarkers() {
        int ws = playWorld.getWorldListener().getWorldSection();
        
        playWorld.addObject(markerPlayer, getMapMenuCoordinateX(ws), getMapMenuCoordinateY(ws) - 20);//adauga marker acolo unde e playerul pe mapa
        thingsToClear.add(markerPlayer);

        playWorld.addObject(markerObjective, getMapMenuObjectiveX(WorldData.objectiveWS), getMapMenuObjectiveY(WorldData.objectiveWS) - 20); //adauga marker acolo unde e obiectivul pe map
        thingsToClear.add(markerObjective);

    }

    private void addText() {

        objectiveText = new Text("\n"+WorldData.objective, 30);
        playWorld.addObject(objectiveText,1024,270);
        thingsToClear.add(objectiveText);

    }

    private void addMapSections() {
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 3; j++) {
                int ws = i * 10 + j;
                if (WorldData.visitedWorldSections[i][j] == true) {
                    Picture pic = new Picture("UI/mapMenu/mapMenu" + ws + ".png");
                    playWorld.addObject(pic, 247 * j - 84, 247 * i - 84);
                    thingsToClear.add(pic);
                }
            }
        }
        //playWorld.addObject(new Picture("UI/mapMenu/mapMenu"+11+".png"),246*3-122+39,246*2-122+39);
    }

    public void clear() {
        playWorld.removeObjects(thingsToClear);
    }

    public void act() {
        if (!butoanead) {
            addButoane();
            addMapSections();
            addMarkers();
            addText();
            butoanead = true;
        }
    }

    private int getMapMenuCoordinateX(int ws) {   //sectiunea si dist globala pe sectiunea aia
        int dist = 0;
        dist += (8196 * (ws % 10 - 1)) + player.getX() + playWorld.getScroller().getScrolledX();

        return dist / 33 + 39;
    }

    private int getMapMenuCoordinateY(int ws) {
        int dist = 0;
        dist += (8196 * (ws / 10 - 1)) + player.getY() + playWorld.getScroller().getScrolledY();

        return dist / 33 + 39;
    }

    private int getMapMenuObjectiveX(int ws) {
        int dist = 0;
        dist += (8196 * (ws % 10 - 1)) + WorldData.objectiveX;

        return dist / 33 + 39;
    }

    private int getMapMenuObjectiveY(int ws) {
        int dist = 0;
        dist += (8196 * (ws / 10 - 1)) + WorldData.objectiveY;

        return dist / 33 + 39;
    }
}

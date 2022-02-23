
import greenfoot.*;

public class LaserStroke extends NpcItem {

    Stroke stroke;
    GreenfootImage art = new GreenfootImage(512, 288);
    int xStart, yStart, dist;

    public LaserStroke(Stroke st, int dist) { ///ochi stang dist intre ochi
        stroke = st;
        this.xStart = stroke.getOchix();
        this.yStart = stroke.getOchiy();
        this.dist = dist;

        Color color = new Color(255, 11, 11);
        art.setColor(color);

        prepareLasers();

    }

    void prepareLasers() {
        int x1, y1, x2, y2;            //puncte de break
        int mod1, mod2;
        mod1 = Greenfoot.getRandomNumber(150);
        mod2 = Greenfoot.getRandomNumber(50);

        x1 = Greenfoot.getRandomNumber(75) + 75 - mod1;
        y1 = Greenfoot.getRandomNumber(25) + 25;
        x2 = Greenfoot.getRandomNumber(175) - mod2;
        y2 = Greenfoot.getRandomNumber(75) + y1;

        Player player = stroke.getPlayer();
        int xFin = (player.getX() - dist / 2) / 2;
        int yFin = (player.getY()) / 2;

        art.drawLine(xStart / 2, yStart / 2, x1, y1);
        art.drawLine(xStart / 2 + dist / 2, yStart / 2, x1 + dist / 2, y1);
        art.drawLine(x1, y1, x2, y2);
        art.drawLine(x1 + dist / 2, y1, x2 + dist / 2, y2);
        art.drawLine(x1, y1, x2, y2);
        art.drawLine(x1 + dist / 2, y1, x2 + dist / 2, y2);
        art.drawLine(x2, y2, xFin, yFin);
        art.drawLine(x2 + dist / 2, y2, xFin + dist / 2, yFin);

        art.scale(1024, 576);
        setImage(art);

    }

    public void act() {
        if (!WorldData.PAUZA) {
            setLocation(getX()-(xStart-stroke.getOchix()), getY()-(yStart-stroke.getOchiy()));
        }
    }
}

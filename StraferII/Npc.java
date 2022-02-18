
import greenfoot.*;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

class Pozitie {

    int lin, col, prec, dist;

    Pozitie(int x, int y, int prec, int dist) {
        this.lin = x;
        this.col = y;
        this.prec = prec;
        this.dist = dist;
    }
}

public class Npc extends Movers {

    int scrolledX;
    int scrolledY;
    Scroller scroller;

    protected final int rez = 64;           //pixeli intr o celula
    final int searchDistanceMax = 32;       //la cate celule distanta sa caute maxim

    protected String gif = "Idle";
    //protected static String[] ord=new String[121001];
    protected ArrayList<StringBuilder> ord = new ArrayList<>();
    protected boolean gasit = false;

    protected int worldX;//pozitia pe X pe mapa
    protected int worldY;//pozitia pe Y pe mapa
    protected int prevsx = Scroller.scrolledX;
    protected int prevsy = Scroller.scrolledY;

    protected int[][] matElem = new int[WorldData.maxLengthWorld + 1][WorldData.maxWidthWorld + 1]; //matricea copiata din nivelx

    protected int sectiune = -1;

    protected int getScrollX() {
        int nx = this.getX();
        return nx;
    }

    protected int getScrollY() {
        int ny = this.getY();
        return ny;
    }

    protected void updateScroll() {
        this.scrolledX = scroller.getScrolledX();
        this.scrolledY = scroller.getScrolledY();
    }

    public Npc(Scroller scrl) {
        scroller = scrl;
        this.scrolledX = scroller.getScrolledX();
        this.scrolledY = scroller.getScrolledY();

    }

    protected boolean checkPlayerInChunck() {
        if (getObjectsInRange(1024, Player.class) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void setLocation(int x, int y) {
        int a = x, b = y;
        super.setLocation(a, b);
    }

    @Override
    public String toString() {
        //the name of the class
        return this.getClass().getSimpleName();
    }

    protected void Lee(int startL, int startC, int x, int y) {
        //aici trebuie pusa matricea din WorldData
        if (sectiune == -1) {
            sectiune = WorldData.getWorldSectionShort((~((PlayWorld) getWorld()).getWorldListener().getWorldSection()));
        }
        if (WorldData.worldSectionMatrix[WorldData.getWorldSectionShort(sectiune)][x][y] == -1) {
            gasit = false;
            return;
        }

        int[][] mat = new int[WorldData.maxLengthWorld + 1][WorldData.maxWidthWorld + 1];
        for (int i = 0; i < WorldData.maxLengthWorld; i++) {
            for (int j = 0; j < WorldData.maxWidthWorld; j++) {
                mat[i][j] = WorldData.worldSectionMatrix[WorldData.getWorldSectionShort(sectiune)][i][j];
            }

        }

        ord.clear();

        final int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
        final int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};

        ArrayList<Pozitie> v = new ArrayList<>();

        v.add(new Pozitie(startL, startC, -1, 1));
        mat[startL][startC] = 1;

        int st = 0, dr = 0;
        if (startL == x && startC == y) {
            st = 1;
        }

        gasit = false;
        int d;
        while (st <= dr) {
            Pozitie start = v.get(st);
            int l = start.lin;
            int c = start.col;
            if (start.dist < searchDistanceMax) {
                for (int i = 0; i < 8; i++) {

                    if (mat[l + dx[i]][c + dy[i]] == 0 && l + dx[i] > 0 && c + dy[i] > 0 && l + dx[i] < 1000 && c + dy[i] < 1000) {
                        //nu am mai fost aici
                        //adaug in coada
                        dr++;
                        Pozitie elem = new Pozitie(l + dx[i], c + dy[i], st, start.dist + 1);
                        v.add(elem);
                        mat[elem.lin][elem.col] = mat[l][c] + 1;//marchez elementul ca parcurs
                        if (elem.lin == x && elem.col == y) {
                            //gasesc playerul
                            gasit = true;
                            break;
                        }
                    }
                }
                if (gasit == true) {
                    break;
                }
                st++;
            } else {
                gasit = false;
                return;
            }

        }

        //gasesc pathul
        int val = dr;
        while (gasit && v.get(val).prec != -1 && v.get(val).prec != -2) {
            ord.add(new StringBuilder());
            if (v.get(val).lin > v.get(v.get(val).prec).lin) {
                //am mers in sud
                StringBuilder idn = ord.get(ord.size() - 1);
                idn.append("S");
                ord.set(ord.size() - 1, idn);

            } else if (v.get(val).lin < v.get(v.get(val).prec).lin) {
                //am fost in nord
                StringBuilder idn = ord.get(ord.size() - 1);
                idn.append("W");
                ord.set(ord.size() - 1, idn);

            }

            if (v.get(val).col > v.get(v.get(val).prec).col) {
                //merg in est
                StringBuilder idn = ord.get(ord.size() - 1);
                idn.append("D");
                ord.set(ord.size() - 1, idn);

            } else if (v.get(val).col < v.get(v.get(val).prec).col) {
                //merg in vest
                StringBuilder idn = ord.get(ord.size() - 1);
                idn.append("A");
                ord.set(ord.size() - 1, idn);

            }

            val = v.get(val).prec;
        }

    }

    //
    //
    //
    //
    //
    ///knockback
    protected double viteza_frame_x;
    protected double viteza_frame_y;
    protected double distance_added;
    protected double grade_rezultanta;
    protected int sens_x = 1;
    protected int sens_y = 1;
    protected int xfinknockback, yfinknockback;
    protected double timp_knockback;
    protected int frameuri_trecute = 0; ///de cand incepe knockback

    protected void knockback(double timp, Actor attacker, double masa_attacker, double masa_this) {

        this.timp_knockback = timp;

        double delta_x = this.getX() / 64.0 - attacker.getX() / 64.0;                            // impartit la 64 pt convert  din pixeli in metri
        double delta_y = this.getY() / 64.0 - attacker.getY() / 64.0;

        sens_x = 1;
        sens_y = 1;
        if (delta_x < 0) {
            sens_x = -1;
        }
        if (delta_y < 0) {
            sens_y = -1;
        }

        double grade_attack = Math.toDegrees(Math.atan2(delta_y, delta_x));                  //alfa

        double forta_attack = masa_attacker;
        double forta_greutate = masa_this * 9.8;

        double rezultanta = Math.sqrt(forta_attack * forta_attack + forta_greutate * forta_greutate
                - 2 * forta_attack * forta_greutate * Math.sin(grade_attack));

        double grade_attack_greutate = 3.14159 / 2 - Math.asin(-1 * Math.sin(grade_attack));                   //beta

        grade_rezultanta = 90 - (grade_attack_greutate - grade_attack);                     //epsilon

        double acceleratie_this = rezultanta / masa_this;

        distance_added = acceleratie_this * 64 * timp * timp / 2;              //legea miscarii

        xfinknockback = (int) (getX() + distance_added * Math.cos(grade_rezultanta)); //unde trebe sa ajunga dupa knockback
        yfinknockback = (int) (getY() + distance_added * Math.sin(grade_rezultanta));

        double fractiune = 60 / (timp * 1000);

        viteza_frame_x = sens_x * 2 * acceleratie_this * distance_added * fractiune;//*64;           //distanta cu care e miscat pe fiecare frame pana in pozitia de knockback
        viteza_frame_y = sens_y * 2 * acceleratie_this * distance_added * fractiune;
    }

    public void act() {

    }
}

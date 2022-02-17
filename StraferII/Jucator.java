
import greenfoot.*;

public class Jucator extends Movers {

    protected void atingeNpc() {
        Actor a = getOneIntersectingObject(Npc.class);//daca sunt in exterior
        if (a != null) {
            Movers m = (Movers) a;
            if (!m.isKnockbacked()) {
                int procentX = 50;
                int procentY = 60;

                //am un npc care mi se atinge cu playerul
                double npcX = (a.getX() - getX()) * procentX / 100;//8/100
                double npcY = (a.getY() - getY()) * procentY / 100;//7/100   

                //setLocation(getX() - (int)npcX, getY() - (int)npcY);
                if (a.getX() - getX() <= 0) {
                    setLocation(getX() + 7, getY());
                }
                if (a.getX() - getX() >= 0) {
                    setLocation(getX() - 7, getY());
                }
                if (a.getY() - getY() <= 0) {
                    setLocation(getX(), getY() + 7);
                }
                if (a.getY() - getY() >= 0) {
                    setLocation(getX(), getY() - 7);
                }

            }
        }

        Actor b = getOneObjectAtOffset(0, 0, Npc.class);//daca sunt in interior\
        if (b != null) {
            Movers mb = (Movers) b;
            if (!mb.isKnockbacked()) {

                int npcX = (b.getX() - getX());//8/100
                int npcY = (b.getY() - getY());//7/100

                setLocation(getX() - npcX, getY() - npcY);
            }
        }
    }

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

    protected void knockbackMove() {//boolean knockbacked) {

        if (knockbacked) {
            this.frameuri_trecute++;
            setLocation((int) (getX() + viteza_frame_x * Math.cos(grade_rezultanta)), (int) (getY() + viteza_frame_y * Math.sin(grade_rezultanta)));

            if (this.frameuri_trecute >= this.timp_knockback * 60) {
                this.frameuri_trecute = 0;
                knockbacked = false;
            }
        }

    }

    public void act() {

    }
}

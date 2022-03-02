
import greenfoot.*;

public class Movers extends Actor {

    protected boolean knockbacked = false;

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

    public boolean isKnockbacked() {
        return knockbacked;
    }

    public void act() {

    }
}

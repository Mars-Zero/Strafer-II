import greenfoot.*;  

public class Movers extends Actor{

    /**
     * TODO: Doctore, pune JavaDoc pe metodele importante
     */
    protected void knockback(float timp,Actor attacker, double masa_attacker, double masa_this){
        
        Vector2d    vector_this=       new Vector2d(this.getX(),this.getY());
        Vector2d    vector_attacker=   new Vector2d(attacker.getX(),attacker.getY());
        
        double delta_x = this.getX()/64.0 - attacker.getX()/64.0;                            // impartit la 64 pt convert  din pixeli in metri
        double delta_y = this.getY()/64.0 - attacker.getY()/64.0;
        
        double grade_attack=Math.toDegrees( Math.atan2(delta_y, delta_x));                  //alfa
        
        double forta_attack=masa_attacker;
        double forta_greutate=masa_this * 9.8;  
        
        
        double rezultanta=Math.sqrt(    forta_attack*forta_attack +  forta_greutate*forta_greutate
                                        -2* forta_attack* forta_greutate  *Math.sin(grade_attack));
        
        
        double grade_attack_greutate=Math.acos(-1*Math.sin(grade_attack));                   //beta
        
        double grade_rezultanta=90-(grade_attack_greutate-grade_attack);                     //epsilon
        
        double acceleratie_this=rezultanta/masa_this;
        
        
        double distance_added=acceleratie_this* timp* timp/2;              //legea miscarii
        
        double fractiune=WorldData.FPS/timp;
        
        double viteza_frame=Math.sqrt(2* acceleratie_this* distance_added) *fractiune*64;           //distanta cu care e miscat pe fiecare frame pana in pozitia de knockback
        
    }
    
    public void act() {
        
        
    }    
}


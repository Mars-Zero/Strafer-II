import greenfoot.*;  
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;


class Pozitie{
    int lin, col, prec,dist;
    Pozitie(int x, int y, int prec,int dist){
        this.lin=x;
        this.col=y;
        this.prec=prec;
        this.dist=dist;
    }
}

public class Npc extends Movers
{
    int scrolledX;
    int scrolledY;
    Scroller scroller;
    
    protected final int rez=64;
    final int dMax=16;
    
    protected String gif="Idle";
    //protected static String[] ord=new String[121001];
    protected ArrayList<StringBuilder> ord=new ArrayList<>();
    protected boolean gasit=false;
    
    protected int worldX;//pozitia pe X pe mapa
    protected int worldY;//pozitia pe Y pe mapa
    protected int prevsx=Scroller.scrolledX;
    protected int prevsy=Scroller.scrolledY;
     
     public static int[][] matElem=new int[128][128]; //matricea copiata din nivelx
     
     
     
     
     protected int getScrollX(){
         int nx=this.getX();
         return nx;
     }
     protected int getScrollY(){
         int ny=this.getY();
         return ny;
     }
      protected void updateScroll(){
        this.scrolledX=scroller.getScrolledX();
        this.scrolledY=scroller.getScrolledY();
    }
     
     public Npc(Scroller scrl){
          scroller=scrl;
        this.scrolledX=scroller.getScrolledX();
        this.scrolledY=scroller.getScrolledY();
     }
     
     protected boolean checkPlayerInChunck(){
        if(getObjectsInRange(1024,Player.class)!=null){
            return true;
        }
        return false;
     }
     
    
     @Override public void setLocation(int x,int y){
         int a=x,b=y;
         super.setLocation( a,b);
     }
     
    protected void Lee(int startL, int startC, int x, int y){
        int[][] mat=new int[1000][1000];
        for(int i=0; i<1000; i++){
          for(int j=0; j<1000; j++){
            mat[i][j]=0;
           }
        }
        ord.clear();
            

        final int[] dy={1,-1,0,0,-1,1,1,-1};
        final int[] dx={0,0,1,-1,-1,1,-1,1};
        
        ArrayList<Pozitie> v=new ArrayList<>();
       
        v.add(new Pozitie(startL,startC,-1,1));
        mat[startL][startC]=1;
       
        int st=0,dr=0;
        if(startL==x && startC==y){
            st=1;
        }
        
        gasit=false;
        while(st<=dr){
            Pozitie start=v.get(st);
            int l=start.lin;
            int c=start.col;
            if(start.dist<dMax) {
                for(int i=0; i<8; i++){

                    if(mat[l+dx[i]][c+dy[i]]==0 && l+dx[i]>0 && c+dy[i]>0 &&l+dx[i]<1000 && c+dy[i]<1000 ){
                        //nu am mai fost aici
                        //adaug in coada
                        dr++;
                        Pozitie elem=new Pozitie(l+dx[i],c+dy[i],st,start.dist+1);
                        v.add(elem);
                        mat[elem.lin][elem.col]=mat[l][c]+1;//marchez elementul ca parcurs
                        if(elem.lin==x && elem.col==y){
                            //gasesc playerul
                            gasit=true;
                            break;
                        }
                    }
                }
                if(gasit==true){
                    break;
                }
                st++;
            }

        }
        
        //gasesc pathul
        int val=dr;
        while(gasit && v.get(val).prec!=-1 && v.get(val).prec!=-2){
            ord.add(new StringBuilder());
            if(v.get(val).lin>v.get(v.get(val).prec).lin)
                {
                //am mers in sud
                StringBuilder idn=ord.get(ord.size()-1);
                idn.append("S");
                ord.set(ord.size()-1, idn);
                
            }
            else if(v.get(val).lin<v.get(v.get(val).prec).lin)
            {
                //am fost in nord
                StringBuilder idn=ord.get(ord.size()-1);
                idn.append("W");
                ord.set(ord.size()-1, idn);
                

            }
                
                if(v.get(val).col>v.get(v.get(val).prec).col)
                {
                //merg in est
                    StringBuilder idn=ord.get(ord.size()-1);
                    idn.append("D");
                    ord.set(ord.size()-1, idn);
                    
                }
               else if(v.get(val).col<v.get(v.get(val).prec).col)
               {
                //merg in vest
                   StringBuilder idn=ord.get(ord.size()-1);
                   idn.append("A");
                   ord.set(ord.size()-1, idn);
                   
               }
            
           val=v.get(val).prec;
        }
     
    }
    
    
    
    ///knockback
    protected double viteza_frame;
    protected double distance_added;
    protected double grade_rezultanta;
    protected int xfinknockback, yfinknockback;
    protected double timp_knockback;
    protected int frameuri_trecute = 0; ///de cand incepe knockback

    protected void knockback(double timp, Actor attacker, double masa_attacker, double masa_this) {

       
        this.timp_knockback = timp;

       // Vector2d vector_this = new Vector2d(this.getX(), this.getY());
       // Vector2d vector_attacker = new Vector2d(attacker.getX(), attacker.getY());

        double delta_x = this.getX() / 64.0 - attacker.getX() / 64.0;                            // impartit la 64 pt convert  din pixeli in metri
        double delta_y = this.getY() / 64.0 - attacker.getY() / 64.0;

        double grade_attack = Math.toDegrees(Math.atan2(delta_y, delta_x));                  //alfa

        double forta_attack = masa_attacker;
        double forta_greutate = masa_this * 9.8;

        double rezultanta = Math.sqrt(forta_attack * forta_attack + forta_greutate * forta_greutate
                - 2 * forta_attack * forta_greutate * Math.sin(grade_attack));

        double grade_attack_greutate = Math.acos(-1 * Math.sin(grade_attack));                   //beta

        grade_rezultanta = 90 - (grade_attack_greutate - grade_attack);                     //epsilon

        double acceleratie_this = rezultanta / masa_this;

        distance_added = acceleratie_this*64 * timp * timp / 2;              //legea miscarii

        xfinknockback = (int) (getX() + distance_added * Math.cos(grade_rezultanta)); //unde trebe sa ajunga dupa knockback
        yfinknockback = (int) (getY() + distance_added * Math.sin(grade_rezultanta));

        double fractiune = 60 / (timp*1000);

        viteza_frame = 2* acceleratie_this * distance_added* fractiune ;//*64;           //distanta cu care e miscat pe fiecare frame pana in pozitia de knockback

    }

    
    
    
   
    public void act() 
    {
        
       
    }    
}

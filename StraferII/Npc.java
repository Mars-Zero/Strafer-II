import greenfoot.*;  
import java.util.ArrayDeque;
import java.util.ArrayList;
/**
 * Write a description of class Npc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class Pozitie{
    int lin, col, prec;
    Pozitie(int x, int y, int prec){
        this.lin=x;
        this.col=y;
        this.prec=prec;
    }
}

public class Npc extends Actor
{
    protected static final int rez=60;
    
    protected String gif="Idle";
    protected static String[] ord=new String[203];
    protected static int pasi=1;
     protected static boolean gasit=false;
     
     public static int[][] matElem=new int[11][23]; //matricea copiata din nivelx
     
     
    protected static void Lee(int startL, int startC, int x, int y){
        int[][] mat=new int[11][23];
        for(int i=0; i<11; i++)
        {
          for(int j=0; j<23; j++){
            mat[i][j]=matElem[i][j];
           }
        }
            for(int i=0;i<203;i++)
            {
            ord[i]=" ";
            }
            

        final int[] dy={1,-1,0,0,-1,1,1,-1};
        final int[] dx={0,0,1,-1,-1,1,-1,1};
        
        
        Pozitie[] v=new Pozitie[203];
        v[0]=new Pozitie(startL,startC,-1);
        mat[startL][startC]=1;
       
        int st=0,dr=0;
        if(startL==x && startC==y)
        {
            pasi=1;
            st=1;
        }
        
        gasit=false;
        while(st<=dr)
        {
            Pozitie start=v[st];
            int l=start.lin;
            int c=start.col;
            
            
            for(int i=0; i<8; i++)
            {
                
                if(mat[l+dx[i]][c+dy[i]]==0)
                {
                    //nu am mai fost aici
                    //adaug in coada
                    dr++;
                    Pozitie elem=new Pozitie(l+dx[i],c+dy[i],st);
                    v[dr]=elem;
                    mat[elem.lin][elem.col]=mat[l][c]+1;//marchez elementul ca parcurs
                    if(elem.lin==x && elem.col==y)
                    {
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
        
        //gasesc pathul
        int val=dr;
        pasi=1;
        while(v[val].prec!=-1 && v[val].prec!=-2)
        {
            ord[pasi]="";
            if(v[val].lin>v[v[val].prec].lin)
                {
                //am mers in sud
                ord[pasi]+="S";
                
                }
                else if(v[val].lin<v[v[val].prec].lin)
                {
                //am fost in nord
                ord[pasi]+="W";
                }
                
                if(v[val].col>v[v[val].prec].col)
                {
                //merg in est
                ord[pasi]+="D";
                }
               else if(v[val].col<v[v[val].prec].col)
               {
                //merg in vest
                ord[pasi]+="A";
               }
            
           pasi++;
           val=v[val].prec;
        }
    }
   
    public void act() 
    {
    
    }    
}

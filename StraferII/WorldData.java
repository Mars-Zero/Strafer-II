
public class WorldData {

    public static boolean PAUZA;
    public static final int WIDTH = 1024, HIGHT = 576;
    public static final int FPS = 60;
    
    public static boolean addedDialogs = false;

    public static int menuX = 512; //  pt adaugat meniuri in mijlocu ecranului
    public static int menuY = 288;

    
    
    public static boolean[][] visitedWorldSections={{false,false,false,false},{false,false,false,false},{false,false,false,false}};
    
    /**
     * Numarul maxim de sectiuni de pe o linie
     */
    public static final int numberOfCollumns=3;
    public static int getWorldsection(int world)
    {
        int col=world%10;
        int lin=world/10;
        int sol=(lin-1)*numberOfCollumns+col;
        if(sol<0){
            sol=1;
        }
        return sol;
    }
}

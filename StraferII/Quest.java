import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Quest extends EventSystem{
    
    /**
     * The event queue represents the quest in cronological order
     */
    Queue<Event> eventQueue= new LinkedList<>();
    
    /**
     * The name of the quest
     */
    String questName;
    
     /**
     * This method loads the whole quest from a file
     */
    public void load(File file) {
        try {
            file.createNewFile();
            Scanner fin = new Scanner(file, "UTF-8");
            
            int n=0;
            int val=0;
            String action;
            String cod=null;
            while (fin.hasNext()) {
                String str = fin.nextLine();
                if(n==0)
                {
                    this.questName=str;
                    n++;
                }
                else{
                    if(val==0){
                        cod=str;
                    }
                    else{
                       eventQueue.add(new Event(cod,str));
                       System.out.println(cod);
                        System.out.println(str);
                       val=-1;
                    }
                    val++;
                }
              
            }
           fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean isRelevantEvent(Event event)
    {
        if(eventQueue.peek().isEqual(event))
        {
            eventQueue.poll();
            return true;
        }
        return false;
    }
    
    public String nextToDo(){
        return eventQueue.peek().toDo();
    }
    
    public boolean isFinished() {
        return eventQueue.size()==0;
    }
}

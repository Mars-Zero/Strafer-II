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
     * The action queue represents what actions need to be taken
     * based on the event present
     */
    Queue<EventForcedAction> actionsQueue= new LinkedList<>();
    
    /**
     * The name of the quest
     */
    String questName;
    
    /**
     * The number of the current event in the original queue
     */
    private int currentEventNumber=0;
    
     /**
     * This method loads the whole quest from a file
     */
    public void load(File file) {
        try {
            file.createNewFile();
            Scanner fin = new Scanner(file, "UTF-8");
            
            currentEventNumber=0;
            int n=0;
            int val=0;
            String action;
            String cod=null;
            String displayString=null;
            boolean eventsNeedToBeProccessed=false;
            while (fin.hasNext()) {
                String str = fin.nextLine();
                if(str.equalsIgnoreCase("*"))
                {
                    eventsNeedToBeProccessed=true;
                }
                else{
                    if(!eventsNeedToBeProccessed)
                    {
                        if(n==0)
                        {
                            this.questName=str;
                            n++;
                        }
                        else{
                           String[] arr=str.split(" ");  
                           int numberEvent=Integer.parseInt(arr[0]);
                           int numberSlides=Integer.parseInt(arr[3]);
                           EventForcedAction actioin=new EventForcedAction(numberEvent,new Tutorial(arr[1],
                           arr[2],numberSlides,false));
                        }
                    }
                    else{
                        if(val==0){
                        cod=str;
                        }
                         else if(val==1){
                           displayString=str;
                      
                        }
                        else{
                       String[] arr=str.split(" ");
                       int ws=Integer.parseInt(arr[0]);
                       int pX=Integer.parseInt(arr[1]);
                       int pY=Integer.parseInt(arr[2]);
                       eventQueue.add(new Event(cod,displayString,ws,pX,pY));
                       System.out.println(cod);
                       System.out.println(str);
                       val=-1;
                        }
                        val++;
                    }
              
                }
            }
           fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean isRelevantEvent(Event event,PlayWorld world)
    {
        if(eventQueue.peek().isEqual(event))
        {
            eventQueue.poll();
            currentEventNumber++;
            findForcedAction(world);
            return true;
        }
        return false;
    }
    /**
     * This method checks whether the event that was finished should trigger
     * a forced/scripted action
     */
    void findForcedAction(PlayWorld world){
        if(actionsQueue.peek().getEvent()==currentEventNumber)
        {
            actionsQueue.poll();
        }
    }
    public String nextToDo(){
        return eventQueue.peek().toDo();
    }
    
    public boolean isFinished() {
        return eventQueue.size()==0;
    }
}

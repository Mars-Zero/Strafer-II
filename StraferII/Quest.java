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
            while (fin.hasNext()) {
                String str = fin.nextLine();
                if(n==0)
                {
                	this.questName=str;
                	n++;
                }
                eventQueue.add(new Event(str));
              
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

	public boolean isFinished() {
		return eventQueue.size()==0;
	}
}

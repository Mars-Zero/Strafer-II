
public class Event extends EventSystem{
     
     /**
      * A code that identifies this event 
      * It should be a combination of the type and some other metric based on type
      * 
      */
     String eventCode;
     /**
      * A string that displays what the player needs to do
      */
     String action;
     public String getEventCode() {
        return eventCode;
    }
    
    /**
     * The coordinates of this event
     */
    private int worldSection,positionX,positionY;
    public int getWorldSection() {
		return worldSection;
	}
    public void setWorldSection(int worldSection) {
		this.worldSection = worldSection;
    }

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
    /**
      * The type of event
      * 1- event thrown by a dialogue with some npc
      * 2- event thrown by what the player is doing(ex. combat state, wandering etc)
      * 3- event thrown by the a object
      */
     public Event(int tip,String objectCode)
     {
         int type=tip;
         this.eventCode=tip+objectCode;
     }
     
     /**
      * A constructor used to load an event by using directly it's code
      * @param eventCode
      */
     public Event(String eventCode,String toDo,int worldSec,int pX,int pY)
     {
         this.action=toDo;
         this.eventCode=eventCode;
         this.worldSection=worldSec;
         this.positionX=pX;
         this.positionY=pY;
     }
     
     public String toDo(){
         return action;
     }
     public boolean isEqual(Event event)
     {
         return eventCode.equalsIgnoreCase(event.getEventCode());
     }
}


public class Event {
	 
     /**
      * A code that identifies this event 
      * It should be a combination of the type and some other metric based on type
      * 
      */
     String eventCode;
     
     public String getEventCode() {
		return eventCode;
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
}

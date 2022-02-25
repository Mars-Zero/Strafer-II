import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class EventForcedAction extends EventSystem
{
    /**
     * A cutscene or a tutorial that needs to be displayed
     */
  Tutorial action;
   
   /**
    * The number of the event which throws this event
    */
   int event;
   
   public EventForcedAction(int e,Tutorial tut)
   {
       this.event=e;
       this.action=tut;
   }
   
   /**
    * Returns the event index
    */
   public int getEvent()
   {
       return event;
   }
   
   /**
    * Executes the scripted action
    */
   public void scriptedAction(PlayWorld world){
       world.initObject(action, WorldData.menuX, WorldData.menuY);
   }
}

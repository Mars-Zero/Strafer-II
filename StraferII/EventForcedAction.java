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
}

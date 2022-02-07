import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class contains all the quests relevant for the game
 * 
 * @author razva
 *
 */
public class MainStoryline extends Storyline{
    /**
     * The event queue represents all the quests that need to be completed to
     * finalize this storyline
     */
    Queue<Quest> questQueue = new LinkedList<>();

    public MainStoryline() {
        try (Stream<Path> walk = Files.walk(Paths.get("quests/mainQuest"))) {
            // We want to find only regular files
            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

            for (String string : result) {
                Quest toAdd = new Quest();
                toAdd.load(new File(string));
                questQueue.add(toAdd);
            }
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void isRelevantEvent(Event event) {
        if (questQueue.peek().isRelevantEvent(event)) {
            if (questQueue.peek().isFinished()) {
                questQueue.poll();
            }
        }
    }

    public void act() {
        //aici vor venii diferite eventuri
        Quest questCurent=questQueue.peek();
        String currentToDo=questCurent.nextToDo();
    }
}

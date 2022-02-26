import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import greenfoot.*;

/**
 * This class caches all images,gifs and sounds and returns them when needed
 * 
 * @author Mars 
 */
abstract class AssetsCache  {
    private static HashMap<String, GifImage> mapGifs = new HashMap<String, GifImage>();
    private static HashMap<String, GreenfootImage> mapImages = new HashMap<String, GreenfootImage>();
    private static HashMap<String, GreenfootSound> mapAudio = new HashMap<String, GreenfootSound>();
    
    /**
     * This method returns the extension of the file
     */
    public static Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	} 
    
    public static void storeAssets(){
        try (Stream<Path> walk = Files.walk(Paths.get("images"))) {
            // We want to find only regular files
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            
            for (String string : result) {
            	Optional<String> extension=getExtensionByStringHandling(string);
            	if(extension.get().equalsIgnoreCase("png")) {
            	    mapImages.put(string,new GreenfootImage(string));
            	}
            	else if(extension.get().equalsIgnoreCase("gif"))
            	{
            	    mapGifs.put(string,new GifImage(string));
                }
                else if(extension.get().equalsIgnoreCase("mp3"))
            	{
            	    mapAudio.put(string,new GreenfootSound(string));
                }
	    }
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

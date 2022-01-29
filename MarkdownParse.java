// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextClosedBracOpenParen = markdown.indexOf("](", nextOpenBracket);
            int closeParen = markdown.indexOf(")", nextClosedBracOpenParen);

            if (nextOpenBracket == -1 || nextClosedBracOpenParen == -1 || closeParen == -1) {
                return toReturn;
            }
            
            currentIndex = closeParen + 1;
            
            if (nextOpenBracket == 0 || markdown.charAt(nextOpenBracket - 1) != '!') {
                toReturn.add(markdown.substring(nextClosedBracOpenParen + 2, closeParen));
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}

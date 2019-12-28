import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataReader {

    private Map<String,Word> words=new HashMap<>();
    public DataReader() {
    }

    public void loadWords (Path path)  {
        String type;
        if(path.toString().contains("ham")) {
            type="ham";
        } else {
            type = "spam";
        }
        try {
            System.out.println(path.toString());
            Files.lines(path).forEach((String line) ->{
                String [] words=line.replaceAll("Subject: ","").replaceAll("re:","").replaceAll(":","").split(" ");
                for(String word:words) {
                    Word w =new Word();
                    if(this.words.containsKey(word)){
                       this.words.get(word).increaseSpamHam(type);
                       this.words.get(word).calculateProbability();
                    }else {
                        w.setWord(word);
                        w.increaseSpamHam(type);
                        w.calculateProbability();
                        this.words.put(word,w);
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("Error reading file");
        }
        words.remove("");
        words.remove("\u0007");
        words.remove("\u000F");
        words.remove("\u0012");
        words.remove("\u0001");
    }
    public void print () {

        System.out.println("###############################");
        for (Map.Entry<String, Word> entry : words.entrySet()) {
            System.out.println(entry.getValue().getWord());

        }
        System.out.println(words.size());
    }
}

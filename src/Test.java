import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    int corrects=0;
    int filenum=0;
    private int hamwords;
    private int spamwords;

    public Test() {

    }

    public void checkWords(Path path, Train train) {
        hamwords = 0;
        spamwords = 0;


        String type;

        try {
            List<String> lines = Files.readAllLines(path); //WARN
            System.out.println("I'm in");
            filenum++;
            if (path.toString().contains("ham")) {
                type = "ham";
            } else {
                type = "spam";
            }
            Map<String,Word> words=train.getWords();
            for (String line : lines) {
                String[] term = line.replaceAll("Subject: ", "").replaceAll("re:", "").replaceAll(":", "").replaceAll("[\u0000-\u001f]", "").split(" ");
                for (String word : term) {
                    if (train.words.containsKey(word)){
                        Word w = new Word();
                        for (Map.Entry<String, Word> entry : words.entrySet()){
                            if (entry.getKey().equals(word)){
                                w=entry.getValue();
                                break;
                            }
                        }
                        if(w.getSprob()<w.getHprob()){
                            hamwords++;
                        } else {
                            spamwords++;
                        }
                    }else {
                        spamwords ++;
                    }
                }

            }
            System.out.println(spamwords + " "+hamwords);
            if (hamwords>spamwords){
                corrects++;
            }

        } catch (IOException e) {
            System.err.println("Error reading file");
            System.out.println(path.toString());
        }
    }
}

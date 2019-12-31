import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Train {

    private int totalSpam=0;
    private int totalHam=0;

    public Map<String,Word> words=new HashMap<>();

    Train() { }

    public Map<String, Word> getWords() {
        return words;
    }

    void loadWords(Path path) {
        String type;

        try {
            List<String> lines = Files.readAllLines(path);
            if(path.toString().contains("ham")) {
                type="ham";
                totalHam++;
            } else {
                type = "spam";
                totalSpam++;
            }

            for(String line :lines) {
                String[] words = line.replaceAll("Subject: ", "").replaceAll("re:", "").replaceAll(":", "").replaceAll("[\u0000-\u001f]", "").split(" ");
                for (String word : words) {
                    Word w = new Word();
                    if (this.words.containsKey(word)) {
                        this.words.get(word).increaseSpamHam(type);
                    } else {
                        w.setWord(word);
                        w.increaseSpamHam(type);
                        this.words.put(word, w);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file");
            System.out.println(path.toString());
        }

        for (Map.Entry<String, Word> entry : words.entrySet()) {
            entry.getValue().spamProb(totalSpam,totalHam);
            entry.getValue().hamProb(totalSpam,totalHam);
        }
        words.remove("");
    }
    public void print () {

        for (Map.Entry<String, Word> entry : words.entrySet()) {
            System.out.println(entry.getValue().getWord() +" spamPro:" +entry.getValue().getSpamProb() +" ham:" +entry.getValue().getHamProb()+" Count spam " + entry.getValue().getCountSpam() + " Count ham " +entry.getValue().getCountHam());

        }
        System.out.println(words.size());
    }
}

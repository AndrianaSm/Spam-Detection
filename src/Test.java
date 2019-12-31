import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Test {

    private int corrects=0;
    private int files=0;
    private float hamWords;
    private float spamWords;
    private String type;
    private String testType;
    private int count=0;

    int correctSpam=0;
    int correctHam=0;


    public Test() {}

    public void checkWords(Path path, Train train) {
        hamWords = 0;
        spamWords = 0;

        try {
            List<String> lines = Files.readAllLines(path);
            files++;
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
                        Word w=words.get(word);
                        hamWords+=w.getHamProb();
                        spamWords+=w.getSpamProb();
                        count++;
                    }else {
                        spamWords+=0.99f;
                        count++;
                    }
                }
            }

            if(spamWords>hamWords) {
                testType="spam";
            }else {
                testType ="ham";
            }
            if (Objects.equals(type, testType)) {
                corrects++;
                if(testType.equals("spam")) {
                    correctSpam++;
                }else {
                    correctHam++;
                }

             }

        } catch (IOException e) {
            System.err.println("Error reading file");
            System.out.println(path.toString());
        }
    }

    public int getCorrects() {
        return corrects;
    }

    public void setCorrects(int corrects) {
        this.corrects = corrects;
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public float getHamWords() {
        return hamWords;
    }

    public void setHamWords(float hamWords) {
        this.hamWords = hamWords;
    }

    public float getSpamWords() {
        return spamWords;
    }

    public void setSpamWords(float spamWords) {
        this.spamWords = spamWords;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

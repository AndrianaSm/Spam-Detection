import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Test {

    private int files = 0;
    private float ProbOfSpam;
    private float ProbOfHam;
    private String type;
    private int tn=0,tp=0,fn=0,fp=0;

    Test() { }

    void checkWords(Path path, Train train) {
        ProbOfSpam = 0;
        ProbOfHam = 0;

        try {
            List<String> lines = Files.readAllLines(path);
            files++;
            Map<String, Word> words = train.getWords();
            for (String line : lines) {
                String[] term = line.replaceAll("Subject: ", "").replaceAll("re:", "").replaceAll(":", "").replaceAll("[\u0000-\u001f]", "").split(" ");
                for (String word : term) {
                    if (train.words.containsKey(word)) {
                        Word w = words.get(word);
                        ProbOfHam += w.getHamProb();
                        ProbOfSpam += w.getSpamProb();
                    } else {
                        ProbOfSpam += 0.99f;
                    }
                }
            }

            if (ProbOfSpam > ProbOfHam) {
                type = "spam";
            } else {
                type = "ham";
            }

        } catch (IOException e) {
//            System.err.println("Error reading file");
//            System.out.println(path.toString());
        }
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public String getTestType() {
        return type;
    }

    public void setTestType(String testType) {
        this.type = testType;
    }

    public float getProbOfSpam() {
        return ProbOfSpam;
    }

    public void setProbOfSpam(float probOfSpam) {
        ProbOfSpam = probOfSpam;
    }

    public float getProbOfHam() {
        return ProbOfHam;
    }

    public void setProbOfHam(float probOfHam) {
        ProbOfHam = probOfHam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTn() {
        return tn;
    }

    public void setTn(int tn) {
        this.tn = tn;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getFn() {
        return fn;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }

    public int getFp() {
        return fp;
    }

    public void setFp(int fp) {
        this.fp = fp;
    }

    public void increaseTn(){
        tn++;
    }
    public void increaseTp(){
        tp++;
    }
    public void increaseFn(){
        fn++;
    }
    public void increaseFp(){
        fp++;
    }
}



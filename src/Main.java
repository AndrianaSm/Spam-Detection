import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        Train train = new Train();

        train_call("data/Enron-Spam/enron1/ham", train);
        train_call("data/Enron-Spam/enron1/spam", train);


        train.print();

        Test test = new Test();

        test_call("data/Enron-Spam/enron2/spam", test, train);
        test_call("data/Enron-Spam/enron2/ham", test, train);
        System.out.println(test.corrects + " out of " + test.filenum);

    }


    public static void train_call(String loc, Train train){
        File folder = new File(loc);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Path path = Paths.get(file.getPath());
                        train.loadWords(path);
                }
            }
        }
    }

    public static void test_call(String loc, Test test, Train train){
        File folder = new File(loc);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Path path = Paths.get(file.getPath());
                    test.checkWords(path, train);
                }
            }
        }
    }

}

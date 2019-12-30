import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        Train train = new Train();

        File folder = new File("data/Enron-Spam/enron1/ham");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                 if (file.isFile()) {
                    Path path = Paths.get(file.getPath());
                    train.loadWords(path);

                }
            }
        }
        folder = new File("data/Enron-Spam/enron1/spam");
        listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Path path = Paths.get(file.getPath());
                    train.loadWords(path);

                }
            }
        }

        train.print();

    }

}

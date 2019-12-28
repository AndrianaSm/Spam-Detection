import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){

        DataReader data = new DataReader();

        File folder = new File("/Users/Adriana/Desktop/AI/Spam-Detection/data/Enron-Spam/enron1/spam");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
             if (file.isFile()) {
                Path path = Paths.get(file.getPath());
                data.loadWords(path);


            }
        }
        data.print();
    }

}

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        double accuracy,recall,precision,f=0.0;

        Train train = new Train();
        Test test = new Test();

        train_call("data/Enron-Spam/enron2/ham", train);
        train_call("data/Enron-Spam/enron2/spam", train);
//        train_call("data/Enron-Spam/enron3/ham", train);
//        train_call("data/Enron-Spam/enron3/spam", train);
//        train_call("data/Enron-Spam/enron4/ham", train);
//        train_call("data/Enron-Spam/enron4/spam", train);
//        train_call("data/Enron-Spam/enron5/ham", train);
//        train_call("data/Enron-Spam/enron5/spam", train);
//        train_call("data/Enron-Spam/enron6/ham", train);
//        train_call("data/Enron-Spam/enron6/spam", train);

        test_call("data/Enron-Spam/enron1/spam", test, train,"results_for_spam.txt");
        test_call("data/Enron-Spam/enron1/ham", test, train,"results_for_ham.txt");

        double x,y;

        x=test.getTp()+test.getTn();
        y=test.getTn()+test.getFn()+test.getFp()+test.getTp();
        accuracy=x/y;

        x=test.getTp();
        y=test.getTp()+test.getFn();
        recall=x/y;

        x=test.getTp();
        y=test.getTp()+test.getFp();
        precision=x/y;

        f = 2 * ((precision * recall) / (precision + recall));

        System.out.println("accuracy : " +accuracy + "\nrecall : " +recall +"\nprecision : " +precision + "\nf : " +f);

    }

    private static void train_call(String loc, Train train){
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

    private static void test_call(String loc, Test test, Train train,String txtName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter resultsTxt = new PrintWriter(txtName, "UTF-8");
        File folder = new File(loc);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Path path = Paths.get(file.getPath());
                    test.checkWords(path, train);
                    resultsTxt.println(file.getName()+" : " +test.getTestType().toUpperCase());
                    if(loc.contains("spam")){
                        if(test.getTestType().equals("spam")){
                            test.increaseTn();
                        }else{
                            test.increaseFp();
                        }
                    }else{
                        if(test.getTestType().equals("spam")){
                            test.increaseFn();
                        }else {
                            test.increaseTp();
                        }
                    }
                }
            }
        }
    }
}

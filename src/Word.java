public class Word {

    private String word;
    private int countSpam;
    private int countHam;
    private float prob;


    public Word(String word, int countSpam, int countHam, float prob) {
        this.word = word;
        this.countSpam = countSpam;
        this.countHam = countHam;
        this.prob = prob;
    }
    public Word () {

    }

    public void increaseSpamHam(String type) {
        if(type=="ham"){
            countHam++;
        }else {
            countSpam++;
        }
    }
    public void calculateProbability (float totSpam,float totHam) {

        prob = ((countSpam / totSpam) * totSpam / (totHam + totSpam)) / ((countSpam / totSpam) * (totSpam / (totHam + totSpam)) + ((countHam / totHam) * (totHam / (totHam + totSpam))));

        if(prob < 0.01f) {
            prob = 0.01f;
        } else if(prob > 0.99f) {
            prob = 0.99f;
        }
    }

    public String getWord() {
        return word;
    }

    public int getCountSpam() {
        return countSpam;
    }

    public int getCountHam() {
        return countHam;
    }

    public float getProb() {
        return prob;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCountSpam(int countSpam) {
        this.countSpam = countSpam;
    }

    public void setCountHam(int countHam) {
        this.countHam = countHam;
    }

    public void setProb(float prob) {
        this.prob = prob;
    }



    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", countSpam=" + countSpam +
                ", countHam=" + countHam +
                ", prob=" + prob +
                '}';
    }
}

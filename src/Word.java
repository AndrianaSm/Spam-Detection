public class Word {

    private String word;
    private int countSpam;
    private int countHam;
    private float spamProb;
    private float hamProb;

    public Word(String word, int countSpam, int countHam, float spamProb, float hamProb) {
        this.word = word;
        this.countSpam = countSpam;
        this.countHam = countHam;
        this.spamProb = spamProb;
        this.hamProb=hamProb;
    }
    public Word () { }

    public void increaseSpamHam(String type) {
        if(type=="ham"){
            countHam++;
        }else {
            countSpam++;
        }
    }
    public void spamProb (float totSpam,float totHam) {

        spamProb = ((countSpam / totSpam) * totSpam / (totHam + totSpam)) / ((countSpam / totSpam) * (totSpam / (totHam + totSpam)) + ((countHam / totHam) * (totHam / (totHam + totSpam))));

        if(spamProb < 0.01f) {
            spamProb = 0.01f;
        } else if(spamProb > 0.99f) {
            spamProb = 0.99f;
        }
    }

    public void hamProb (float totSpam,float totHam) {

        hamProb = (countHam / totHam) * (totHam / (totHam + totSpam)) / ((countSpam / totSpam) * (totSpam / (totHam + totSpam)) + ((countHam / totHam) * (totHam / (totHam + totSpam))));

        if(hamProb < 0.01f) {
            hamProb = 0.01f;
        } else if(hamProb > 0.99f) {
            hamProb = 0.99f;
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCountSpam() {
        return countSpam;
    }

    public void setCountSpam(int countSpam) {
        this.countSpam = countSpam;
    }

    public int getCountHam() {
        return countHam;
    }

    public void setCountHam(int countHam) {
        this.countHam = countHam;
    }

    public float getSpamProb() {
        return spamProb;
    }

    public void setSpamProb(float spamProb) {
        this.spamProb = spamProb;
    }

    public float getHamProb() {
        return hamProb;
    }

    public void setHamProb(float hamProb) {
        this.hamProb = hamProb;
    }
}

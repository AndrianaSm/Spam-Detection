public class Word {

    private String word;
    private int countSpam;
    private int countHam;
    private float sprob;
    private float hprob;


    public Word(String word, int countSpam, int countHam, float sprob, float hprob) {
        this.word = word;
        this.countSpam = countSpam;
        this.countHam = countHam;
        this.sprob = sprob;
        this.hprob=hprob;
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
    public void spamProb (float totSpam,float totHam) {

        sprob = ((countSpam / totSpam) * totSpam / (totHam + totSpam)) / ((countSpam / totSpam) * (totSpam / (totHam + totSpam)) + ((countHam / totHam) * (totHam / (totHam + totSpam))));

        if(sprob < 0.01f) {
            sprob = 0.01f;
        } else if(sprob > 0.99f) {
            sprob = 0.99f;
        }
    }

    public void hamProb (float totSpam,float totHam) {

        hprob = (countHam / totHam) * (totHam / (totHam + totSpam)) / ((countSpam / totSpam) * (totSpam / (totHam + totSpam)) + ((countHam / totHam) * (totHam / (totHam + totSpam))));

        if(hprob < 0.01f) {
            hprob = 0.01f;
        } else if(hprob > 0.99f) {
            hprob = 0.99f;
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

    public float getSprob() { return sprob;  }

    public float getHprob() { return hprob;  }


    public void setWord(String word) {
        this.word = word;
    }

    public void setCountSpam(int countSpam) {
        this.countSpam = countSpam;
    }

    public void setCountHam(int countHam) {
        this.countHam = countHam;
    }

    public void setSprob(float sprob) {  this.sprob = sprob; }

    public void setHprob(float hprob) { this.hprob = hprob;  }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", countSpam=" + countSpam +
                ", countHam=" + countHam +
                ", spam prob=" + sprob +
                ", ham prob=" + hprob +
                '}';
    }
}

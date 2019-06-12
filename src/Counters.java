import processing.core.PApplet;

public class Counters {
    
    PApplet pApplet;
    
    int generationNumber = 0;
    int scoreValue = 0;
    
    public Counters(PApplet pApplet){
        this.pApplet = pApplet;
    }

    public void display() {
        pApplet.push();
        pApplet.fill(255);
        pApplet.translate(0, Resources.gameHeight);
        pApplet.rect(0, 0, pApplet.width, pApplet.height - Resources.gameHeight);
        pApplet.fill(0);
        pApplet.text("Generation: " + generationNumber, 10, 20);
        pApplet.text("Score: " + scoreValue, 10, 40);
        pApplet.pop();
    }

    public void setGeneration(int generation) {
        this.generationNumber = generation;
    }

    public void setScore(int score) {
        this.scoreValue = score;
    }


}

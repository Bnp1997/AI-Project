import processing.core.PApplet;

class Display {

    private PApplet pApplet;
    private int generation = 0;
    private int score = 0;
    private int highScore =0;
    Display(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    void render() {
        pApplet.push();
        pApplet.fill(255);
        pApplet.translate(0, Resources.GAME_SIZE);
        pApplet.rect(0, 0, pApplet.width, pApplet.height - Resources.GAME_SIZE);
        pApplet.fill(0);
        pApplet.text("Generation: " + generation, 10, 20);
        pApplet.text("Score: " + score, 10, 40);
        if (score > highScore){
            highScore = score;
        }
        pApplet.text("High score: " + highScore, 10, 60);
        pApplet.pop();
    }

    void setGeneration(int generation) {
        this.generation= generation;
    }

    void setScore(int score) {
        this.score = score;
    }
}

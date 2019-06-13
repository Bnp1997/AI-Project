import basicneuralnetwork.NeuralNetwork;
import processing.core.PApplet;

import java.awt.image.BufferedImage;

class Bird {

    private PApplet pApplet;
    private float speed = 0;
    private Pipe pipe = null;
    private boolean bestOne = false;

    float xPosition = 280;
    float yPosition;
    float r = 12;
    float pivotPoint = 0;
    boolean alive = true;
    int score = 0;

    NeuralNetwork brain;

    Bird(PApplet pApplet, float y) {
        this.pApplet = pApplet;
        this.yPosition = y;
        brain = new NeuralNetwork(7, 14, 2);
    }

    void render() {
        if (alive) {
            score++;
            if (bestOne) {
                pApplet.push();
                pApplet.ellipse(xPosition, yPosition, 2 * r, 2 * r);
                pApplet.fill(0, 150, 0);
                pApplet.pop();
            } else {
                pApplet.ellipse(xPosition, yPosition, 2 * r, 2 * r);
            }
        }
    }

    void update() {
        float gravity = (float) 0.08;
        speed += gravity;
        this.yPosition += speed;
        distanceToPivot();
        bordersCollisionChecker();
    }

    double[] learningAI(Pipe closestPipe) {
        this.pipe = closestPipe;
        double[] inputs = new double[7];
        inputs[0] = closestPipe.x - (this.xPosition + this.r);
        inputs[1] = closestPipe.openTop;
        inputs[2] = closestPipe.openBottom;
        inputs[3] = this.yPosition;
        inputs[4] = this.speed;
        inputs[5] = pivotPoint;
        inputs[6] = closestPipe.y + closestPipe.width;

        return brain.guess(inputs);
    }

    private void distanceToPivot() {
        if (this.pipe != null) {
            float distance = (float) (Math.pow((pipe.x - this.xPosition), 2) + Math.pow(Math.abs((pipe.openTop + pipe.space / 2) - this.yPosition), 2));
            distance = (float) Math.sqrt(distance);
            this.pivotPoint = distance;
        }
    }

    private void bordersCollisionChecker() {
        if (this.yPosition - this.r / 2 < 0)
            kill();
        if (this.yPosition + this.r / 2 > Resources.GAME_SIZE) {
            kill();
        }
    }

    void jump() {
        this.speed = 0;
        float speedUp = -3;
        this.speed += speedUp;
    }

    void kill() {
        this.alive = false;
    }

    void makeBest() {
        this.bestOne = true;
    }
}

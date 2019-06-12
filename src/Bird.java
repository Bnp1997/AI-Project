import processing.core.PApplet;

public class Bird {
    private PApplet pApplet;
    private float yPosition = 0;
    private float xPosition = 280;
    private boolean alive = true;
    private boolean bestBird = false;
    private int score = 0;
    private float q = 12;
    private float speed = 0;
    private float speedUp = -3;
    private float gravity = (float) 0.08;

    private Bird(PApplet pApplet, float y) {
        this.pApplet = pApplet;
        this.yPosition = y;
    }

    void render() {
        if (alive) {
            score++;
            if (bestBird) {
                pApplet.push();
                pApplet.fill(0, 150, 0);
                pApplet.ellipse(xPosition, yPosition,
                        q * 2, q * 2);
                pApplet.pop();
            } else {
                pApplet.ellipse(xPosition, yPosition,
                        q * 2, q * 2);
            }
        }
    }


    void update() {
        speed += gravity;
        this.yPosition += speed;
        checkingCollisionWithTopBottom();
    }
    private void checkingCollisionWithTopBottom() {
        if (yPosition + q / 2 > Resources.gameHeight)
            kill();
        if (yPosition - q / 2 < 0)
            kill();

    }

    private void jump() {
        this.speed = 0;
        this.speed += this.speedUp;
    }

    private void kill() {
        this.alive = false;
    }

    private void isBestBird() {
        this.bestBird = true;
    }
}

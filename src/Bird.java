import processing.core.PApplet;

class Bird {
    private PApplet pApplet;
    float yPosition = 0;
    float xPosition = 280;
    boolean alive = true;
    boolean bestBird = false;
    int score = 0;
    float q = 12;
    float speed = 0;
    float speedUp = -3;
    float gravity = (float) 0.08;

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
        checkingCollisionWithBorders();
    }

    private void checkingCollisionWithBorders() {
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

    private void bestBird() {
        this.bestBird = true;
    }
}

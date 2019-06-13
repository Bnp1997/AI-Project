import processing.core.PApplet;


class Pipe {

    private PApplet pApplet;
    float width = Resources.PIPE_WIDTH;
    float space = 100;
    float x;
    float y;
    float openTop;
    float openBottom;
    boolean isClosestPipe = false;

    Pipe(PApplet parent, float x) {
        this.pApplet = parent;
        this.x = x;
        float pivotSpace = 50;
        openTop = pApplet.random(space, Resources.GAME_SIZE - (pivotSpace + space));
        openBottom = openTop + space;
    }

    void render() {
        if (isClosestPipe) {
            pApplet.push();
            pApplet.fill(0, 200, 0);
            pApplet.rect(x, 0, width, openTop);
            pApplet.rect(x, openBottom, width, Resources.GAME_SIZE - (openTop + space));
            pApplet.pop();
            isClosestPipe = false;
        } else {
            pApplet.rect(x, 0, width, openTop);
            pApplet.rect(x, openBottom, width, Resources.GAME_SIZE - (openTop + space));
        }


    }

    void update() {
        x--;
    }

    boolean birdCollision(Bird bird) {
        if (bird.xPosition >= this.x && bird.xPosition < this.x + width) {
            return bird.yPosition - bird.r < openTop || bird.yPosition + bird.r > openBottom;
        }
        return false;
    }

    boolean beyondBorders() {
        return (x + width) < 0;
    }
}

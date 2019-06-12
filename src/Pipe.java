import processing.core.PApplet;

public class Pipe {
    PApplet p;
    float height = 800;
    float width = Resources.PIPE_WIDTH;
    float space = 100;
    float x;
    float y;
    float top;
    float bottom;
    boolean closestPipe = false;

    public Pipe(PApplet parent, float x) {
        this.p = parent;
        this.x = x;
        top = p.random(space, Resources.gameHeight - space);
        bottom = top + space;
    }

    void display() {
        if(closestPipe) {
            p.push();
            p.fill(0,200, 0);
            p.rect(x, 0, width, top);
            p.rect(x, bottom, width, Resources.gameHeight - (top+space));
            p.pop();
            closestPipe = false;
        } else {
            p.rect(x, 0, width, top);
            p.rect(x, bottom, width, Resources.gameHeight - (top+space));
        }
    }

    void update() {
        x --;
    }


    public boolean isOffScreen() {
        if((x + width) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean birdCollision(Bird bird) {

        if(bird.xPosition  >= this.x && bird.xPosition < this.x + width) {

            if(bird.yPosition - bird.q< top|| bird.yPosition + bird.q > bottom) {
                return true;
            }

        }

        return false;
    }
}

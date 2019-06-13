import java.util.ArrayList;

import processing.core.PApplet;

class Road {

    private PApplet pApplet;
    private ArrayList<Pipe> road = new ArrayList<>();

    Road(PApplet pApplet) {
        this.pApplet = pApplet;
        generatePipes();
    }

    void render() {
        for (Pipe pipe : road) {
            pipe.render();
            pipe.update();
        }
        if (road.get(0).beyondBorders()) {
            road.add(new Pipe(pApplet, road.get(road.size() - 1).x + Resources.EACH_PIPE_SPACE));
            road.remove(0);
        }
    }

    void reset() {
        road.clear();
        generatePipes();
    }

    private void generatePipes() {
        float sum = Resources.FIRST_PIPE_X;
        for (int i = 0; i < calculateToNeededPipeCount(); i++) {
            road.add(new Pipe(pApplet, sum));
            sum += Resources.EACH_PIPE_SPACE;
        }
    }

    private int calculateToNeededPipeCount() {
        return (int) ((pApplet.width + Resources.EACH_PIPE_SPACE) / (Resources.EACH_PIPE_SPACE)) + 1;
    }

    ArrayList<Pipe> getPipeRoad() {
        return this.road;
    }

}

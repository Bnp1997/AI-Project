import processing.core.PApplet;

import java.util.ArrayList;

public class Road {

    PApplet pApplet;

    ArrayList<Pipe> road = new ArrayList<Pipe>();

    public Road(PApplet pApplet) {
        this.pApplet = pApplet;
        generatePipes();
    }

    void display() {
        for(Pipe pipe : road) {
            pipe.display();
            pipe.update();
        }

        //Check if the first pipe is off the screen then add a new pipe 
        if(road.get(0).isOffScreen()) {
            road.add(new Pipe(pApplet, road.get(road.size()-1).x + Resources.EACH_PIPE_SPACE));
            road.remove(0);
        }
    }

    public void reset() {
        road.clear();
        generatePipes();
    }

    public ArrayList<Pipe> getPipes(){
        return this.road;
    }

    public void generatePipes() {
        float sum = Resources.FIRST_PIPE_X;
        for(int i=0; i<calculateToNeededPipeCount(); i++) {
            road.add(new Pipe(pApplet, sum));
            sum += Resources.EACH_PIPE_SPACE;
        }
    }

    private int calculateToNeededPipeCount() {
        return (int) ( (pApplet.width + Resources.EACH_PIPE_SPACE)  / (Resources.EACH_PIPE_SPACE) ) +1 ;
    }

}
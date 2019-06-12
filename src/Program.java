import processing.core.PApplet;

public class Program {

    PApplet pApplet;
    Road road;
    Counters counters;

    public Program(PApplet pApplet, Counters counters) {
        this.pApplet = pApplet;
        this.counters = counters;
    }

    public void setup() {
        road = new Road(pApplet);
    }

    public void display() {
        pApplet.background(0);
        road.display();
        counters.display();
    }
}

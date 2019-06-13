import processing.core.PApplet;

class Program {

    private PApplet pApplet;
    private Road road;
    private Display display;

    private BirdPopulation birdPopulation;

    Program(PApplet pApplet, Display display) {
        this.pApplet = pApplet;
        this.display = display;
    }

    void setup() {
        road = new Road(pApplet);
        birdPopulation = new BirdPopulation(pApplet);
    }

    void render() {
        pApplet.background(0);
        birdPopulation.setPipes(road.getPipeRoad());
        road.render();
        birdPopulation.render();
        if (birdPopulation.populationDead())
            road.reset();
        birdPopulation.update();
        display.render();
        display.setGeneration(birdPopulation.getGeneration());
        display.setScore(birdPopulation.getScore());
    }
}

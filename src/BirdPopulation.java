import java.util.ArrayList;

import processing.core.PApplet;


class BirdPopulation {

    private PApplet pApplet;
    private float bestScore = 0;
    private int generation = 0;

    private ArrayList<Pipe> pipes = null;
    private ArrayList<Bird> birds = new ArrayList<>();
    private ArrayList<Bird> savedBirds = new ArrayList<>();

    private int frameCount = 0;

    BirdPopulation(PApplet pApplet) {
        this.pApplet = pApplet;
        generatePopulation();
    }

    void render() {
        frameCount++;
        for (Bird bird : birds) {
            bird.render();
            bird.update();
            double[] output;
            int closestPipe = pickClosestPipe();
            pipes.get(closestPipe).isClosestPipe = true;
            output = bird.learningAI(pipes.get(closestPipe));
            if (output[0] < output[1])
                bird.jump();
            if (pipes.get(closestPipe).birdCollision(bird)) {
                bird.kill();
            }
        }
    }

    void update() {
        if (populationDead()) {
            mutatePopulation();
        }
    }

    private void mutatePopulation() {
        generation++;
        savedBirds = birds;
        Bird bestBird = pickBestOne();
        savedBirds.clear();
        birds.clear();
        System.out.println("Generation: " + generation);
        System.out.println("Score: " + bestScore);
        frameCount = 0;
        for (int i = 0; i < Resources.POPULATION_SIZE * 0.8; i++) {
            Bird child = new Bird(pApplet, Resources.GAME_SIZE / 2);
            child.brain = bestBird.brain.copy();
            child.brain.mutate(0.1);
            birds.add(child);
        }
        for (int i = 0; i < Resources.POPULATION_SIZE * 0.2; i++) {
            Bird best = new Bird(pApplet, Resources.GAME_SIZE / 2);
            best.brain = bestBird.brain.copy();
            best.makeBest();
            birds.add(best);
        }

    }

    private Bird pickBestOne() {
        int bestScore = frameCount;
        float distance = 1000000; //We can give any big number
        Bird best = savedBirds.get(0);
        for (Bird bird : savedBirds) {
            if (bird.score >= bestScore) {
                if (bird.pivotPoint <= distance) {
                    distance = bird.pivotPoint;
                    best = bird;
                }
            }
        }
        this.bestScore = bestScore;
        return best;
    }

    private int pickClosestPipe() {
        float close = 1000000000;
        int pipeIndex = 0;
        for (int i = 0; i < pipes.size(); i++) {
            if (Math.abs(pipes.get(i).x - Resources.BIRD_STARTING_X_POSITION) < close && pipes.get(i).x >= Resources.BIRD_STARTING_X_POSITION - Resources.PIPE_WIDTH - 5) {
                close = Math.abs(pipes.get(i).x - Resources.BIRD_STARTING_X_POSITION);
                pipeIndex = i;
            }
        }
        return pipeIndex;
    }

    private void generatePopulation() {
        for (int i = 0; i < Resources.POPULATION_SIZE; i++) {
            birds.add(new Bird(pApplet, Resources.GAME_SIZE));
        }
    }

    boolean populationDead() {
        for (Bird bird : birds) {
            if (bird.alive)
                return false;
        }
        return true;
    }

    void setPipes(ArrayList<Pipe> pipe) {
        this.pipes = pipe;
    }

    int getGeneration() {
        return generation;
    }

    int getScore() {
        return frameCount;
    }


}

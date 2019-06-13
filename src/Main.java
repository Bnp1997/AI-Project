import processing.core.PApplet;

public class Main extends PApplet {

    private Program program;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings(){
        size(1200,800);
    }

    public void setup(){
        Display display = new Display(this);
        program = new Program(this, display);
        program.setup();
        frameRate(999);
    }

    public void draw(){
        background(0);
        program.render();
    }
}

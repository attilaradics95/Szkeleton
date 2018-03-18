public class Wall extends ATile {

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;

    public Wall() {
        instanceCounter++;
        counter = instanceCounter;
    }

    public void accept(Visitor v, Directions d) {
        System.out.println(this + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public String toString() {
        return "wall" + counter;
    }
}

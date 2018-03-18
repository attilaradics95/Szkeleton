public class Switch extends ATile {
    //Attribútumok
    private Trap trap;
    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;

    public Switch() {
        instanceCounter++;
        counter = instanceCounter;
    }

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public void switchIt(Box b) {
        System.out.println(this.toString() + ".switch(" + b + ")");
        trap.setOpened(true);
    }

    public void switchIt(Worker w){
        System.out.println(this.toString() + ".switch(" + w + ")");
        trap.setOpened(false);
    }

    public String toString() {
        return "switch" + counter;
    }

}


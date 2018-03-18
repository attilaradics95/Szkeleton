public class Switch extends ATile {
    //Attribútumok
    private Trap trap;
    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    Tabulate tabulate = new Tabulate();

    public Switch() {
        instanceCounter++;
        counter = instanceCounter;
    }

    //Függvények
    public void accept(Visitor v, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);

        tabulate.out();
    }

    public void setTrap(Trap t){
        trap = t;
    }

    public void switchIt(Box b) {
        tabulate.in();

        System.out.println(this.toString() + ".switch(" + b + ")");
        trap.setOpened(true);

        tabulate.out();
    }

    public void switchIt(Worker w){
        tabulate.in();

        System.out.println(this.toString() + ".switch(" + w + ")");
        trap.setOpened(false);

        tabulate.out();
    }

    public String toString() {
        return "switch" + counter;
    }

}


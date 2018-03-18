public class Switch extends ATile {
    //Attribútumok
    private Trap trap;

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println(this + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public void switchIt(Box b) {
        System.out.println(this + ".switch(" + b + ")");
        trap.setOpened(true);
    }

    public void switchIt(Worker w){
        System.out.println(this + ".switch(" + w + ")");
        trap.setOpened(false);
    }

}


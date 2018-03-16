public class Switch extends ATile {
    //Attribútumok
    private Trap trap;

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public void switchIt(Box b) {
        System.out.println("switch(" + b + ")");
    }

    public void switchIt(Worker w){
        System.out.println("switch(" + w + ")");
    }
}


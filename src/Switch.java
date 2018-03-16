public class Switch extends ATile {
    //Attribútumok
    private Trap trap;

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
    }

    public void switch(
    Box b)

    {
        System.out.println("switch(" + b + ")");
    }
}


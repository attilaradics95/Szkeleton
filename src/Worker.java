public class Worker extends Visitor {
    //Attribútumok
    private Controller controller = null;

    //Metódusok
    public Worker() {
        controller = Controller.getInstance();
    }

    public void move(Directions d) {
        ATile next = currentTile.getNeighbor(d);
        next.accept(this, d);
    }

    public void pushTo(Tile next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Switch next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Hole next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Trap next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Target next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Wall next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Column next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    @Override
    public void die() {
        System.out.println("die()");
    }
}

public abstract class Visitor {
    //Attribútumok
    private Tile currentTile;
    private Boolean movable;

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

    public void pushTo(Obstacle next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void die() {
        System.out.println("die()");
    }

    public void move(Directions d) {
        System.out.println("move(" + d + ")");
    }
}

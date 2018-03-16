public abstract class Visitor {
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

    public void die() {
        System.out.println("die()");
    }

    public void move(Directions d) {
        System.out.println("move(" + d + ")");
    }
}

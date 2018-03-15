public abstract class Visitor {
    public void pushTo(Tile next, Directions d) {
        system.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Switch next, Directions d) {
        system.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Hole next, Directions d) {
        system.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Trap next, Directions d) {
        system.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Target next, Directions d) {
        system.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Obstacle next, Directions d) {
        system.out.println("pushTo(" + next + "," + d + ")");
    }

    public void die() {
        system.out.println("die()");
    }

    public void move(Directions d) {
        system.out.println("move(" + d + ")");
    }
}

public class Target extends ATile {
    public void accept(Visitor v, Directions d) {
        system.out.println("accept(" + v + "," + d + ")");
    }
}

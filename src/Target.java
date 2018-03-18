public class Target extends ATile {

    public void accept(Visitor v, Directions d) {
        System.out.println(this + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }
}

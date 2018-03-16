public class Trap extends ATile {


    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public void setOpened(Boolean bool) {
        System.out.println("setOpened(" + bool + ")");
    }
}

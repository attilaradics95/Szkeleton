public class Hole extends ATile {

    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    @Override
    public void setNeighbors(ATile north, ATile east, ATile south, ATile west) {

    }
}

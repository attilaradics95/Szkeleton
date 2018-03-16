public class Trap extends ATile {
    @Override
    public Tile getNeighbor(Directions d) {
        return null;
    }

    @Override
    public void setNeighbor(Tile t, Directions d) {

    }

    @Override
    public void setVisitor(Visitor v) {

    }

    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
    }

    public void setOpened(Boolean bool) {
        System.out.println("setOpened(" + bool + ")");
    }
}

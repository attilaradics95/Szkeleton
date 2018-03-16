public class Switch extends ATile {
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

    public void switchIt(Box b){
        System.out.println("switch(" + b + ")");
    }
}


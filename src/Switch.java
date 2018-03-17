public class Switch extends ATile {
    //Attribútumok
    private Trap trap;

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    @Override
    public void setNeighbors(ATile north, ATile east, ATile south, ATile west) {
        neighbors.put(Directions.NORTH, north);
        neighbors.put(Directions.EAST, east);
        neighbors.put(Directions.SOUTH, south);
        neighbors.put(Directions.WEST, west);
    }

    public void switchIt(Box b) {
        System.out.println("switch(" + b + ")");
    }

    public void switchIt(Worker w){
        System.out.println("switch(" + w + ")");
    }

}


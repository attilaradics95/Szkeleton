public class Trap extends ATile {
    //Attribútumok
    private Boolean opened;

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

    public void setOpened(Boolean opened) {
        System.out.println("setOpened(" + opened + ")");
        this.opened = opened;
        if (opened && (visitor != null))
        {
            visitor.die();
        }
    }
}

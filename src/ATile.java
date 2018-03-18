import java.util.HashMap;

public abstract class ATile {
    protected HashMap<Directions, ATile> neighbors;
    protected Visitor visitor;

    public ATile(){
        neighbors = new HashMap<>();
        visitor = null;
    }

    public ATile getNeighbor(Directions d){
        return neighbors.get(d);
    }

    public void setNeighbor(ATile t, Directions d){
        neighbors.put(d, t);
    }

    public Visitor getVisitor(){
        return visitor;
    }

    public void setVisitor(Visitor v){
        visitor = v;
    }

    abstract public void accept(Visitor v, Directions d);

    public void setNeighbors(ATile north,ATile east, ATile south, ATile west){
            neighbors.put(Directions.NORTH, north);
            neighbors.put(Directions.EAST, east);
            neighbors.put(Directions.SOUTH, south);
            neighbors.put(Directions.WEST, west);
    }

}

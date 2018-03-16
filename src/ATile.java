public abstract class ATile {
    abstract public Tile getNeighbor(Directions d);

    abstract public void setNeighbor(Tile t, Directions d);

    //abstract public void Visitor getVisitor();

    abstract public void setVisitor(Visitor v);

    abstract public void accept(Visitor v, Directions d);
}

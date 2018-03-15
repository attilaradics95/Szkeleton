public abstract class ATile {
    public virtual Tile getNeighbor(Directions d);
    public virtual void setNeighbor(Tile t, Directions d);
    public virtual void Visitor getVisitor();
    public virtual void setVisitor(Visitor v);
    public virtual Boolean accept(Visitor v, Directions d);
}

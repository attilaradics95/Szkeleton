public abstract class ATile {
    public virtual Tile getNeighbor(Direction d);
    public virtual void setNeighbor(Tile t, Direction d);
    public virtual void Visitor getVisitor();
    public virtual void setVisitor(Visitor v);
    public virtual Boolean accept(Visitor v, Direction d);
}

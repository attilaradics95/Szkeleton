import java.util.List;

public abstract class ATile {
    //Attribútumok
    private List<ATile> neighbors;
    private Visitor visitor;
    //Függvények
    public virtual Tile

    getNeighbor(Directions d);

    public virtual

    void setNeighbor(Tile t, Directions d);

    public virtual
    void Visitor

    getVisitor();

    public virtual

    void setVisitor(Visitor v);

    public virtual Boolean

    accept(Visitor v, Directions d);
}

public abstract class Visitor {
    protected ATile currentTile;
    protected boolean movable;
    Tabulate tabulate = new Tabulate();

    public void setCurrentTile(ATile currentTile){
        this.currentTile = currentTile;
    }
    abstract public void pushTo(Tile next, Directions d);
    abstract public void pushTo(Switch next, Directions d);
    abstract public void pushTo(Hole next, Directions d);
    abstract public void pushTo(Trap next, Directions d);
    abstract public void pushTo(Target next, Directions d);
    abstract public void pushTo(Wall next, Directions d);
    abstract public void pushTo(Column next, Directions d);
    abstract public void die();
}

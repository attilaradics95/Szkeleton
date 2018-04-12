public abstract class Visitor {
    protected int id;
    protected int force;
    protected ATile currentTile;


    //függvények, leszármazottakban implementálva
    //setCurrentTile beállítja az aktuális Visitornak, hogy melyik mezőn áll
    public void setCurrentTile(ATile currentTile){
        this.currentTile = currentTile;
    }
    public void setForce(int force){
        this.force = force;
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

public abstract class Visitor {
    protected int id;
    protected int force;
    protected ATile currentTile;
    protected ElementView view;


    public int getId() { return this.id; }

    public ATile getCurrentTile() { return this.currentTile; }

    public void setForce(int force) {this.force = force; }

    public int getForce() {return this.force; }

    //setCurrentTile beállítja az aktuális Visitornak, hogy melyik mezőn áll
    public void setCurrentTile(ATile currentTile){
        this.currentTile = currentTile;
    }

    //függvények, leszármazottakban implementálva

    abstract public void pushTo(Tile next, Directions d, int force);
    abstract public void pushTo(Switch next, Directions d, int force);
    abstract public void pushTo(Honey next, Directions d, int force);
    abstract public void pushTo(Hole next, Directions d, int force);
    abstract public void pushTo(Oil next, Directions d, int force);
    abstract public void pushTo(Trap next, Directions d, int force);
    abstract public void pushTo(Target next, Directions d, int force);
    abstract public void pushTo(Obstacle next, Directions d, int force);
    abstract public void die();

    public ElementView getView() {
        return view;
    }
}

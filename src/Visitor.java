/**
 * A worker és box osztályok absztrakt őse, a Visitor minta megvalósítása.
 */
public abstract class Visitor {
    protected int id;
    protected int force;
    protected ATile currentTile;
    protected ElementView view;

    /**
     * Visszaadja a visitor azonosítóját
     * @return a visitor azonosítója
     */
    public int getId() { return this.id; }

    /**
     * Visszaadja melyik mezőn áll éppen a visitor.
     * @return a visitor tartózkodási helye
     */
    public ATile getCurrentTile() { return this.currentTile; }

    /**
     * Beállítja a visitor erejét
     * @param force a visitor ereje
     */
    public void setForce(int force) {this.force = force; }

    /**
     * Visszaadja visitor erejét
     * @return  a visitor ereje
     */
    public int getForce() {return this.force; }

    /**
     * Beállítja az aktuális Visitornak, hogy melyik mezőn áll
     * @param currentTile az aktuális mező
     */
    public void setCurrentTile(ATile currentTile){
        this.currentTile = currentTile;
    }

    //

    /**
     * A függvények a leszármazottakban vannak implementálva
     */
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

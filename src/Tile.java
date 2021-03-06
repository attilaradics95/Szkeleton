public class Tile extends ATile{
    public Tile() {
        this.view = new TileView();
    }

    /**
     * mint minden accept meghívja a visitor pushTo metódusát önmagát átadva
     * @param v visitor
     * @param d irany
     * @param force worker ereje
     */
    public void accept(Visitor v, Directions d, int force) {
        v.pushTo(this, d, force);
    }

    /**
     * objektum kiíráshoz
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    public String toString() {
        return ".";
    }
}

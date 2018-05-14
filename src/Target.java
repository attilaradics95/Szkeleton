public class Target extends ATile {
    public Target() {
        this.view = new TargetView();
    }

    /**
     * Minnt minden accept, meghívja a visitor pushTo metódusát önmagát átadva.
     *
     * @param v visitor
     * @param d irany
     * @param force worker ereje
     */
    public void accept(Visitor v, Directions d, int force) {
        v.pushTo(this, d, force);
    }

    /**
     * Kiírja az objektumot
     *
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    public String toString() {
        return "X";
    }
}

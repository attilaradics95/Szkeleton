public class Honey extends ATile {
    /**
     * Konstruktor
     *
     */
    public Honey(){
        this.view = new HoneyView();
    }

    /**
     * Mint minden accept, meghívja a visitor pushTo metódusát önmagát átadva.
     * ha áll rajta visitor csökken a toló erő
     * @param v visitor
     * @param d irany
     * @param force worker ereje
     */
    @Override
    public void accept(Visitor v, Directions d, int force) {
        if (visitor != null)
            v.pushTo(this, d, force - 1);
        else
            v.pushTo(this, d, force);
    }

    /**
     * Kiírja az objektumot.
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    @Override
    public String toString(){
        return "M";
    }
}

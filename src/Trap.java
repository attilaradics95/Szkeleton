public class Trap extends ATile {
    //Attribútumok
    private boolean opened;
    private int id;

    public Trap() {
        opened = false;
        id = 0;
    }

    public Trap(int id) {
        opened = false;
        this.id = id;
    }

    //Függvények

    public int getId() {
        return id;
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
     * ha visitor kerül, akkor attól függően, hogy milyen visitor,
     * kinyílik vagy becsukódik a csapda
     * @param bool true - nyit, false - csuk.
     */
    public void setOpened(Boolean bool) {
        opened = bool;
        if(bool && visitor != null){
            visitor.die();
        }
    }

    public boolean isOpened(){
        return opened;
    }

    /**
     * objektum kiíráshoz
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    public String toString() {
        return "T" + id;
    }
}

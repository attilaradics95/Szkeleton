public class Switch extends ATile {
    //Attribútumok
    private Trap trap;
    private int id;

    //Függvények
    public Switch() {
        id = 0;
    }

    public Switch(int id) {
        this.id = id;
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

    public void setTrap(Trap t){
        trap = t;
    }

    /**
     * ha box kerül a kapcsolóra vált - a trap kinyílik
     * @param b Box
     */
    public void switchIt(Box b) {
        if (trap != null){
            trap.setOpened(true);
        } else {
            System.out.println("Nem tartozik csapda a kapcsolohoz.");
        }
    }

    /**
     * ha worker kerül a kapcsolóra - a trap inaktív lesz
     * @param w Worker
     */
    public void switchIt(Worker w){
        if (trap != null){
            trap.setOpened(false);
        } else {
            System.out.println("Nem tartozik csapda a kapcsolohoz.");
        }
    }

    /**
     * objektum kiíráshoz
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    public String toString() {
        return "S" + id;
    }

    public int getId() {
        return id;
    }
}


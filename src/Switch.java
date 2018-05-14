public class Switch extends ATile {
    //Attribútumok
    //A kapcsolóhoz tartozó csapda
    private Trap trap;
    private int id;

    //Függvények

    /**
     * Konstruktor
     *
     */
    public Switch() {
        id = 0;
    }

    /**
     * Konstruktor
     *
     * @param id  A kapcsoló azonosítója
     */
    public Switch(int id) {
        this.id = id;
        this.view = new SwitchView();
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
     * Beállítja a csapdát
     *
     * @param t a csapda amit hozzárendelünk a kapcsolóhoz
     */
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
        //a rákerülő láda bekapcsolja, így ezt a képet állítjuk be
        view.setImage("switch_on.jpg");
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
        //a rákerülő munkás nem kapcsolja be
        //ennek megfelelően állítjuk a képet is
        view.setImage("switch_off.jpg");
    }

    /**
     * Kiírja az objektumot
     *
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    public String toString() {
        return "S" + id;
    }

    /**
     * Visszaadja a kapcsoló azonosítóját
     *
     * @return a kapcsoló azonosítója
     */
    public int getId() {
        return id;
    }
}


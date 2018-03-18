public class Switch extends ATile {
    //Attribútumok
    private Trap trap;
    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
   //dupla tabulátorozás ellen kikommenteltem @author Rozi
    //Tabulate tabulate = new Tabulate();

    public Switch() {
        instanceCounter++;
        counter = instanceCounter;
    }

    //Függvények
    public void accept(Visitor v, Directions d) {
        tabulate.in();

        //mint minden accept meghívja a visitor pushTo metódusát önmagát átadva
        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);

        tabulate.out();
    }

    public void setTrap(Trap t){
        trap = t;
    }

    public void switchIt(Box b) {
        tabulate.in();

        //ha box kerül a kapcsolóra vált - a trap kinyílik
        System.out.println(this.toString() + ".switchIt(" + b + ")");
        if (trap != null){
            trap.setOpened(true);
        } else {
            tabulate.tabulate();
            System.out.println("Nem tartozik csapda a kapcsolohoz.");
        }

        tabulate.out();
    }

    public void switchIt(Worker w){
        tabulate.in();

        //ha worker kerül a kapcsolóra - a trap inaktív lesz
        System.out.println(this.toString() + ".switchIt(" + w + ")");
        if (trap != null){
            trap.setOpened(false);
        } else {
            System.out.println("Nem tartozik csapda a kapcsolohoz.");
        }

        tabulate.out();
    }

    //objektum kiíráshoz
    public String toString() {
        return "switch" + counter;
    }

}


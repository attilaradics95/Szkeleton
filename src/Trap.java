public class Trap extends ATile {
    //Attribútumok
    private boolean opened;

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;

    public Trap() {
        instanceCounter++;
        counter = instanceCounter;
    }

    //Függvények
    public void accept(Visitor v, Directions d, int force) {
        //mint minden accept meghívja a visitor pushTo metódusát önmagát átadva
        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d, force);
    }

    //ha visitor kerül, akkor attól függően, hogy milyen visitor,
    // kinyílik vagy becsukódik a csapda
    public void setOpened(Boolean bool) {
        System.out.println(this.toString() + ".setOpened(" + bool + ")");

        opened = bool;
        if(bool && visitor != null){
            visitor.die();
        }
    }

    //objektum kiíráshoz
    public String toString() {
        return "trap" + counter;
    }
}

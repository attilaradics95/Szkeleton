public class Trap extends ATile {
    //Attribútumok
    //majd később szükség lesz rá, de jelenleg nem tárolunk állpotot ehhez
    //private boolean opened;

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    Tabulate tabulate = new Tabulate();

    public Trap() {
        instanceCounter++;
        counter = instanceCounter;
    }

    //Függvények
    public void accept(Visitor v, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".accept(" + v + "," + d + ")");
        v.pushTo(this, d);

        tabulate.out();
    }

    //ha visitor kerül, akkor attól függően, hogy milyen visitor,
    // kinyílik vagy becsukódik a csapda
    public void setOpened(Boolean bool) {
        tabulate.in();

        System.out.println(this.toString() + ".setOpened(" + bool + ")");
        //ez is a továbbiakban fog kelleni
        //opened = bool
        if(bool && visitor != null){
            visitor.die();
        }

        tabulate.out();
    }

    public String toString() {
        return "trap" + counter;
    }
}

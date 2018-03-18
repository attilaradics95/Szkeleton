public class Trap extends ATile {
    //Attribútumok
    //majd később szükség lesz rá, de jelenleg nem tárolunk állpotot ehhez
    //private boolean opened;

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    //ha visitor kerül, akkor attól függően, hogy milyen visitor,
    // kinyílik vagy becsukódik a csapda
    public void setOpened(Boolean bool) {
        System.out.println("setOpened(" + bool + ")");
        //ez is a továbbiakban fog kelleni
        //opened = bool
        if(bool && visitor != null){
            visitor.die();
        }
    }
}

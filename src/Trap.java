public class Trap extends ATile {
    //Attribútumok
    private Boolean opened;

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
    }

    public void setOpened(Boolean bool) {
        System.out.println("setOpened(" + bool + ")");
    }
}

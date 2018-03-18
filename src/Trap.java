public class Trap extends ATile {
    //Attribútumok
    private boolean opened;

    public Trap(){
        opened = false;
    }

    //Függvények
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public void setOpened(Boolean bool) {
        System.out.println("setOpened(" + bool + ")");
        if(opened && visitor != null){
            visitor.die();
        }
    }
}

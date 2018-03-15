public class Trap extends ATile{
    public void accept(Visitor v, Directions d){
        System.out.println("accept(" + v + "," + d + ")");
    }
    public void setOpened(Boolean bool) {
        system.out.println("setOpened(" + bool + ")");
    }
}

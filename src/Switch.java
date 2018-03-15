public class Switch extends ATile {
    public void accept(Visitor v, Directions d) {
        system.out.println("accept(" + v + "," + d + ")");
    }
    public void switch(Box b){
        system.out.println("switch(" + b + ")");
    }
}


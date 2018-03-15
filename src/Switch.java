public class Switch {
    public void accept(Visitor v, Direction d) {
        system.out.println("accept(" + v + "," + d + ")");
    }
    public void switch(Box b){
        system.out.println("switch(" + b + ")");
    }
}


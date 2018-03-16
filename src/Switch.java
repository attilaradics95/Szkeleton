public class Switch extends ATile {

    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
        v.pushTo(this, d);
    }

    public void switchIt(Box b){
        System.out.println("switch(" + b + ")");
    }
}


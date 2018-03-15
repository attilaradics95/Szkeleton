public class Switch extends ATile {
    public void accept(Visitor v, Directions d) {
        System.out.println("accept(" + v + "," + d + ")");
    }

    public void switch(Box b)

    {
        System.out.println("switch(" + b + ")");
    }
}


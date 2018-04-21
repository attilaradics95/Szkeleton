public class Oil extends ATile {
    @Override
    public void accept(Visitor v, Directions d, int force) {
        if (visitor != null)
            v.pushTo(this, d, force + 1);
        else
            v.pushTo(this, d, force);
    }

    @Override
    public String toString(){
        return "O";
    }
}

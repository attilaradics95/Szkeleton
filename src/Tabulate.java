public class Tabulate {

    private static int tabNum = 0;

    public void in() {
        tabNum++;
        tabulate();
    }

    public void out() {
        //System.out.println("Fuggveny visszatert.");
        tabNum--;
        tabulate();

    }

    public void tabulate() {
        for(int i = 0; i < tabNum; i++) {
            System.out.print("\t");
        }
    }
}

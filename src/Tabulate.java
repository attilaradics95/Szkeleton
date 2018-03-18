public class Tabulate {
    //egy osztály arra, hogy a függvényhívás alapján megfelelően legyenek behúzva a dolgok

    private static int tabNum = 0;

    //növeli a tabulátorok számát
    //minden kiírandó függvénynél meghívjuk a legelején
    public void in() {
        tabNum++;
        tabulate();
    }

    //csökkenti a tabulátorok számát
    //minden kiírandó függvénynél meghívjuk a legvégén
    public void out() {
        //System.out.println("Fuggveny visszatert.");
        tabNum--;
        tabulate();

    }

    //a függvény neve előtt kiírja a megfelelő számú tabulátort
    public void tabulate() {
        for(int i = 0; i < tabNum; i++) {
            System.out.print("\t");
        }
    }
}

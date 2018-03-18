import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Box extends Visitor {
    //Attribútumok
    Game game = null;
    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    //Tabulate tabulate = new Tabulate();

    //Függvények
    public Box() {
        game = Game.getInstance();
        instanceCounter++;
        counter = instanceCounter;
    }

    //Wall és Column kivételével - mivel ide úgyse tud menni -  minden pushTo-nál megkérdezzük, hogy mozgatható-e a doboz
    //ha nem, akkor nem mozdul el -- ki hitte volna? -- egyébként a mezőtől függ, mi történik
    //ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitorral
    // ha átkerül a következő mezőre beállítja magát a visitorának, annak a mezőnek, ahonnan ellépett nullra állítja

    //Tile
    // semmi extra nem történik
    public void pushTo(Tile next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        while (true) {
            tabulate.tabulate();
            System.out.println("Mozgathato a doboz? (Y/N)");
            tabulate.tabulate();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                Visitor visitorOnNext = next.getVisitor();
                if(visitorOnNext != null){
                    ATile next1 = next.getNeighbor(d);
                    next1.accept(visitorOnNext, d);
                    visitorOnNext = next.getVisitor();
                }
                if(visitorOnNext == null){
                    currentTile.setVisitor(null);
                    next.setVisitor(this);
                }

                tabulate.out();

                return;
            }
            if (input.equals("N") || input.equals("n")) {

                tabulate.out();

                return;
            }
        }
    }

    //Switch
    // amikor átlép meghívja önmagát átadva paraméterként a switch switchIt metódusát
    public void pushTo(Switch next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        while (true) {
            tabulate.tabulate();
            System.out.println("Mozgathato a doboz? (Y/N)");
            tabulate.tabulate();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                Visitor visitorOnNext = next.getVisitor();
                if (visitorOnNext != null) {
                    ATile next1 = next.getNeighbor(d);
                    next1.accept(visitorOnNext, d);
                    visitorOnNext = next.getVisitor();
                }

                if (visitorOnNext == null) {
                    currentTile.setVisitor(null);
                    next.setVisitor(this);
                    next.switchIt(this);
                }

                tabulate.out();

                return;
            }
            if (input.equals("N") || input.equals("n")) {

                tabulate.out();

                return;
            }
        }
    }

    //Hole
    //beleesik és meghal
    public void pushTo(Hole next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        while (true) {
            tabulate.tabulate();
            System.out.println("Mozgathato a doboz? (Y/N)");
            tabulate.tabulate();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                this.die();

                tabulate.out();

                return;
            }
            if (input.equals("N") || input.equals("n")) {

                tabulate.out();

                return;
            }
        }
    }

    //Trap
    // megkérdezzük, hogy nyitva van-e
    // ha igen beleesik és meghal
    // ha nem, megpróbál odalépni - úgy viselkedik a Trap csukva, mint egy egyszerű Tile
    public void pushTo(Trap next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        while (true) {
            tabulate.tabulate();
            System.out.println("Mozgathato a doboz? (Y/N)");
            tabulate.tabulate();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                while (true) {
                    tabulate.tabulate();
                    System.out.println("Nyitva van a csapda? (Y/N)");
                    tabulate.tabulate();
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String input1 = "";
                    try {
                        input1 = br1.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (input1.equals("Y") || input1.equals("y")) {
                        this.die();
                        tabulate.out();
                        return;
                    }
                    if (input1.equals("N") || input1.equals("n")) {
                        Visitor visitorOnNext = next.getVisitor();
                        if (visitorOnNext != null) {
                            ATile next1 = next.getNeighbor(d);
                            next1.accept(visitorOnNext, d);
                            visitorOnNext = next.getVisitor();
                        }

                        if (visitorOnNext == null) {
                            currentTile.setVisitor(null);
                            next.setVisitor(this);
                        }
                        tabulate.out();
                        return;
                    }
                }
            }
            if (input.equals("N") || input.equals("n")) {
                tabulate.out();
                return;
            }
        }
    }

    //Target
    // ha odalép a doboz, mozgathatatlanná válik
    public void pushTo(Target next, Directions d) {
        tabulate.in();
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        while (true) {
            tabulate.tabulate();
            System.out.println("Mozgathato a doboz? (Y/N)");
            tabulate.tabulate();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                Visitor visitorOnNext = next.getVisitor();
                if(visitorOnNext != null){
                    ATile next1 = next.getNeighbor(d);
                    next1.accept(visitorOnNext, d);
                    visitorOnNext = next.getVisitor();
                }
                if(visitorOnNext == null){
                    currentTile.setVisitor(null);
                    next.setVisitor(this);
                    this.setUnmovable();
                }
                tabulate.out();
                return;
            }
            if (input.equals("N") || input.equals("n")) {
                tabulate.out();
                return;
            }
        }
    }

    // Column és Wall
    //nem tud elmozdulni ezekre a mezőkre
    // összenyomni se lehet, így a dobozt toló munkás se mozdul el a helyéről
    public void pushTo(Wall next, Directions d) {
        tabulate.in();
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        tabulate.out();
    }

    public void pushTo(Column next, Directions d) {
        tabulate.in();
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        tabulate.out();
    }

    //mozgathatatlanná válik a box
    public void setUnmovable() {
        tabulate.in();
        System.out.println(this.toString() + ".setUnmovable()");
        tabulate.out();
    }

    //ha meghal az aktuális mező visitorját nullra állítja
    //ezután csökkenti a mozgatható dobozok számát
    public void die() {
        tabulate.in();
        System.out.println(this.toString() + ".die()");
        currentTile.setVisitor(null);
        currentTile = null;
        game.decreaseBoxes(this);
        tabulate.out();
    }

    //objektum kiíráshoz
    public String toString() {
        return "box" + counter;
    }

}

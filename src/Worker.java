import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Worker extends Visitor{
    private Controller controller = null;

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;

    public Worker(){
        controller = Controller.getInstance();
        instanceCounter++;
        counter = instanceCounter;
    }

    //egy adott szekvenciában először a controller hívja meg a selectedWorker move-ját
    //így ezzel indul el minden szekvenciában a visitorok mozgatása
    //meghívja a kapott irányban következő mező accept metódusát a workerrel
    public void move(Directions d) {
        System.out.println(this.toString() + ".move("+ d +")");
        ATile next = currentTile.getNeighbor(d);
        next.accept(this, d, this.force);
    }

    //Minden pushTo-nál megnézzük, hogy van-e a mezőn, amire lépne visitor
    //ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitorral
    //ha az accept visszatért és még mindig van visitor a mezőn, amire lépne, akkor ha nem ő a kiválasztott raktáros, akkor meghal, mert összenyomják
    //ha ő a selected worker, akkor nem fog elmozdulni a kiindulási helyéről
    // ha átkerül a következő mezőre beállítja magát a visitorának, annak a mezőnek, ahonnan ellépett nullra állítja

    //Tile
    // semmi extra nem történik
    public void pushTo(Tile next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
    }

    //Switch
    // amikor átlép meghívja önmagát átadva paraméterként a switch switchIt metódusát
    public void pushTo(Switch next, Directions d, int force) {

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            next.setVisitor(this);
            next.switchIt(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }

    }

    //Hole
    //beleesik és meghal
    public void pushTo(Hole next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        this.die();
    }

    //Trap
    // megkérdezzük, hogy nyitva van-e
    // ha igen beleesik és meghal
    // ha nem, megpróbál odalépni - úgy viselkedik a Trap csukva, mint egy egyszerű Tile
    public void pushTo(Trap next, Directions d, int force) {

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        //Input beolvasása a konzolról
        while (true) {
            System.out.println("Nyitva van a csapda? (Y/N)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                this.die();
                return;
            }
            if (input.equals("N") || input.equals("n")) {
                Visitor visitorOnNext = next.getVisitor();
                if(visitorOnNext != null){
                    ATile next1 = next.getNeighbor(d);
                    next1.accept(visitorOnNext, d, force);
                    visitorOnNext = next.getVisitor();
                }

                if(visitorOnNext == null){
                    currentTile.setVisitor(null);
                    next.setVisitor(this);
                }
                else {
                    Worker sw = controller.getSelectedworker();
                    if (this != sw){
                        this.die();
                    }
                }
                return;
            }
        }
    }

    //Target
    //ugyanúgy viselkedik a raktárossal szemben, mint a sima Tile
    public void pushTo(Target next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
    }

    // Obstacle
    //ha a kiválasztott raktárost léptetik ide, akkor nem tud elmozdulni ezekre a mezőkre
    //ha nem a kiválasztott raktáros az, akkor összenyomódik és meghal
    @Override
    public void pushTo(Obstacle next, Directions d, int force) {
        Worker sw = controller.getSelectedworker();
        if(this != sw){
            this.die();
        }
    }

    //ha meghal az aktuális mező visitorját nullra állítja
    //ezután kiveszi az elérhető munkások listájából magát
    public void die() {
        System.out.println(this.toString() + ".die()");
        currentTile.setVisitor(null);
        currentTile = null;
        controller.eliminateWorker(this);
    }

    //objektum kiírásához
    public String toString() {
        return "worker" + counter;
    }
}

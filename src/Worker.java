import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Worker extends Visitor{
    private Controller controller = null;

    //név kiírására szolgáló számlálók
    static int instanceCounter = 0;
    int counter = 0;
    //Tabulate tabulate = new Tabulate();

    public Worker(){
        controller = Controller.getInstance();
        instanceCounter++;
        counter = instanceCounter;
    }



    public void move(Directions d) {
        tabulate.in();
        System.out.println(this.toString() + ".move("+ d +")");
        ATile next = currentTile.getNeighbor(d);
        next.accept(this, d);
        tabulate.out();
    }

    public void pushTo(Tile next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
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
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }

        tabulate.out();
    }

    public void pushTo(Switch next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d);
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

        tabulate.out();
    }

    public void pushTo(Hole next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        this.die();

        tabulate.out();
    }

    public void pushTo(Trap next, Directions d) {
        tabulate.in();

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

                tabulate.out();

                return;
            }
            if (input.equals("N") || input.equals("n")) {
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
                else {
                    Worker sw = controller.getSelectedworker();
                    if (this != sw){
                        this.die();
                    }
                }

                tabulate.out();

                return;
            }
        }
    }

    public void pushTo(Target next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
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
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }

        tabulate.out();
    }

    public void pushTo(Wall next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        Worker sw = controller.getSelectedworker();
        if(this != sw){
            this.die();
        }

        tabulate.out();
    }

    public void pushTo(Column next, Directions d) {
        tabulate.in();

        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        Worker sw = controller.getSelectedworker();
        if(this != sw){
            this.die();
        }

        tabulate.out();
    }

    public void die() {
        tabulate.in();

        System.out.println(this.toString() + ".die()");
        currentTile.setVisitor(null);
        currentTile = null;
        controller.eliminateWorker(this);

        tabulate.out();
    }

    public String toString() {
        return "worker" + counter;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Worker extends Visitor{
    private Controller controller = null;

    public Worker(){
        controller = Controller.getInstance();
    }

    public void move(Directions d) {
        ATile next = currentTile.getNeighbor(d);
        next.accept(this, d);
    }

    public void pushTo(Tile next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
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
    }

    public void pushTo(Switch next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

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
    }

    public void pushTo(Hole next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
        this.die();
    }

    public void pushTo(Trap next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
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
                return;
            }
        }
    }

    public void pushTo(Target next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
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
    }

    public void pushTo(Wall next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

        Worker sw = controller.getSelectedworker();
        if(this != sw){
            this.die();
        }
    }

    public void pushTo(Column next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

        Worker sw = controller.getSelectedworker();
        if(this != sw){
            this.die();
        }
    }

    public void die() {
        System.out.println("die()");
        currentTile.setVisitor(null);
        currentTile = null;
        controller.eliminateWorker(this);
    }


}

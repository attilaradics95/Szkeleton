import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Box extends Visitor {
    //Attribútumok
    Game game = null;

    //Függvények
    public Box() {
        game = Game.getInstance();
    }

    public void pushTo(Tile next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

        if(movable){
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
        }
    }

    public void pushTo(Switch next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

        if(movable) {
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
        }
    }

    public void pushTo(Hole next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

        if(movable){
            this.die();
        }
    }

    public void pushTo(Trap next, Directions d) {

        System.out.println("pushTo(" + next + "," + d + ")");
        if(movable) {
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
                    if (visitorOnNext != null) {
                        ATile next1 = next.getNeighbor(d);
                        next1.accept(visitorOnNext, d);
                        visitorOnNext = next.getVisitor();
                    }

                    if (visitorOnNext == null) {
                        currentTile.setVisitor(null);
                        next.setVisitor(this);
                    }
                    return;
                }
            }
        }
    }

    public void pushTo(Target next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");

        if(movable){
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
        }
    }

    public void pushTo(Wall next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void pushTo(Column next, Directions d) {
        System.out.println("pushTo(" + next + "," + d + ")");
    }

    public void setUnmovable() {
        System.out.println("setUnmovable()");
        movable = false;
    }

    public void die() {
        System.out.println("die()");
        currentTile.setVisitor(null);
        currentTile = null;
        game.decreaseBoxes(this);
    }

}

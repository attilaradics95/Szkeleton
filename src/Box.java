public class Box extends Visitor {
    //Attribútumok
    private Game game = null;
    private boolean movable;
    //id meghatározására szolgáló számlálók
    private static int instanceCounter = 0;

    //Függvények
    public Box() {
        game = Game.getInstance();
        movable = true;
        instanceCounter++;
        id = instanceCounter;
    }

    //Obstacle kivételével - mivel ide úgyse tud menni -  minden pushTo-nál megkérdezzük, hogy mozgatható-e a doboz
    //ha nem, akkor nem mozdul el -- ki hitte volna? -- egyébként a mezőtől függ, mi történik
    //ha van visitor a mezőn, amire lépne, akkor meghívja az azután következő mező accept függvényét a szomszédos visitorral
    // ha átkerül a következő mezőre beállítja magát a visitorának, annak a mezőnek, ahonnan ellépett nullra állítja

    //Tile
    // semmi extra nem történik
    public void pushTo(Tile next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                next.setVisitor(this);
            }
        }
    }

    //Switch
    // amikor átlép meghívja önmagát átadva paraméterként a switch switchIt metódusát
    public void pushTo(Switch next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");

        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if (visitorOnNext != null) {
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }

            if (visitorOnNext == null) {
                currentTile.setVisitor(null);
                next.setVisitor(this);
                next.switchIt(this);
            }
        }
    }

    @Override
    public void pushTo(Honey next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                next.setVisitor(this);
            }
        }
    }

    //Hole
    //beleesik és meghal
    public void pushTo(Hole next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            this.die();
        }
    }

    @Override
    public void pushTo(Oil next, Directions d, int force) {
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                next.setVisitor(this);
            }
        }
    }

    //Trap
    // megkérdezzük, hogy nyitva van-e
    // ha igen beleesik és meghal
    // ha nem, megpróbál odalépni - úgy viselkedik a Trap csukva, mint egy egyszerű Tile
    public void pushTo(Trap next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            if (next.isOpened()){
                this.die();
            }
            else{
                Visitor visitorOnNext = next.getVisitor();
                if (visitorOnNext != null) {
                    ATile next1 = next.getNeighbor(d);
                    next1.accept(visitorOnNext, d, reducedforce);
                    visitorOnNext = next.getVisitor();
                }

                if (visitorOnNext == null) {
                    currentTile.setVisitor(null);
                    next.setVisitor(this);
                }
            }
        }

    }

    //Target
    // ha odalép a doboz, mozgathatatlanná válik
    public void pushTo(Target next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
        if (movable){
            int reducedforce = force - this.force;
            //Ha a surlodas miatt az ero 0 ala csokken akkor visszater a metodus es a doboz a helyen marad
            if(reducedforce < 0){
                return;
            }

            Visitor visitorOnNext = next.getVisitor();
            if(visitorOnNext != null){
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, reducedforce);
                visitorOnNext = next.getVisitor();
            }
            if(visitorOnNext == null){
                currentTile.setVisitor(null);
                next.setVisitor(this);
                this.setUnmovable();
                //TODO addpoint meghivasa
            }
        }
    }

    // Obstacle
    //nem tud elmozdulni ezekre a mezőkre
    // összenyomni se lehet, így a dobozt toló munkás se mozdul el a helyéről
    @Override
    public void pushTo(Obstacle next, Directions d, int force) {
        System.out.println(this.toString() + ".pushTo(" + next + "," + d + ")");
    }

    //mozgathatatlanná válik a box
    public void setUnmovable() {
        System.out.println(this.toString() + ".setUnmovable()");
        movable = false;
    }

    //ha meghal az aktuális mező visitorját nullra állítja
    //ezután csökkenti a mozgatható dobozok számát
    public void die() {
        System.out.println(this.toString() + ".die()");
        currentTile.setVisitor(null);
        currentTile = null;
        game.decreaseBoxes(this);
    }

    //objektum kiíráshoz
    public String toString() {
        return "box" + id;
    }

}

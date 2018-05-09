public class Worker extends Visitor{

    //region Attribútumok
    private Controller controller;
    private Game game;
    //név kiírására szolgáló számlálók
    private static int instanceCounter = 0;
    //endregion

    //region Metódusok
    public Worker(){
        controller = Controller.getInstance();
        game = Game.getInstance();
        instanceCounter++;
        id = instanceCounter;
    }

    public Worker(int id, ElementView view){
        controller = Controller.getInstance();
        game = Game.getInstance();
        instanceCounter++;
        this.id = id;
        this.view = view;
    }

    //egy adott szekvenciában először a controller hívja meg a selectedWorker move-ját
    //így ezzel indul el minden szekvenciában a visitorok mozgatása
    //meghívja a kapott irányban következő mező accept metódusát a workerrel
    public void move(Directions d) {
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
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
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
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
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

    @Override
    public void pushTo(Honey next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
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
        this.die();
    }

    @Override
    public void pushTo(Oil next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
            next.setVisitor(this);
        }
        else {
            Worker sw = controller.getSelectedworker();
            if (this != sw){
                this.die();
            }
        }
    }

    //Trap
    // megkérdezzük, hogy nyitva van-e
    // ha igen beleesik és meghal
    // ha nem, megpróbál odalépni - úgy viselkedik a Trap csukva, mint egy egyszerű Tile
    public void pushTo(Trap next, Directions d, int force) {
        if (next.isOpened()) {
            this.die();
        } else {
            Visitor visitorOnNext = next.getVisitor();
            if (visitorOnNext != null) {
                ATile next1 = next.getNeighbor(d);
                next1.accept(visitorOnNext, d, force);
                visitorOnNext = next.getVisitor();
            }

            if (visitorOnNext == null) {
                if(next.isOpened()){
                    this.die();
                }
                else {
                    currentTile.setVisitor(null);
                    currentTile = next;
                    next.setVisitor(this);
                }
            } else {
                Worker sw = controller.getSelectedworker();
                if (this != sw) {
                    this.die();
                }
            }
        }
    }

    //Target
    //ugyanúgy viselkedik a raktárossal szemben, mint a sima Tile
    public void pushTo(Target next, Directions d, int force) {
        Visitor visitorOnNext = next.getVisitor();
        if(visitorOnNext != null){
            ATile next1 = next.getNeighbor(d);
            next1.accept(visitorOnNext, d, force);
            visitorOnNext = next.getVisitor();
        }

        if(visitorOnNext == null){
            currentTile.setVisitor(null);
            currentTile = next;
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
        currentTile.setVisitor(null);
        currentTile = null;
        controller.eliminateWorker(this);
    }

    /**
     * objektum kiíráshoz
     * @return a kimeneti nyelvvel egyező szimbólum
     */
    @Override
    public String toString() {
        return "W" + id;
    }

    //Kicseréli a munkás alatti mezőt a Honey osztály egy példányára. Átállítja az összes érintett szomszédot.
    public void putHoney() {
        Honey honey = new Honey(new HoneyView());
        game.swap(this.currentTile,honey);
    }

    public void putOil() {
        Oil oil = new Oil(new OilView());
        game.swap(this.currentTile,oil);
    }
    //endregion
}
